package per.itachi.java.tools.danmuku.app.generator;

import java.io.Serializable;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UuidIdGenerator implements IdGenerator {

    @Override
    public String getStrategyName() {
        return GeneratorConstant.ID_GENERATOR_UUID;
    }

    @Override
    public Serializable generate() {
        return UUID.randomUUID().toString();
    }
}
