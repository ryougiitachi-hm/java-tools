package per.itachi.java.tools.danmuku.app.generator;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import per.itachi.java.tools.danmuku.app.exception.AppException;

@Primary
@Component
public class IdGeneratorProxy implements IdGenerator {

    @Value("${app.generator.id-generator.strategy}")
    private String strategy;

    @Resource
    private List<IdGenerator> idGeneratorList;

    @PostConstruct
    public void init() {
        verifyIdGenerator();
    }

    @Override
    public String getStrategyName() {
        return null;
    }

    @Override
    public Serializable generate() {
        IdGenerator idGenerator = verifyIdGenerator();
        return idGenerator.generate();
    }

    private IdGenerator verifyIdGenerator() {
        Optional<IdGenerator> idGeneratorOptional =idGeneratorList.stream()
                .filter(idGenerator -> strategy.equals(idGenerator.getStrategyName()))
                .findFirst();
        if (idGeneratorOptional.isEmpty()) {
            throw new AppException(String.format("%s is not valid IdGenerator", strategy));
        }
        return idGeneratorOptional.get();
    }
}
