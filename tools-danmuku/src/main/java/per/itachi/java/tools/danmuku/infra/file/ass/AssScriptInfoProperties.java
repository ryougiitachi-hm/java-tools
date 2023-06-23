package per.itachi.java.tools.danmuku.infra.file.ass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * So far,
 * Title: file name.
 * Original Script: file name.
 * */
@Getter
@Setter
@ToString
public class AssScriptInfoProperties {

    private String scriptType;

    private String collisions;

    private String playResX;

    private String playResY;

    private String timer;
}
