package per.itachi.java.tools.danmuku.app.generator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import per.itachi.java.tools.danmuku.app.exception.AppException;

@Component
public class IdGeneratorSelector {

    @Resource
    private List<IdGenerator> idGeneratorList;

    private Map<String, IdGenerator> idGeneratorMap;

    @PostConstruct
    public void init() {
        Map<String, IdGenerator> idGeneratorMap = new HashMap<>();
        for (IdGenerator idGenerator : idGeneratorList) {
            if (StringUtils.hasText(idGenerator.getStrategyName())) {
                idGeneratorMap.put(idGenerator.getStrategyName(), idGenerator);
            }
        }
        this.idGeneratorMap = idGeneratorMap;
    }

    public Serializable generate(String strategy) {
        IdGenerator idGenerator = idGeneratorMap.get(strategy);
        if (idGenerator == null) {
            throw new AppException("");
        }
        return idGenerator.generate();
    }
}
