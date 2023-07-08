package per.itachi.java.tools.danmuku.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import per.itachi.java.tools.danmuku.app.pipeline.PipelineProperties;

@Configuration
public class AppConfig {

    @Bean
    @ConfigurationProperties("app.processor.portal-url-mappings")
    public Map<String, String> portalUrlMappings() {
        return new HashMap<>();
    }

    @Bean
    @ConfigurationProperties("app.pipeline")
    public PipelineProperties pipelineProperties() {
        return new PipelineProperties();
    }

    @Bean
    public ObjectMapper pipelineObjectMapper() {
        return new ObjectMapper();
    }
}
