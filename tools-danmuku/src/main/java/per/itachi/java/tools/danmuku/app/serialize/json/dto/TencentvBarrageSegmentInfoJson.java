package per.itachi.java.tools.danmuku.app.serialize.json.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TencentvBarrageSegmentInfoJson {

    @JsonProperty("segment_start")
    private String segmentStart;

    @JsonProperty("segment_name")
    private String segmentName;
}
