package per.itachi.java.tools.danmuku.infra.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipInputStream;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.brotli.dec.BrotliInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import per.itachi.java.tools.danmuku.app.exception.AdapterException;
import per.itachi.java.tools.danmuku.app.port.HttpDownloaderPort;

/**
 * To be continued: output path(input parameter or member variable default value).
 * */
@Slf4j
@Component
public class JdkHttpDownloaderAdapter implements HttpDownloaderPort {

    private static final Pattern REGEX_INVALID_PATH_CHARACTERS = Pattern.compile("[\\\\/:\\*\\?\\|<>]");

    private static final String HTTP_HEADER_COLON_AUTHORITY = ":Authority";

    private static final String HTTP_HEADER_COLON_METHOD = ":Method";

    private static final String HTTP_HEADER_COLON_PATH = ":Path";

    private static final String HTTP_HEADER_COLON_SCHEME = ":Scheme";

    @Autowired
    @Qualifier("headers")
    private Map<String, Map<String, String>> headers;

    @Autowired
    @Qualifier("mimeExtensionMappings")
    private Map<String, String> mimeExtensionMappings;

    @Value("${infra.http.bufferSize}")
    private int bufferSize;

    @Value("${infra.http.outputDir}")
    private String outputDir;

    @Override
    public String getStrategyName() {
        return STRATEGY_JDK;
    }

    @PostConstruct
    public void init() {
        log.info("");
    }

    @Override
    public String parseAsFile(String strUrl, String outputNamePrefix, String outputNamePostfix) {
        try {
            URL url= new URL(strUrl);
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection)url.openConnection();
                fillHttpHeaders(urlConnection);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                printResponseHttpHeaders(urlConnection);
//                String contentEncoding = urlConnection.getHeaderField(HTTP_HEADER_CONTENT_ENCODING);
                log.info("Connected repsonseCode={}, and started downloading with url={}, ", urlConnection.getResponseCode(), strUrl);
                String strPath = url.getPath();
                List<String> listPath = Arrays.asList(strPath.split("/"));
                String strOutputName = listPath.isEmpty() ? url.getHost() : listPath.get(listPath.size() - 1);
                String strOutputFileName = normalizeOutputFileName(strOutputName, outputNamePrefix, outputNamePostfix);
                Path outputFilePath = Paths.get(outputDir,
                        strOutputFileName + generateFileExtendedName(urlConnection.getContentType()));
                try(InputStream inputStream = wrapInputStream(urlConnection.getContentEncoding(), urlConnection.getInputStream());
                    OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(outputFilePath), bufferSize)) {
                    byte[] buffer = new byte[bufferSize];
                    int count = 0;
                    int countSum = 0;
                    while ((count = inputStream.read(buffer)) > 0) {
                        countSum += count;
                        outputStream.write(buffer, 0, count);
                    }
                    log.info("The total size of input bytes is {}. ", countSum);
                }
                return outputFilePath.toString();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect(); // HttpURLConnection
                }
            }
        }
        catch (IOException e) {
            log.error("Failed to download http url {}. ", strUrl, e);
            return null; // not good habit
        }
    }

    // InputStream can't be returned directly after closing http connenction.
    @Override
    public InputStream parseAsStream(String strUrl, String outputNamePrefix, String outputNamePostfix) {
        String strFilePath = parseAsFile(strUrl, outputNamePrefix, outputNamePostfix);
        try {
            return new BufferedInputStream(Files.newInputStream(Paths.get(strFilePath)), bufferSize);
        }
        catch (IOException e) {
            throw new AdapterException(String.format("Failed to open input stream after transferring %s. ", strUrl), e);
        }
    }

    private String normalizeOutputFileName(String outputFileName, String outputNamePrefix, String outputNamePostfix) {
        String normalized = outputNamePrefix + "-" + outputFileName;
        return normalized.replaceAll(REGEX_INVALID_PATH_CHARACTERS.pattern(), "");
    }

    private void fillHttpHeaders(HttpURLConnection connection) {
        URL url = connection.getURL();
        String host = url.getHost();
        Map<String, String> headersDefault = headers.get("default");
//        connection.setRequestProperty(HTTP_HEADER_COLON_AUTHORITY, url.getHost());
//        connection.setRequestProperty(HTTP_HEADER_COLON_METHOD, "GET");
//        connection.setRequestProperty(HTTP_HEADER_COLON_PATH, url.getPath());
//        connection.setRequestProperty(HTTP_HEADER_COLON_SCHEME, url.getProtocol());
        if (!CollectionUtils.isEmpty(headersDefault)) {
            for (Map.Entry<String, String> entry : headersDefault.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
//                connection.addRequestProperty();
            }
        }
        Map<String, String> headersSpecific = headers.get(host);
        if (!CollectionUtils.isEmpty(headersSpecific)) {
            for (Map.Entry<String, String> entry : headersSpecific.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    private void printResponseHttpHeaders(HttpURLConnection urlConnection) {
        Map<String, List<String>> repsonseHeaders = urlConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> header : repsonseHeaders.entrySet()) {
            log.debug("Header {}={}", header.getKey(), header.getValue());
        }
    }

    private InputStream wrapInputStream(String contentEncoding, InputStream sourceIs)
            throws IOException {
        if (!StringUtils.hasText(contentEncoding)) {
//            return new BrotliInputStream(sourceIs);
            return sourceIs;
        }
        switch (contentEncoding) {
        case "br":
            return new BrotliInputStream(sourceIs);
        case "zip":
            return new ZipInputStream(sourceIs);
        case "gz":
        case "gzip":
            return new GZIPInputStream(sourceIs);
        case "deflate":
            return new InflaterInputStream(sourceIs, new Inflater(true)); // DeflaterInputStream
        default:
            throw new AdapterException(String.format("Unsupported content encoding, contentEncoding=%s", contentEncoding));
//            return new BrotliInputStream(sourceIs);
        }
//        return switch (contentEncoding) {
//            case "br" -> new BrotliInputStream(sourceIs);
//            case "zip", "tar" -> new ZipInputStream(sourceIs);
//            case "gz" -> new GZIPInputStream(sourceIs);
//            default -> throw new AdapterException(String.format("Unsupported content encoding, contentEncoding=%s", contentEncoding));
//        };
    }

    // TODO: generateFileExtendedName
    public String generateFileExtendedName(String contentType) {
        String[] mimeAndCharset = contentType.split(";");
        String mime = mimeAndCharset[0].trim();
        String charset = "UTF-8";
        if (mimeAndCharset.length == 2) {
            charset = mimeAndCharset[1].trim();
        }
//        String[] mimeLeftAndRight = mime.split("/");
//        String mimeLeft = mimeLeftAndRight[0];
//        String mimeRight = mimeLeftAndRight[1];
        String extension = mimeExtensionMappings.get(mime.trim());
        if (StringUtils.hasText(extension)) {
            return extension;
        }
        // it is needed to define a mapping between mime type and suffix.
        return ".dat";
    }
}
