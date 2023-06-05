package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * props.pageProps.dehydratedState.queries[...]
 * */
@Getter
@Setter
@ToString
public class BilibiliDehydratedStateQueryJson {

    private BilibiliQueryStateJson state;

    private List<String> queryKey;

    private String queryHash;
}
