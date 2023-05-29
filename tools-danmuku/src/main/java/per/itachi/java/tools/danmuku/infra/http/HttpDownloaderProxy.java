package per.itachi.java.tools.danmuku.infra.http;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import per.itachi.java.tools.danmuku.app.exception.InvalidStrategyException;
import per.itachi.java.tools.danmuku.app.port.HttpDownloaderPort;

@Primary
@Component
public class HttpDownloaderProxy implements HttpDownloaderPort {

    @Autowired
    private List<HttpDownloaderPort> httpDownloaderPorts;

    @Value("${infra.http.strategy}")
    private String strategy;

    @PostConstruct
    public void init() {
        // in case that strategy is not valid value, application is not allowed to startup.
        verifyStrategy();
    }

    @Override
    public String getStrategyName() {
        // proxy class should not have strategy value.
        return null;
    }

    @Override
    public String parseAsFile(String url, String outputNamePrefix, String outputNamePostfix) {
        return verifyStrategy().parseAsFile(url, outputNamePrefix, outputNamePostfix);
    }

    @Override
    public InputStream parseAsStream(String url, String outputNamePrefix, String outputNamePostfix) {
        return verifyStrategy().parseAsStream(url, outputNamePrefix, outputNamePostfix);
    }

    private HttpDownloaderPort verifyStrategy() {
        Optional<HttpDownloaderPort> httpDownloaderPort = httpDownloaderPorts.stream()
                .filter(port-> strategy.equals(port.getStrategyName()))
                .findFirst();
        if (httpDownloaderPort.isEmpty()) {
            throw new InvalidStrategyException(
                    String.format("%s is not valid strategy for HttpDownloaderPort", strategy));
        }
        return httpDownloaderPort.get();
    }

}
