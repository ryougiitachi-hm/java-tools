package per.itachi.java.tools.danmuku.app.serialize.xml.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import per.itachi.java.tools.danmuku.app.exception.InvalidStrategyException;

@Component
public class XmlParserSelector {

    @Autowired
    private List<XmlParser> xmlParserList;

    private Map<String, XmlParser> xmlParserMap;

    @PostConstruct
    public void init() {
        Map<String, XmlParser> xmlParserMap = new HashMap<>();
        for (XmlParser xmlParser : xmlParserList) {
            xmlParserMap.put(xmlParser.getStrategyName(), xmlParser);
        }
        this.xmlParserMap = xmlParserMap;
    }

    public Object parseXmlAsObject(String strategy, String xmlFilePath) {
        XmlParser xmlParser = findXmlParser(strategy);
        return xmlParser.parseXmlAsObject(xmlFilePath);
    }

    public String writeObjectToXml(String strategy, Object xmlObject, String outputFileName) {
        XmlParser xmlParser = findXmlParser(strategy);
        return xmlParser.writeObjectToXml(xmlObject, outputFileName);
    }

    private XmlParser findXmlParser(String strategy) {
        XmlParser xmlParser = xmlParserMap.get(strategy);
        if (xmlParser == null) {
            throw new InvalidStrategyException("");
        }
        return xmlParser;
    }

}
