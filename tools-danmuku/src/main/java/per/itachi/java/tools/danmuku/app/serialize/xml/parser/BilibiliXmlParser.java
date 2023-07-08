package per.itachi.java.tools.danmuku.app.serialize.xml.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.digester3.Digester;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.xml.sax.SAXException;
import per.itachi.java.tools.danmuku.app.common.AppConstant;
import per.itachi.java.tools.danmuku.app.dto.danmaku.BilibiliDanmakuDTO;
import per.itachi.java.tools.danmuku.app.exception.AppException;
import per.itachi.java.tools.danmuku.app.serialize.xml.dto.BilibiliDXmlData;
import per.itachi.java.tools.danmuku.app.serialize.xml.dto.BilibiliIXmlData;

@Slf4j
@Component
public class BilibiliXmlParser implements XmlParser {

    private static final int FLD_IDX_VIDEO_START_TIME = 0;

    private static final int FLD_IDX_TYPE = 1;

    private static final int FLD_IDX_FONT_SIZE = 2;

    private static final int FLD_IDX_FONT_COLOR = 3;

    private static final int FLD_IDX_CTIMESTAMP = 4;

    private static final int FLD_IDX_POOL = 5;

    private static final int FLD_IDX_USER_ID = 6;

    private static final int FLD_IDX_DANMAKU_ID = 7;

    private static final int FLD_IDX_UNKNOWN_RESERVED_08 = 8;

    @Value("${app.common.danmaku-output-dir}")
    private String danmakuOutputDir;

    @Override
    public String getStrategyName() {
        return AppConstant.PORTAL_BILIBILI;
    }

    @Override
    public Object parseXmlAsObject(String xmlFilePath) {
        File file = new File(xmlFilePath);
        try(InputStream fis = Files.newInputStream(file.toPath())) {
            return parseXmlAsObject(fis);
        }
        catch (IOException | SAXException e) {
            log.error("Error occurred when parsing bilibili xml danmaku file {}. ", xmlFilePath, e);
            throw new AppException(String
                    .format("Error occurred when parsing bilibili xml danmaku file %s. ", xmlFilePath), e);
        }
    }

    public Object parseXmlAsObject(InputStream is) throws IOException, SAXException {
        log.info("Started deserializing bilibili danmaku xml file");
        Digester digester = new Digester();
        digester.addObjectCreate("i", BilibiliIXmlData.class);
        digester.addSetProperties("i");
        // not working
//        digester.addObjectCreate("i/chatserver", String.class);
//        digester.addObjectCreate("i/chatid", String.class);
//        digester.addObjectCreate("i/mission", String.class);
//        digester.addObjectCreate("i/maxlimit", String.class);
//        digester.addObjectCreate("i/real_name", String.class);
//        digester.addObjectCreate("i/source", String.class);
        digester.addCallMethod("i/chatserver", "setChatServer", 0);
        digester.addCallMethod("i/chatid", "setChatId", 0);
        digester.addCallMethod("i/mission", "setMission", 0);
        digester.addCallMethod("i/maxlimit", "setMaxLimit", 0);
        digester.addCallMethod("i/state", "setState", 0);
        digester.addCallMethod("i/real_name", "setRealName", 0);
        digester.addCallMethod("i/source", "setSource", 0);
        digester.addObjectCreate("i/d", BilibiliDXmlData.class);
        digester.addSetProperties("i/d");
        digester.addCallMethod("i/d", "setText", 0);
        digester.addSetNext("i/d", "addData");
        BilibiliIXmlData result = digester.parse(is); // <BilibiliIXmlData> can be ignored.
        log.info("Completed deserializing bilibili danmaku xml file");
        log.info("Started parsing biilibili danmaku xml file");
        if (CollectionUtils.isEmpty(result.getDataList())) {
//            return ;
        }
        int skippedCount = 0;
        List<BilibiliDanmakuDTO> danmakuDTOList = new LinkedList<>();
        for (BilibiliDXmlData xmlData : result.getDataList()) {
            if (!StringUtils.hasText(xmlData.getP()) || !StringUtils.hasText(xmlData.getText())) {
                ++skippedCount;
                continue;
            }
            String[] fields = xmlData.getP().split(",");
            if (fields.length < 9) {
                ++skippedCount;
                continue;
            }
            BilibiliDanmakuDTO danmakuDTO = new BilibiliDanmakuDTO();
            // TODO: lack of verification for field value
            danmakuDTO.setVideoStartTime(new BigDecimal(fields[FLD_IDX_VIDEO_START_TIME]));
            danmakuDTO.setType(Integer.parseInt(fields[FLD_IDX_TYPE]));
            danmakuDTO.setFontSize(Integer.parseInt(fields[FLD_IDX_FONT_SIZE]));
            danmakuDTO.setFontColor(Integer.parseInt(fields[FLD_IDX_FONT_COLOR]));
            danmakuDTO.setCtimestamp(Long.parseLong(fields[FLD_IDX_CTIMESTAMP]));
            danmakuDTO.setPool(Integer.parseInt(fields[FLD_IDX_POOL]));
            danmakuDTO.setUserId(fields[FLD_IDX_USER_ID]);
            danmakuDTO.setDanmakuId(fields[FLD_IDX_DANMAKU_ID]);
            danmakuDTO.setUnknownReserved08(fields[FLD_IDX_UNKNOWN_RESERVED_08]);
            danmakuDTO.setContent(xmlData.getText());
            danmakuDTOList.add(danmakuDTO);
        }
        log.info("Completed parsing biilibili danmaku xml file, skipped {}. ", skippedCount);
        return result;
    }

