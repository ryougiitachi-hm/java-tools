package per.itachi.java.tools.danmuku.app.dto.danmaku;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BilibiliDanmakuDTO {

    /**
     * The time point when the danmaku appears in the video.
     * */
    private BigDecimal videoStartTime;

    private int type;

    private int fontSize;

    /**
     * Decimal color
     * */
    private int fontColor;

    private long ctimestamp;

    private int pool;

    private String userId;

    private String danmakuId;

    private String unknownReserved08;

    private String content;
}
