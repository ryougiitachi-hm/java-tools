package per.itachi.java.tools.danmuku.infra.http;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
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
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;
import lombok.extern.slf4j.Slf4j;
import org.brotli.dec.BrotliInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import per.itachi.java.tools.danmuku.app.exception.AdapterException;
import per.itachi.java.tools.danmuku.app.port.HttpDownloaderPort;

/**
 * To be continued: output path(input parameter or member variable default value).
 * */
@Slf4j
@Component
public class JdkHttpDownloaderAdapter implements HttpDownloaderPort {

    @Value("${infra.http.bufferSize}")
    private int bufferSize;

    @Value("${infra.http.outputDir}")
    private String outputDir;

    @Override
    public String getStrategyName() {
        return STRATEGY_JDK;
    }

    @Override
    public String parseAsFile(String strUrl, String outputNamePrefix, String outputNamePostfix) {
        try {
            URL url= new URL(strUrl);
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.connect();
//                String contentEncoding = urlConnection.getHeaderField(HTTP_HEADER_CONTENT_ENCODING);
                log.info("Start downloading url={}, ", strUrl);
                String strPath = url.getPath();
                List<String> listPath = Arrays.asList(strPath.split("/"));
                String strOutputName = listPath.isEmpty() ? url.getHost() : listPath.get(listPath.size() - 1);
                Path outputFilePath = Paths.get(outputDir,
                        strOutputName + generateFileExtendedName(urlConnection.getContentType()));
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

    @Override
    public InputStream parseAsStream(String strUrl, String outputNamePrefix, String outputNamePostfix) {
        try {
            URL url= new URL(strUrl);
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.connect();
//                String contentEncoding = urlConnection.getHeaderField(HTTP_HEADER_CONTENT_ENCODING);
                String strPath = url.getPath();
                List<String> listPath = Arrays.asList(strPath.split("/"));
                String strOutputName = listPath.isEmpty() ? url.getHost() : listPath.get(listPath.size() - 1);
                ByteArrayInputStream resultInputStream = new ByteArrayInputStream(new byte[urlConnection.getContentLength() * 10]);
                try(InputStream inputStream = wrapInputStream(urlConnection.getContentEncoding(), urlConnection.getInputStream());
                    OutputStream outputStream = new BufferedOutputStream(Files
                            .newOutputStream(Paths.get(outputDir,
                                    strOutputName + generateFileExtendedName(urlConnection.getContentType()))), bufferSize)) {
                    byte[] buffer = new byte[bufferSize];
                    int count = 0;
                    int countSum = 0;
                    while ((count = inputStream.read(buffer)) > 0) {
                        countSum += count;
                        outputStream.write(buffer);
                    }
                    log.info("The total size of input bytes is {}. ", countSum);
                }
                return resultInputStream;
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
            return new GZIPInputStream(sourceIs);
        case "deflate":
            return new DeflaterInputStream(sourceIs);
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
        String[] mimeLeftAndRight = mime.split("/");
        String mimeLeft = mimeLeftAndRight[0];
        String mimeRight = mimeLeftAndRight[1];
        // it is needed to define a mapping between mime type and suffix.
        return ".dat";
    }

}
