package per.itachi.java.tools.dankumu.infra.protobuf.iqiyi;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.Danmuku;

@Slf4j
public class DanmukuTest {

    @Test
    public void parseFrom() {
        String strInputFilePath = System.getenv("DANMUKU_IQIYI_TEST_FILE_PATH");
        if (strInputFilePath == null ) {
            log.warn("DANMUKU_IQIYI_TEST_FILE_PATH is undefined. ");
            return;
        }
        try(InputStream fis = Files.newInputStream(Paths.get(strInputFilePath))) {
            Danmuku danmuku = Danmuku.parseFrom(fis);
            log.info("DANMUKU_IQIYI_TEST_FILE_PATH={}, danmuku={}. ", strInputFilePath, danmuku);
        }
        catch (IOException e) {
            log.error("Error occur, strInputFilePath={}. ", strInputFilePath, e);
        }
    }
}
