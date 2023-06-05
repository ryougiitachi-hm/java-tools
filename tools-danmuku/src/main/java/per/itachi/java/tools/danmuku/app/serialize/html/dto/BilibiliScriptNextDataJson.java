package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BilibiliScriptNextDataJson {

    private BilibiliPropsJson props;

    private String page;

    private BilibiliQueryJson query;

    private String buildId;

    private String assetPrefix;

    private Boolean isFallback;

    private Boolean customServer;

    /**
     * no idea about this so far.
     * */
    private List<String> scriptLoader;
}
