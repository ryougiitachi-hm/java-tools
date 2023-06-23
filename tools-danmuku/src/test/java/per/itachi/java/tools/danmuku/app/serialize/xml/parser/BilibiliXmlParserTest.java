package per.itachi.java.tools.danmuku.app.serialize.xml.parser;

import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.xml.sax.SAXException;

@Slf4j
public class BilibiliXmlParserTest {

    @Test
    public void parseXmlAsObject() {
        BilibiliXmlParser parser = new BilibiliXmlParser();
        // "/data/xml/bilibili-danmaku.xml" will err
        try(InputStream fis = parser.getClass().getClassLoader().getResourceAsStream("data/xml/bilibili-danmaku.xml")) {
            parser.parseXmlAsObject(fis);
        }
        catch (IOException | SAXException e) {
            log.error("", e);
        }
    }
}