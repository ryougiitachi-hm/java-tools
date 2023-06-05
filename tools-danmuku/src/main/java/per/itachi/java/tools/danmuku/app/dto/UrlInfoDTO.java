package per.itachi.java.tools.danmuku.app.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UrlInfoDTO {

    private String originalUrl;

    private String scheme;

    private String host;

    private int port;

    private List<String> paths;

    private String params; // TODO: parse.

}
