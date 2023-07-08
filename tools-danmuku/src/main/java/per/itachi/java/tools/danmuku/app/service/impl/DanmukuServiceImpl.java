package per.itachi.java.tools.danmuku.app.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import per.itachi.java.tools.danmuku.app.dto.UrlInfoDTO;
import per.itachi.java.tools.danmuku.app.exception.AppException;
import per.itachi.java.tools.danmuku.app.pipeline.PipelineContext;
import per.itachi.java.tools.danmuku.app.pipeline.PipelineManager;
import per.itachi.java.tools.danmuku.app.processor.DanmukuProcessorSelector;
import per.itachi.java.tools.danmuku.app.service.DanmukuService;

@Slf4j
@Service
public class DanmukuServiceImpl implements DanmukuService {

    @Resource
    private Map<String, String> portalUrlMappings;

    @Resource
    private DanmukuProcessorSelector danmukuProcessorSelector;

    @Resource
    private PipelineManager pipelineManager;

    @Override
    public void process(String strUrl) {
        UrlInfoDTO url = parseUrl(strUrl);
        String portal = portalUrlMappings.get(url.getHost());
        danmukuProcessorSelector.handle(portal, url);
    }

    @Override
    public void process(String url, String portal) {
    }

    private UrlInfoDTO parseUrl(String originalUrl) {
        try {
            URL url = new URL(originalUrl);
            List<String> paths = Collections.emptyList();
            String strPaths = url.getPath();
            if(strPaths != null && strPaths.trim().length() > 0) {
                strPaths = strPaths.trim();
                if (strPaths.charAt(0) == '/') {
                    if (strPaths.length() == 1) {
                        paths = Collections.emptyList();
                    }
                    else {
                        paths = Arrays.asList(strPaths.substring(1).split("/"));
                    }
                }
            }
            UrlInfoDTO dto = new UrlInfoDTO();
            dto.setOriginalUrl(originalUrl);
            dto.setScheme(url.getProtocol());
            dto.setHost(url.getHost());
            dto.setPort(url.getPort());
            dto.setPaths(paths);
            dto.setParams(url.getQuery());
            return dto;
        }
        catch (MalformedURLException e) {
            log.error("Error occurred. ", e);
            throw new AppException("Error occurred. ", e);
        }
    }

    @Override
    public void convertTencent2BilibiliDanmaku(String inputFilePath) {
        Map<String, String> inputContextParams = new HashMap<>();
        inputContextParams.put(PipelineContext.CONTEXT_PARAM_TENCENT_DANMAKU_FILE_PATH, inputFilePath);
        Map<String, String> outputContextParams = pipelineManager
                .performPipeline("convert-tencent-to-bilibili-danmaku", inputContextParams);
        log.info("outputContextParams={}", outputContextParams);
    }
}