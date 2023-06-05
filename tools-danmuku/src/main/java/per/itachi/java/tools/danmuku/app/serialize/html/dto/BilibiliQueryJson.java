package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BilibiliQueryJson {

    private String theme;

    @JsonProperty("from_spmid")
    private String fromSpmid;

    private String videoId;
}
