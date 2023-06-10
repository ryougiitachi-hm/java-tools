package per.itachi.java.tools.danmuku.app.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;
import per.itachi.java.tools.danmuku.app.common.AppConstant;
import per.itachi.java.tools.danmuku.app.dto.UrlInfoDTO;
import per.itachi.java.tools.danmuku.app.exception.AppException;
import per.itachi.java.tools.danmuku.app.port.HttpDownloaderPort;
import per.itachi.java.tools.danmuku.app.serialize.json.dto.TencentvBarrageJson;
import per.itachi.java.tools.danmuku.app.serialize.json.dto.TencentvBarrageListJson;
import per.itachi.java.tools.danmuku.app.serialize.json.dto.TencentvBarrageSegmentInfoJson;
import per.itachi.java.tools.danmuku.app.serialize.json.dto.TencentvBarrageSegmentsJson;

@Slf4j
@Component
public class TencentVideoDanmukuProcessor implements DanmukuProcessor {

    private static final String URL_TEMPLATE_BARRAGE_BASE = "https://dm.video.qq.com/barrage/base/%s";

    private static final String URL_TEMPLATE_BARRAGE_SEGMENT = "https://dm.video.qq.com/barrage/segment";

    @Value("${app.processor.bufferSize}")
    private int bufferSize;

    @Value("${app.processor.outputDir}")
    private String outputDir;

    @Value("${app.processor.reserveMiddleFile}")
    private boolean reserveMiddleFile;

    @Resource
    private HttpDownloaderPort httpDownloaderPort;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public String getStrategyName() {
        return AppConstant.PORTAL_TENCENT_VIDEO;
    }

    @Override
    public void handle(UrlInfoDTO urlInfoDTO) {
        if (CollectionUtils.isEmpty(urlInfoDTO.getPaths())) {
            throw new AppException("No valid http url paths found.");
        }
        // https://v.qq.com/x/cover/{1}/{2}.html
        List<String> paths = urlInfoDTO.getPaths();
        String strVideoId = paths.get(paths.size() - 1);
        strVideoId = strVideoId.substring(0, strVideoId.indexOf("."));
        String strSegmentInfoFilePath = httpDownloaderPort
                .parseAsFile(String.format(URL_TEMPLATE_BARRAGE_BASE, strVideoId), AppConstant.PORTAL_TENCENT_VIDEO, "");
        TencentvBarrageSegmentsJson tencentvBarrageSegmentsJson = extractDanmukuSegments(strSegmentInfoFilePath);
        List<TencentvBarrageJson> tencentvBarrageJsonList = extractActualDanmuku(tencentvBarrageSegmentsJson, strVideoId);
        log.info("Downloaded {} danmuku successfully from portal {} ", tencentvBarrageJsonList.size(), urlInfoDTO.getOriginalUrl());
        storeDanmuku(tencentvBarrageJsonList, strVideoId);
    }

    private TencentvBarrageSegmentsJson extractDanmukuSegments(String segmentsFilePath) {
        try(InputStream inputStream = Files.newInputStream(Paths.get(segmentsFilePath))) {
            TencentvBarrageSegmentsJson tencentvBarrageSegmentsJson = objectMapper
                    .readValue(inputStream, TencentvBarrageSegmentsJson.class);
            log.info("Checking the availability of danmuku segments {}. ", segmentsFilePath);
            if (CollectionUtils.isEmpty(tencentvBarrageSegmentsJson.getSegmentIndex())) {
                log.error("tencentvBarrageSegmentsJson.segmentIndex is empty, {}. ", segmentsFilePath);
                throw new AppException("tencentvBarrageSegmentsJson.segmentIndex is empty. ");
            }
            log.info("Checking the availability of danmuku segments, size = {}. ",
                    tencentvBarrageSegmentsJson.getSegmentIndex().size());
            return tencentvBarrageSegmentsJson;
        }
        catch (IOException e) {
            log.error("Error occurred when reading tencent v segments list file {}. ", segmentsFilePath, e);
            throw new AppException("Error occurred when reading tencent v segments list file", e);
        }
    }

    private List<TencentvBarrageJson> extractActualDanmuku(TencentvBarrageSegmentsJson tencentvBarrageSegmentsJson, String videoId) {
        List<TencentvBarrageJson> tencentvBarrageJsonList = new LinkedList<>();
        for (Map.Entry<Integer, TencentvBarrageSegmentInfoJson> entry : tencentvBarrageSegmentsJson.getSegmentIndex().entrySet()) {
            String strDanmukuUrl = UriComponentsBuilder.fromHttpUrl(URL_TEMPLATE_BARRAGE_SEGMENT)
                    .pathSegment(videoId, entry.getValue().getSegmentName())
                    .build()
                    .toString();
            log.info("Sending HTTP request for {} to {}. ", entry.getKey(), strDanmukuUrl);
            try(InputStream is = parseHttpAsStream(strDanmukuUrl, AppConstant.PORTAL_TENCENT_VIDEO, "")) {
                TencentvBarrageListJson tencentvBarrageListJson = objectMapper.readValue(is, TencentvBarrageListJson.class);
                if (!CollectionUtils.isEmpty(tencentvBarrageListJson.getBarrageList())) {
                    tencentvBarrageJsonList.addAll(tencentvBarrageListJson.getBarrageList());
                }
            }
            catch (IOException e) {
                log.error("Failed to send HTTP request for {} to {}. ", entry.getKey(), strDanmukuUrl, e);
            }
        }
        return tencentvBarrageJsonList;
    }

    private InputStream parseHttpAsStream(String url, String outputNamePrefix, String outputNamePostfix) throws IOException {
        String strFilePath = httpDownloaderPort.parseAsFile(url, outputNamePrefix, outputNamePostfix);
        return Files.newInputStream(Paths.get(strFilePath));
    }

    private void storeDanmuku(List<TencentvBarrageJson> tencentvBarrageJsonList, String videoId) {
        TencentvBarrageListJson tencentvBarrageListJson = new TencentvBarrageListJson();
        tencentvBarrageListJson.setBarrageList(tencentvBarrageJsonList);
        Path outputPath = Paths.get(outputDir, AppConstant.PORTAL_TENCENT_VIDEO + "-" + videoId + "-actual" + ".json");
        try(OutputStream os = new BufferedOutputStream(Files.newOutputStream(outputPath), bufferSize)) {
            objectMapper.writeValue(os, tencentvBarrageListJson);
            log.info("Completed storing danmuku json file {}", outputPath);
        }
        catch (IOException e) {
            log.error("Failed to store danmuku json file {}", outputPath, e);
        }
    }
}
