package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * props.pageProps.dehydratedState.queries[0].state.data.mediaInfo
 * The dojo may store the information about the current video,
 * including but not limited to intro, title, series name/film name, episodes, etc.
 * Maybe, both this mediaInfo and initEpInfo are fine to find danmuku info.
 * */
@Getter
@Setter
@ToString
public class BilibiliMediaInfoJson {

    @JsonProperty
    private Boolean hasPlayableEp;

    @JsonProperty("enable_vt")
    private Boolean enableVt;

    @JsonProperty("media_id")
    private Integer mediaId;

    @JsonProperty("season_id")
    private Integer seasonId;

    @JsonProperty("season_type")
    private Integer seasonType;

    @JsonProperty
    private Map<String, Integer> stat;

    @JsonProperty
    private String alias;

    @JsonProperty
    private Integer status;

    @JsonProperty("season_status")
    private String seasonStatus;

    @JsonProperty
    private String record;

    @JsonProperty
    private Map<String, Number> rating;

    @JsonProperty
    private String title;

//    @JsonProperty
//    private Map<String, Object> freya; // not required now.

    @JsonProperty
    private List<BilibiliEpisodeJson> episodes;

//    @JsonProperty("user_status")
//    private List<Object> userStatus; // not required now.

    @JsonProperty("section_bottom_desc")
    private String sectionBottomDesc;

    @JsonProperty
    private String evaluate;

    @JsonProperty("jp_title")
    private String jpTitle;

    @JsonProperty("season_title")
    private String seasonTitle;

    @JsonProperty("multi_view_info")
    private String multiViewInfo;

//    @JsonProperty
//    private List<Object> areas; // not required now.

    @JsonProperty
    private String series;

    @JsonProperty
    private Map<String, String> ssTypeFormat;

    @JsonProperty
    private Boolean multiMode;

    @JsonProperty
    private Boolean forceWide;

    @JsonProperty
    private String specialCover;

    @JsonProperty
    private String squareCover;

    @JsonProperty
    private String cover;

//    @JsonProperty
//    private Map<String, Object> publish; // not required now.

//    @JsonProperty
//    private Map<String, Object> rights; // not required now.

//    @JsonProperty("up_info")
//    private Map<String, Object> up_info; // not required now.

//    @JsonProperty
//    private Map<String, Object> activity; // not required now.

    @JsonProperty
    private String operation;

//    @JsonProperty("new_ep")
//    private Map<String, Object> newEp; // not required now.

    @JsonProperty("pay_pack")
    private Map<String, String> payPack;

    @JsonProperty
    private Boolean pgcType;

    @JsonProperty
    private Boolean epSpMode;

    @JsonProperty
    private Boolean newEpSpMode;

    @JsonProperty
    private List<String> styles;

    @JsonProperty
    private String actors;

    @JsonProperty
    private String staff;

    @JsonProperty
    private List<String> playStrategy;
}
