package per.itachi.java.tools.danmuku.app.serialize.xml.parser;

import per.itachi.java.tools.danmuku.app.port.Strategyable;

public interface XmlParser extends Strategyable {

    Object parseXmlAsObject(String xmlFilePath);
}
