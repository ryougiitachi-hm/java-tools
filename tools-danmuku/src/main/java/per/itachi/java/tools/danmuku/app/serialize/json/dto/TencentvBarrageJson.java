package per.itachi.java.tools.danmuku.app.serialize.json.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TencentvBarrageJson {

    /**
     * danmaku id
     * */
    @JsonProperty
    private String id;

    @JsonProperty("is_op")
    private Integer isOp;

    @JsonProperty("head_url")
    private String headUrl;

    /**
     * The time point when this piece of danmuku should appear, ms.
     * */
    @JsonProperty("time_offset")
    private BigDecimal timeOffset;

    @JsonProperty("up_count")
    private String upCount;

    @JsonProperty("bubble_head")
    private String bubbleHead;

    @JsonProperty("bubble_level")
    private String bubbleLevel;

    @JsonProperty("bubble_id")
    private String bubbleId;

    @JsonProperty("rick_type")
    private Integer rickType;

    @JsonProperty("content_style")
    private String contentStyle;

    @JsonProperty("user_vip_degree")
    private Integer userVipDegree;

    /**
     *  actual Long with second as time unit.
     * */
    @JsonProperty("create_time")
    private String createTime;

    /**
     * danmaku actual content
     * */
    @JsonProperty
    private String content;

    @JsonProperty("hot_type")
    private Integer hotType;

    @JsonProperty("gift_info")
    private String giftInfo;

    @JsonProperty("share_item")
    private String shareItem;

    @JsonProperty
    private String vuid;

    @JsonProperty
    private String nick;

    @JsonProperty("data_key")
    private String dataKey;

    @JsonProperty("content_score")
    private Integer contentScore;

    @JsonProperty("show_weight")
    private Integer showWeight;

    @JsonProperty("track_type")
    private Integer trackType;

    @JsonProperty("show_like_type")
    private Integer showLikeType;

    @JsonProperty("report_like_score")
    private BigDecimal reportLikeScore;
}
