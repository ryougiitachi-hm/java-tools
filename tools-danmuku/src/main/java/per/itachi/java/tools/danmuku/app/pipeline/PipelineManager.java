package per.itachi.java.tools.danmuku.app.pipeline;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import per.itachi.java.tools.danmuku.app.exception.AppException;

@Slf4j
@Component
public class PipelineManager {

    @Resource
    private PipelineProperties pipelineProperties;

    @Resource
    private List<Pipe> pipes;

    private Map<String, Pipe> pipeMap;

    @PostConstruct
    public void init() {
        Map<String, Pipe> pipeMap = new HashMap<>();
        for (Pipe pipe : pipes) {
            pipeMap.put(pipe.getStrategyName(), pipe);
        }
        this.pipeMap = pipeMap;
        // TODO: verify the availability of pipelineProperties
    }

    /**
     * TODO: Lack of feedback about state, eg, success or failure.
     * @param pipelineContextParams input list of parameters defined in config.
     * @return output list of parameters defined in config.
     * */
    public Map<String, String> performPipeline(String pipelineName, Map<String, String> pipelineContextParams) {
        PipelineItemProperties pipelineItemProperties = pipelineProperties.getItems().get(pipelineName);
        if (pipelineItemProperties == null) {
            throw new AppException(String.format("No such pipeline item config, pipelineName=%s", pipelineName));
        }
        if (CollectionUtils.isEmpty(pipelineItemProperties.getPipeList())) {
            log.warn("There is no available pipeline item to perform in pipelineItem={}", pipelineName);
            return Collections.emptyMap();
        }
        // initialise pipeline.
        List<Pipe> pipeList = new LinkedList<>();
        for (String pipeName : pipelineItemProperties.getPipeList()) {
            Pipe pipe = pipeMap.get(pipeName);
            if (pipe == null) {
                throw new AppException(String.format("No such pipe to perform, pipeName=%s", pipeName));
            }
            pipeList.add(pipe);
        }
        // initialise pipeline context parameter list.
        PipelineContext context = new PipelineContext();
        for (String inputContextParamName : pipelineItemProperties.getInputContextParams()) {
            context.putContextParam(inputContextParamName, pipelineContextParams.get(inputContextParamName));
        }
        // perform
        for (Pipe pipe : pipeList) {
            if (!pipe.perform(context)) {
                log.warn("The pipe didn't perform successfully due to false, pipelineName={}, pipe={}. ",
                        pipelineName, pipe.getStrategyName());
                return Collections.emptyMap();
            }
        }
        log.info("The pipeline performed all of pipes, pipelineName={}. ", pipelineName);
        Map<String, String> outputContext = new HashMap<>();
        for (String outputContextParamName : pipelineItemProperties.getOutputContextParams()) {
            outputContext.put(outputContextParamName, context.getContextParam(outputContextParamName));
        }
        return outputContext;
    }
}
