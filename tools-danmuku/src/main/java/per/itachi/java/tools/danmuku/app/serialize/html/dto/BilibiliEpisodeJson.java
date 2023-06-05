package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BilibiliEpisodeJson {

    @JsonProperty
    private Long aid;

    @JsonProperty
    private String badge;

    @JsonProperty("badge_info")
    private Map<String, String> badgeInfo;

    @JsonProperty("badge_type")
    private Integer badgeType;

    @JsonProperty
    private String bvid;

    @JsonProperty
    private Long cid;

    @JsonProperty
    private String cover;

    @JsonProperty
    private Map<String, Integer> dimension;

    @JsonProperty
    private Long duration;

    @JsonProperty("enable_vt")
    private Boolean enableVt;

    @JsonProperty
    private String from;

    @JsonProperty
    private Long id; // This is actually ep id.

    @JsonProperty("is_view_hide")
    private Boolean isViewHide;

    @JsonProperty
    private String link;

    @JsonProperty("long_title")
    private String longTitle;

    @JsonProperty("pub_time")
    private Long pubTime;

    @JsonProperty
    private Integer pv;

    @JsonProperty("release_date")
    private String releaseDate;

//    @JsonProperty
//    private Map<String, Object> rights; // not required now

    @JsonProperty("share_copy")
    private String shareCopy;

    @JsonProperty("share_url")
    private String shareUrl;

    @JsonProperty("short_link")
    private String shortLink;

    @JsonProperty
    private Integer status;

    @JsonProperty
    private String subtitle;

    @JsonProperty
    private String title;

    @JsonProperty
    private String vid; // no idea about what id.

    @JsonProperty
    private Boolean loaded;

    @JsonProperty("ep_id")
    private Long epId;

    @JsonProperty("bgColor")
    private String bg_color;

//    @JsonProperty("archive_attr")
//    private String archiveAttr; // not required now.

    @JsonProperty
    private String titleFormat;

    @JsonProperty
    private Integer sectionType;

    @JsonProperty
    private Map<String, Object> skip;

//    @JsonProperty
//    private Map<String, Object> epRights; // not required now.

    @JsonProperty
    private String premiereBadgeInfo;

    @JsonProperty
    private Map<String, Object> stat;

    @JsonProperty
    private List<Integer> orderSectionIds;

    @JsonProperty
    private Boolean hasNext;

    @JsonProperty
    private Boolean hasSkip;
}
