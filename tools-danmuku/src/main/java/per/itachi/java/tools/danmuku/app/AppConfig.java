package per.itachi.java.tools.danmuku.app;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConfigurationProperties("app.processor.portal-url-mappings")
    public Map<String, String> portalUrlMappings() {
        return new HashMap<>();
    }
}
