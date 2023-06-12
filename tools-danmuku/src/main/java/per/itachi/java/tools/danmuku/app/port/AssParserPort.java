package per.itachi.java.tools.danmuku.app.port;

import java.util.List;

public interface AssParserPort {

    <T> void write(List<T> recordList, Class<T> clazz);
}
