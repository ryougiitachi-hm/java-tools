package per.itachi.java.tools.danmuku.infra.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConfig {

    @Bean
    @ConfigurationProperties("infra.http.headers")
    public Map<String, Map<String, String>> headers() {
        return new HashMap<>();
    }

    @Bean
    @ConfigurationProperties("infra.http.mime-extension-mappings")
    public Map<String, String> mimeExtensionMappings() {
        return new HashMap<>();
    }
}