package per.itachi.java.tools.danmuku.app.processor;

import per.itachi.java.tools.danmuku.app.dto.UrlInfoDTO;
import per.itachi.java.tools.danmuku.app.port.Strategyable;

public interface DanmukuProcessor extends Strategyable {

    void handle(UrlInfoDTO urlInfoDTO);
}
