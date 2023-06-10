package per.itachi.java.tools.danmuku.app.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import per.itachi.java.tools.danmuku.app.common.AppConstant;
import per.itachi.java.tools.danmuku.app.dto.DanmukuOutputDTO;
import per.itachi.java.tools.danmuku.app.dto.UrlInfoDTO;
import per.itachi.java.tools.danmuku.app.exception.AppException;
import per.itachi.java.tools.danmuku.app.port.HttpDownloaderPort;
import per.itachi.java.tools.danmuku.app.serialize.html.dto.BilibiliDehydratedStateJson;
import per.itachi.java.tools.danmuku.app.serialize.html.dto.BilibiliDehydratedStateQueryJson;
import per.itachi.java.tools.danmuku.app.serialize.html.dto.BilibiliEpisodeJson;
import per.itachi.java.tools.danmuku.app.serialize.html.dto.BilibiliExtractedHtmlDojo;
import per.itachi.java.tools.danmuku.app.serialize.html.dto.BilibiliMediaInfoJson;
import per.itachi.java.tools.danmuku.app.serialize.html.dto.BilibiliScriptNextDataJson;
import per.itachi.java.tools.danmuku.app.serialize.html.dto.BilibiliSectionEntryJson;
import per.itachi.java.tools.danmuku.app.serialize.html.parser.HtmlParserSelector;

/**
 * Currently, only supports ep...
 * */
@Slf4j
@Component
public class BilibiliDanmukuProcessor implements DanmukuProcessor {

    /**
     * The only placeholder is cuid.
     * */
    private static final String URL_TEMPLATE = "https://comment.bilibili.com/%s.xml";

    @Resource
    private HttpDownloaderPort httpDownloaderPort;

    @Resource
    private HtmlParserSelector htmlParserSelector;

    @Resource
    private ObjectMapper objectMapper; // TODO: json strategy

    @Override
    public String getStrategyName() {
        return AppConstant.PORTAL_BILIBILI;
    }

    /**
     * Only supports BV, not including .
     * */
    @Override
    public void handle(UrlInfoDTO urlInfoDTO) {
        String strHtmlFilePath = httpDownloaderPort.parseAsFile(urlInfoDTO.getOriginalUrl(), AppConstant.PORTAL_BILIBILI, "");
        log.info("Downloaded html temp file from {}, filePath={}", urlInfoDTO.getOriginalUrl(), strHtmlFilePath);
        BilibiliExtractedHtmlDojo htmlDojo = htmlParserSelector.parseAsHtmlDojo(BilibiliExtractedHtmlDojo.class, strHtmlFilePath);
        String strNextDataJson = htmlDojo.getNextDataJson();
        try {
            BilibiliScriptNextDataJson dataJson = objectMapper.readValue(strNextDataJson, BilibiliScriptNextDataJson.class);
            List<BilibiliEpisodeJson> episodeJsonList = extractEpisodeListFromNextDataJson(dataJson);
//            String bvId = urlInfoDTO.getPaths().get(urlInfoDTO.getPaths().size() - 1);
            Optional<BilibiliEpisodeJson> episodeJson = episodeJsonList.stream()
                    .filter(episode -> episode.getBvid().equals(htmlDojo.getBvid()))
                    .findFirst();
            if (episodeJson.isEmpty()) {
                log.error("The BV {} is not found in {}. ", htmlDojo.getBvid(),
                        episodeJsonList.stream().map(BilibiliEpisodeJson::getBvid).toList());
                throw new AppException("No danmuku can be downloaded. ");
            }
            // download danmuku
            String strDanmukuFilePath = httpDownloaderPort.parseAsFile(String.format(URL_TEMPLATE, episodeJson.get().getCid()),
                    AppConstant.PORTAL_BILIBILI + "-" + htmlDojo.getMainTitle() + "-" + episodeJson.get().getTitle(),
                    "");
            log.info("Downloaded danmuku xml file from {}, filePath={}", urlInfoDTO.getOriginalUrl(), strDanmukuFilePath);
            DanmukuOutputDTO danmukuOutputDTO = new DanmukuOutputDTO();
            danmukuOutputDTO.setDanmukuOutputFile(strDanmukuFilePath);
            log.info("danmukuOutputDTO={}", danmukuOutputDTO);
        }
        catch (JsonProcessingException e) {
            log.error("Error occurred when parsing json. ", e);
            throw new AppException("Error occurred when parsing json. ", e);
        }
    }

    /**
     * check whether there are data objects which should exist.
     * */
    private List<BilibiliEpisodeJson> extractEpisodeListFromNextDataJson(BilibiliScriptNextDataJson dataJson) {
        if (dataJson.getProps() == null) {
            throw new IllegalStateException("dataJson.getProps() == null");
        }
        if (dataJson.getProps().getPageProps() == null) {
            throw new IllegalStateException("dataJson.getProps().getPageProps() == null");
        }
        if (dataJson.getProps().getPageProps().getDehydratedState() == null) {
            throw new IllegalStateException("dataJson.getProps().getDehydratedState() == null");
        }
        List<BilibiliDehydratedStateQueryJson> dehydratedStateJson = dataJson.getProps()
                .getPageProps().getDehydratedState().getQueries();
        if (dehydratedStateJson == null || dehydratedStateJson.isEmpty()) {
            throw new IllegalStateException("props.pageProps.dehydratedState.queries[...] is empty");
        }
        if (dehydratedStateJson.get(0).getState() == null) {
            throw new IllegalStateException("props.pageProps.dehydratedState.queries[0].state == null");
        }
        if (dehydratedStateJson.get(0).getState().getData() == null) {
            throw new IllegalStateException("props.pageProps.dehydratedState.queries[0].state.data == null");
        }
        // main episodes
        BilibiliMediaInfoJson mediaInfoJson = dehydratedStateJson.get(0).getState().getData().getMediaInfo();
        if (mediaInfoJson == null) {
            throw new IllegalStateException("props.pageProps.dehydratedState.queries[0].state.data.mediaInfo == null");
        }
        List<BilibiliEpisodeJson> episodes = mediaInfoJson.getEpisodes();
        if (episodes == null || episodes.isEmpty()) {
            throw new IllegalStateException("props.pageProps.dehydratedState.queries[0].state.data.mediaInfo.episodes is empty. ");
        }
        // result list
        List<BilibiliEpisodeJson> resultEpisodeList = new ArrayList<>(episodes);
        // trails etc
        Map<String, BilibiliSectionEntryJson> sectionsMap = dehydratedStateJson.get(0).getState().getData().getSectionsMap();
        if (CollectionUtils.isEmpty(sectionsMap)) {
            return resultEpisodeList;
        }
        for (Map.Entry<String, BilibiliSectionEntryJson> section : sectionsMap.entrySet()) {
            if (CollectionUtils.isEmpty(section.getValue().getEpList())) {
                continue;
            }
            resultEpisodeList.addAll(section.getValue().getEpList());
        }
        return resultEpisodeList;
    }
}
