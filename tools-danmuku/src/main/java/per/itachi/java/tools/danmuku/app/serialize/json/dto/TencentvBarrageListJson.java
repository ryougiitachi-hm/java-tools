package per.itachi.java.tools.danmuku.app.serialize.json.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TencentvBarrageListJson {

    @JsonProperty("barrage_list")
    private List<TencentvBarrageJson> barrageList;

}
