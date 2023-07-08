package per.itachi.java.tools.danmuku.joint.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.itachi.java.tools.danmuku.app.service.DanmukuService;

@Slf4j
@RestController
@RequestMapping("/danmuku")
public class DanmkuController {

    @Autowired
    private DanmukuService danmukuService;

    @GetMapping("/download")
    public void showDanmuku(@RequestParam String url) {
        String decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8);
        log.info("url={}, decodedUrl={}", url, decodedUrl);
        danmukuService.process(decodedUrl);
    }

    @GetMapping("/conversions/tencent2bilibili")
    public void convertTencent2BilibiliDanmaku(@RequestParam String inputFilePath) {
        String decodedPath = URLDecoder.decode(inputFilePath, StandardCharsets.UTF_8);
        log.info("inputFilePath={}, decodedPath={}", inputFilePath, decodedPath);
        danmukuService.convertTencent2BilibiliDanmaku(decodedPath);
    }

}
