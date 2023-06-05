package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * props.pageProps.dehydratedState.queries[...].state.data
 * */
@Getter
@Setter
@ToString
public class BilibiliQueryStateDataJson {

    private BilibiliMediaInfoJson mediaInfo;

    private Map<String, Object> sectionsMap;

    private List<Integer> publicOrderSectionIds;

//    private Map<String, Object> rights;

//    private Map<String, Object> payment;

//    private Map<String, Object> epMap;

//    private List<Object> initEpList; // Seems like this field is equal to mediaInfo.mediaInfo.

//    private List<Object> initSections; // Seems like this field is related to trailers and behind-the-scene video.

//    private List<Object> seasonList; // No data related to bv nbr or cid.
}
