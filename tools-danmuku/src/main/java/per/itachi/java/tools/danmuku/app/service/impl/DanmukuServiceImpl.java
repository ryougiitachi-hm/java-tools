package per.itachi.java.tools.danmuku.app.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.brotli.dec.BrotliInputStream;
import org.springframework.stereotype.Service;
import per.itachi.java.tools.danmuku.app.port.HttpDownloaderPort;
import per.itachi.java.tools.danmuku.app.service.DanmukuService;
import per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo;
import per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.Danmuku;
import per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.Entry;

@Slf4j
@Service
public class DanmukuServiceImpl implements DanmukuService {

    private static final String PORTAL_IQIYI = "iqiyi";

    @Resource
    private Map<String, String> portalUrlMappings;

    @Resource
    private HttpDownloaderPort httpDownloaderPort;

    @Override
    public void process(String strUrl) {
        try {
            URL url = new URL(strUrl);
            String portal = portalUrlMappings.get(url.getHost());
            if (portal.equals(PORTAL_IQIYI)) {
                String strFilePath = httpDownloaderPort.parseAsFile(strUrl, "", "");
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
        catch (MalformedURLException e) {
            log.error("Error occurred. ", e);
        }
    }

    @Override
    public void process(String url, String portal) {
    }

}
