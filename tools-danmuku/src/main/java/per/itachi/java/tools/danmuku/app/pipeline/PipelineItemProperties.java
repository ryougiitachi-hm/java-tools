package per.itachi.java.tools.danmuku.app.pipeline;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PipelineItemProperties {

    private List<String> pipeList = Collections.emptyList();

    private List<String> inputContextParams = Collections.emptyList();

    private List<String> outputContextParams = Collections.emptyList();
}
