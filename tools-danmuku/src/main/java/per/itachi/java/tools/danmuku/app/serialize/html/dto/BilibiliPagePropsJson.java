package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BilibiliPagePropsJson {

    private BilibiliDehydratedStateJson dehydratedState;

    private String queryPath;

    private Boolean initialWide;

    private Map<String, String> savedState;

    private Map<String, String> abStats;
}
