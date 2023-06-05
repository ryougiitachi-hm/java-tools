package per.itachi.java.tools.danmuku.app.serialize.html.parser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import per.itachi.java.tools.danmuku.app.exception.InvalidHtmlException;

@Component
public class HtmlParserSelector {

    @Resource
    private List<HtmlParser<?>> htmlParserList;

    private Map<Class<?>, HtmlParser<?>> htmlParserMap;

    @PostConstruct
    public void init() {
        Map<Class<?>, HtmlParser<?>> htmlParserMap = new HashMap<>();
        for (HtmlParser<?> htmlParser : htmlParserList) {
            htmlParserMap.put(htmlParser.supportsHtmlDojo(), htmlParser);
        }
        this.htmlParserMap = htmlParserMap;
    }

    @SuppressWarnings("unchecked") // TODO: raw type
    public <T> T parseAsHtmlDojo(Class<T> clazz, String htmlFilePath) {
        HtmlParser<?> htmlParser = htmlParserMap.get(clazz);
        if (htmlParser == null) {
            throw new InvalidHtmlException("");
        }
        return (T)htmlParser.parseAsHtmlDojo(htmlFilePath);
    }

    @SuppressWarnings("unchecked") // TODO: raw type
    public <T> T parseAsHtmlDojo(Class<T> clazz, InputStream is) {
        HtmlParser<?> htmlParser = htmlParserMap.get(clazz);
        if (htmlParser == null) {
            throw new InvalidHtmlException("");
        }
        return (T)htmlParser.parseAsHtmlDojo(is);
    }

}
