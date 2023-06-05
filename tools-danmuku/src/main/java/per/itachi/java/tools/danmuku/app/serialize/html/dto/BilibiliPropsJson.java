package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BilibiliPropsJson {

    private BilibiliPagePropsJson pageProps;

    @JsonProperty("__N_SSP")
    private Boolean flagNSsp;
}
