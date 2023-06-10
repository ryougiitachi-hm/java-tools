package per.itachi.java.tools.danmuku.app.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import per.itachi.java.tools.danmuku.app.dto.UrlInfoDTO;
import per.itachi.java.tools.danmuku.app.exception.AppException;
import per.itachi.java.tools.danmuku.app.exception.InvalidStrategyException;

@Slf4j
@Component
public class DanmukuProcessorSelector {

    @Autowired
    private List<DanmukuProcessor> danmukuProcessorList;

    private Map<String, DanmukuProcessor> danmukuProcessorMap;

    @PostConstruct
    public void init() {
        Map<String, DanmukuProcessor> danmukuProcessorMap = new HashMap<>();
        for (DanmukuProcessor processor : danmukuProcessorList) {
            // check duplicated possible strategy name.
            if (danmukuProcessorMap.containsKey(processor.getStrategyName())) {
                log.error("");
                throw new AppException("");
            }
            danmukuProcessorMap.put(processor.getStrategyName(), processor);
        }
        this.danmukuProcessorMap = danmukuProcessorMap;
    }


    public void handle(String strategyName, UrlInfoDTO urlInfoDTO) {
        DanmukuProcessor processor = danmukuProcessorMap.get(strategyName);
        if (processor == null) {
            throw new InvalidStrategyException(String.format("%s", strategyName));
        }
        processor.handle(urlInfoDTO);
    }

}
