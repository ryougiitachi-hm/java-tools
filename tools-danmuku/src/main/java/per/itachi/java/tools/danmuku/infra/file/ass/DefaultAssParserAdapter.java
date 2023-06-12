package per.itachi.java.tools.danmuku.infra.file.ass;

import java.util.List;
import org.springframework.stereotype.Component;
import per.itachi.java.tools.danmuku.app.port.AssParserPort;

@Component
public class DefaultAssParserAdapter implements AssParserPort {

    @Override
    public <T> void write(List<T> recordList, Class<T> clazz) {
        // convert records to ass record.
        writeScriptInfo();
        writeV4Styles();
        writeEvents();
        writeFonts();
        writeGraphics();
    }

    /**
     * The section for [Script Info]
     * */
    private void writeScriptInfo() {}

    /**
     * The section for [v4+ Styles]
     * */
    private void writeV4Styles() {}

    /**
     * The section for [Event]
     * */
    private void writeEvents() {}

    /**
     * The section for [Fonts]
     * */
    private void writeFonts() {}

    /**
     * The section for [Graphics]
     * */
    private void writeGraphics() {}
}
