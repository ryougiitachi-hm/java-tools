package per.itachi.java.tools.dankumu.infra.http;

import org.junit.Test;
import per.itachi.java.tools.danmuku.infra.http.JdkHttpDownloaderAdapter;

public class JdkHttpDownloaderAdapterTest {

    @Test
    public void parse() {
        String strUrl = "https://cmts.iqiyi.com/bullet/82/00/1697884287788200_60_1_b84bd99a.br";
        JdkHttpDownloaderAdapter adapter = new JdkHttpDownloaderAdapter();
        adapter.parseAsStream(strUrl, "", "");
    }

}
