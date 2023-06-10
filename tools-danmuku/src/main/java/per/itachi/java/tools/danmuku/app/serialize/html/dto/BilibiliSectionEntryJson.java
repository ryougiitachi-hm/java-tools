package per.itachi.java.tools.danmuku.app.serialize.html.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BilibiliSectionEntryJson {

    private Integer id;

    private String title;

    private Integer type;

    private List<Object> episodeIds;

    private List<BilibiliEpisodeJson> epList;
}
