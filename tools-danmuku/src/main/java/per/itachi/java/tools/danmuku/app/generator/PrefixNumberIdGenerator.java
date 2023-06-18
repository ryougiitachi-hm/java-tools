package per.itachi.java.tools.danmuku.app.generator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class PrefixNumberIdGenerator implements IdGenerator {

    private AtomicLong number = new AtomicLong(0);

    @Override
    public String getStrategyName() {
        return GeneratorConstant.ID_GENERATOR_PREFIX_NBR;
    }

    @Override
    public Serializable generate() {
        return String.format("W%d", number.incrementAndGet());
    }
}
