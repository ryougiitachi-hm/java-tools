package per.itachi.java.tools.danmuku.app.serialize.html.parser;

import java.io.InputStream;
import per.itachi.java.tools.danmuku.app.port.Strategyable;

// TODO: better soution for raw type?
public interface HtmlParser<T> extends Strategyable {

    Class<T> supportsHtmlDojo();

    T parseAsHtmlDojo(String htmlFilePath);

    T parseAsHtmlDojo(InputStream htmlFilePath);
}
