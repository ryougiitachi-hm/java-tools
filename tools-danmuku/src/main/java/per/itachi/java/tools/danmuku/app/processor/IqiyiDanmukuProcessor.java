package per.itachi.java.tools.danmuku.app.processor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.brotli.dec.BrotliInputStream;
import org.springframework.stereotype.Component;
import per.itachi.java.tools.danmuku.app.common.AppConstant;
import per.itachi.java.tools.danmuku.app.dto.UrlInfoDTO;
import per.itachi.java.tools.danmuku.app.port.HttpDownloaderPort;
import per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo;
import per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.Danmuku;
import per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.Entry;

@Slf4j
@Component
public class IqiyiDanmukuProcessor implements DanmukuProcessor {

    @Resource
    private HttpDownloaderPort httpDownloaderPort;

    @Override
    public String getStrategyName() {
        return AppConstant.PORTAL_IQIYI;
    }

    @Override
    public void handle(UrlInfoDTO urlInfoDTO) {
        String strFilePath = httpDownloaderPort.parseAsFile(urlInfoDTO.getOriginalUrl(), "", "");
        try(InputStream danmukuis = new BrotliInputStream(Files.newInputStream(Paths.get(strFilePath)))) {
            Danmuku danmuku = Danmuku.parseFrom(danmukuis);
//                    log.info("danmuku={}. ", danmuku);
            log.info("The danmuku info is as follows: ");
            log.info("danmuku.entry.count={}. ", danmuku.getEntryCount());
            int countOfBullet = 0;
            for (Entry entry : danmuku.getEntryList()) {
                log.info("danmuku.entry.bulletInfo.count={}. ", entry.getBulletInfoCount());
                for (BulletInfo bulletInfo : entry.getBulletInfoList()) {
                    log.info("danmuku.entry.bulletInfo.content={}. ", bulletInfo.getContent());
                }
                countOfBullet += entry.getBulletInfoCount();
            }
            log.info("The total number of bulletInfo is {}. ", countOfBullet);
        }
        catch (IOException e) {
            log.error("Error occurred. ", e);
        }
    }
}
