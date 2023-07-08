package per.itachi.java.tools.danmuku.app.pipeline;

import java.util.Collections;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PipelineProperties {

    private Map<String, PipelineItemProperties> items = Collections.emptyMap();

}
