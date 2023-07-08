package per.itachi.java.tools.danmuku.app.serialize.xml.parser;

import per.itachi.java.tools.danmuku.app.port.Strategyable;

public interface XmlParser extends Strategyable {

    /**
     * @param xmlFilePath the path of input xml file.
     * */
    Object parseXmlAsObject(String xmlFilePath);

    /**
     * @return the path of output xml file.
     * */
    String writeObjectToXml(Object xmlObject, String outputFileName);
}
