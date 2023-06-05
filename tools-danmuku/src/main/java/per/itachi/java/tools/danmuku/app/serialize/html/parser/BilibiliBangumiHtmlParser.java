package per.itachi.java.tools.danmuku.app.serialize.html.parser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import per.itachi.java.tools.danmuku.app.common.AppConstant;
import per.itachi.java.tools.danmuku.app.exception.InvalidHtmlException;
import per.itachi.java.tools.danmuku.app.serialize.html.dto.BilibiliExtractedHtmlDojo;

@Slf4j
@Component
public class BilibiliBangumiHtmlParser implements HtmlParser<BilibiliExtractedHtmlDojo> {

    private static final String HTML_SELECTOR_BV_ID = "body a.mediainfo_avLink__bN7nf";

//    private static final String HTML_SELECTOR_MAIN_TITLE = "";

    private static final String HTML_SELECTOR_NEXT_DATA_JSON = "body > script#__NEXT_DATA__";

    @Override
    public Class<BilibiliExtractedHtmlDojo> supportsHtmlDojo() {
        return BilibiliExtractedHtmlDojo.class;
    }

    @Override
    public String getStrategyName() {
        return AppConstant.PORTAL_BILIBILI_BANGUMI;
    }

    @Override
    public BilibiliExtractedHtmlDojo parseAsHtmlDojo(String htmlFilePath) {
        try(InputStream is = Files.newInputStream(Paths.get(htmlFilePath))) {
            return parseAsHtmlDojo(is);
        }
        catch (IOException e) {
            log.error("", e);
            throw new InvalidHtmlException(String.format("Error occurred when opening html %s", htmlFilePath));
        }
    }

    @Override
    public BilibiliExtractedHtmlDojo parseAsHtmlDojo(InputStream is) {
        String charsetName = StandardCharsets.UTF_8.name(); // TODO: to be improved.
        try {
            Document document = Jsoup.parse(is, charsetName, "");
            Element head = document.head();
            Element headTitle = head.selectFirst("title");
            Element elementBvId = document.selectFirst(HTML_SELECTOR_BV_ID);
            Element elementNextData = document.selectFirst(HTML_SELECTOR_NEXT_DATA_JSON);
            BilibiliExtractedHtmlDojo htmlDojo = new BilibiliExtractedHtmlDojo();
            htmlDojo.setBvid(elementBvId == null ? "" : elementBvId.text());
            htmlDojo.setMainTitle(headTitle == null ? "untitled" : headTitle.text());
            htmlDojo.setNextDataJson(elementNextData == null ? "null" : elementNextData.text());
            return htmlDojo; // TODO: to be improved for raw type.
        }
        catch (IOException e) {
            throw new InvalidHtmlException("Error occurred when parsing html input stream");
        }
    }
}
