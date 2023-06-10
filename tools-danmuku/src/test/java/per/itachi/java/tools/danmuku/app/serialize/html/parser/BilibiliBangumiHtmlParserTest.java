package per.itachi.java.tools.danmuku.app.serialize.html.parser;

import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import per.itachi.java.tools.danmuku.app.serialize.html.dto.BilibiliExtractedHtmlDojo;

@Slf4j
public class BilibiliBangumiHtmlParserTest {

    @Test
    public void parseAsHtmlDojoFromInputStream() {
        String strHtmlFilePath = "/bilibili-ep-ep741421.html";
        try(InputStream is = this.getClass().getClassLoader().getResourceAsStream(strHtmlFilePath)) {
            BilibiliBangumiHtmlParser parser = new BilibiliBangumiHtmlParser();
            BilibiliExtractedHtmlDojo htmlDojo = parser.parseAsHtmlDojo(is);
            log.info("htmlDojo={}", htmlDojo);
        }
        catch (IOException e) {
            log.error("Error occurred. ", e);
        }
    }

    @Test
    public void parseAsHtmlDojoFromFile() {
        String strHtmlFilePath =
                "C:/Users/gokuchang/Documents/workspaces/javaee/java-tools/tools-danmuku/src/test/resources/html/bilibili-ep-ep741421.html";
        BilibiliBangumiHtmlParser parser = new BilibiliBangumiHtmlParser();
        BilibiliExtractedHtmlDojo htmlDojo = parser.parseAsHtmlDojo(strHtmlFilePath);
        log.info("htmlDojo={}", htmlDojo);
    }
}