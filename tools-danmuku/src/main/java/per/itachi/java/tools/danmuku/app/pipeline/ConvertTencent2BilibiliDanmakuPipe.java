package per.itachi.java.tools.danmuku.app.pipeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import per.itachi.java.tools.danmuku.app.common.AppConstant;
import per.itachi.java.tools.danmuku.app.serialize.json.dto.TencentvBarrageJson;
import per.itachi.java.tools.danmuku.app.serialize.json.dto.TencentvBarrageListJson;
import per.itachi.java.tools.danmuku.app.serialize.xml.dto.BilibiliDXmlData;
import per.itachi.java.tools.danmuku.app.serialize.xml.dto.BilibiliIXmlData;
import per.itachi.java.tools.danmuku.app.serialize.xml.parser.XmlParserSelector;

/**
 * <p>
 * input context params:
 *  TENCENT_DANMAKU_FILE_PATH
 *
 * output context params:
 *  BILIBILI_DANMAKU_FILE_PATH
 * </p>
 * */
@Slf4j
@Component
public class ConvertTencent2BilibiliDanmakuPipe implements Pipe {

    private static final BigDecimal NUMERIC_BIGDECIMAL_1000 = new BigDecimal("1000");

    @Resource
    private ObjectMapper pipelineObjectMapper;

    @Resource
    private XmlParserSelector xmlParserSelector;

    @Override
    public String getStrategyName() {
        return PIPE_CONVERT_TENCENT_TO_BILIBILI_DANMAKU;
    }

    @Override
    public boolean perform(PipelineContext context) {
        // input context params
        String strTencentFilePath = context.getContextParam(PipelineContext.CONTEXT_PARAM_TENCENT_DANMAKU_FILE_PATH);
        // perform
        TencentvBarrageListJson tencentvBarrageListJson;
        Path tencentFilePath = Paths.get(strTencentFilePath);
        try(InputStream fis = Files.newInputStream(tencentFilePath)) {
            tencentvBarrageListJson = pipelineObjectMapper.readValue(fis, TencentvBarrageListJson.class);
        }
        catch (IOException e) {
            log.error("Error occurred when loading tencent danmaku file {}", strTencentFilePath, e);
            return false;
        }

        BilibiliIXmlData bilibiliIXmlData = new BilibiliIXmlData();
        List<String> bilibiliDanmakuItems = new ArrayList<>();
        for (TencentvBarrageJson tencentvBarrageJson : tencentvBarrageListJson.getBarrageList()) {
            // There may be error if add(idx,e) or set is used here
            bilibiliDanmakuItems.add(tencentvBarrageJson.getTimeOffset()
                    .divide(NUMERIC_BIGDECIMAL_1000, 5, RoundingMode.HALF_UP).toString()); // 0, timepoint
            bilibiliDanmakuItems.add("1"); // TODO: 1 type
            bilibiliDanmakuItems.add("25"); // TODO: 2 fontsize
            bilibiliDanmakuItems.add("16777215"); // TODO: 3 fontcolor, 16777215 == 0XFFFFFF
            bilibiliDanmakuItems.add(tencentvBarrageJson.getCreateTime()); // 4 ctimestamp
            bilibiliDanmakuItems.add("0"); // TODO: 5 pool
            bilibiliDanmakuItems.add("userid"); // TODO: 6 userid, seems like there is no field with user id in danmaku record.
            bilibiliDanmakuItems.add(tencentvBarrageJson.getId()); // 7 danmakuid
            bilibiliDanmakuItems.add("10"); // TODO: 8 unknown
            BilibiliDXmlData bilibiliDXmlData = new BilibiliDXmlData();
            bilibiliDXmlData.setP(String.join(",", bilibiliDanmakuItems));
            bilibiliDXmlData.setText(tencentvBarrageJson.getContent());
            bilibiliIXmlData.addData(bilibiliDXmlData);
            bilibiliDanmakuItems.clear();
        }
        String strOutputFilePath = xmlParserSelector.writeObjectToXml(AppConstant.PORTAL_BILIBILI, bilibiliIXmlData,
                tencentFilePath.getFileName().toString() + ".xml");
        // output context params
        context.putContextParam(PipelineContext.CONTEXT_PARAM_BILIBILI_DANMAKU_FILE_PATH, strOutputFilePath);
        return true;
    }
}