    @Override
    public String writeObjectToXml(Object xmlObject, String outputFileName) {
        if (!(xmlObject instanceof BilibiliIXmlData bilibiliIXmlData)) {
            throw new AppException(String.format(
                    "xmlObject is not BilibiliIXmlData instance, actually %s", xmlObject.getClass()));
        }
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
//        XMLOutputFactory xof = XMLOutputFactory.newFactory(); // the same manner as above
        Path outputFilePath = Paths.get(danmakuOutputDir, outputFileName);
        try(Writer br = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8)) {
            // XMLEventWriter
            XMLStreamWriter xew = xof.createXMLStreamWriter(br);
            try {
                int count = 0; // TODO: dirty
                xew.writeStartDocument(StandardCharsets.UTF_8.name(), "1.0"); // start document
                xew.writeStartElement("i"); // start /i
                writeHeaderElement(xew, "chatserver", "chat.bilibili.com"); // write /i/chatserver
//                writeHeaderElement(xew, "chatid", "0"); // write /i/chatid
                writeHeaderElement(xew, "mission", "0"); // write /i/mission
//                writeHeaderElement(xew, "maxlimit", "6000"); // write /i/maxlimit
                writeHeaderElement(xew, "state", "0"); // write /i/state
                writeHeaderElement(xew, "real_name", "0"); // write /i/real_name
                writeHeaderElement(xew, "source", "k-v"); // write /i/source
                for (BilibiliDXmlData bilibiliDXmlData : bilibiliIXmlData.getDataList()) {
                    xew.writeStartElement("d"); // start /i/d
                    xew.writeAttribute("p", bilibiliDXmlData.getP());
                    xew.writeCharacters(bilibiliDXmlData.getText());
                    xew.writeEndElement(); // end /i/d
                    // flussh
                    ++ count;
                    if (count >= 200) {
                        xew.flush();
                    }
                }
                xew.writeEndElement(); // end /i
                xew.writeEndDocument(); // end document
            }
            finally {
                xew.close();
            }
        }
        catch (XMLStreamException | IOException e) {
            log.error("Error occurred when writing bilibili danmaku xml file, outputFileName={}. ",
                    outputFileName, e);
        }
        // TODO: what if any error is thrown.
        return outputFilePath.toAbsolutePath().toString();
    }

    private void writeHeaderElement(XMLStreamWriter xew, String elementName, String elementValue)
            throws XMLStreamException {
        xew.writeStartElement(elementName);
        xew.writeCharacters(elementValue);
        xew.writeEndElement();
    }
}
