package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BilibiliDehydratedStateJson {

    /**
     * no idea about this so far.
     * */
    private List<String> mutations;

    private List<BilibiliDehydratedStateQueryJson> querys;
}
