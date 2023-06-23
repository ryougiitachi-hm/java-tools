package per.itachi.java.tools.danmuku.app.serialize.xml.dto;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The element for /i &lt;i&gt;&lt;/i&gt;
 * */
@Getter
@Setter
@ToString
public class BilibiliIXmlData {

    private String chatServer;

    private String chatId;

    private String mission;

    private String maxLimit;

    private String state;

    private String realName;

    private String source;

    private List<BilibiliDXmlData> dataList = new LinkedList<>();

    public void addData(BilibiliDXmlData data) {
        dataList.add(data);
    }
}
