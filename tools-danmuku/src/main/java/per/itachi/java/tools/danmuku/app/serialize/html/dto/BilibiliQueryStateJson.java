package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * props.pageProps.dehydratedState.queries[...].state
 * */
@Getter
@Setter
@ToString
public class BilibiliQueryStateJson {

    private BilibiliQueryStateDataJson data;

    private Integer dataUpdateCount;

    private Long dataUpdatedAt;

    private String error;

    private Integer errorUpdateCount;

    private Integer errorUpdatedAt;

    private Integer fetchFailureCount;

    private String fetchMeta;

    private Boolean isFetching;

    private Boolean isInvalidated;

    private Boolean isPaused;

    private String status;
}
