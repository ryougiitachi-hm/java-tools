package per.itachi.java.tools.danmuku.app.serialize.json.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter@ToString
public class TencentvBarrageSegmentsJson {

    @JsonProperty("check_up_time")
    private String checkUpTime;

    @JsonProperty("count_to_display")
    private String countToDisplay;

    /**
     * No idea about this field so far.
     * */
    @JsonProperty("first_segment")
    private List<Object> firstSegment = Collections.emptyList();

    /**
     * Not important for this field so far.
     * */
    @JsonProperty("lead_barrage")
    private Map<String, Object> leadBarrage = Collections.emptyMap();

    /**
     * key is actually numeric string which can be converted to number.
     * */
    @JsonProperty("segment_index")
    private Map<Integer, TencentvBarrageSegmentInfoJson> segmentIndex = Collections.emptyMap();

    @JsonProperty("segment_span")
    private String segmentSpan;

    @JsonProperty("segment_start")
    private String segmentStart;

    @JsonProperty
    private String total;

}
