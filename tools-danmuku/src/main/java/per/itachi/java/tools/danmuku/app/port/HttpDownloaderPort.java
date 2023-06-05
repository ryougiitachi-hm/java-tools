package per.itachi.java.tools.danmuku.app.port;

import java.io.InputStream;

public interface HttpDownloaderPort extends Strategyable {

    String STRATEGY_JDK = "JDK";

    String STRATEGY_HTTP_CLIENT = "httpclient";

    String STRATEGY_SPRING_RESTTEMPLATE = "spring-resttemplate";

    String HTTP_HEADER_CONTENT_ENCODING = "content-encoding";

    String HTTP_HEADER_CONTENT_TYPE = "content-type";

    /**
     * @return the path of output file.
     * */
    String parseAsFile(String strUrl, String outputNamePrefix, String outputNamePostfix);

    /**
     * @return the input stream of output file.
     * */
    InputStream parseAsStream(String url, String outputNamePrefix, String outputNamePostfix);
}
