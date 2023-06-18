package per.itachi.java.tools.danmuku.app.generator;

import java.io.Serializable;
import per.itachi.java.tools.danmuku.app.port.Strategyable;

public interface IdGenerator extends Strategyable {

    Serializable generate();
}
