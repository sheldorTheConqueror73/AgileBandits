package algo;
import scene.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class xmlParser {
    public static Scene readXml(String Path,String Name) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            xmlHandler handler = new xmlHandler().setName(Name);
            parser.parse(Path,handler);
            return handler.getScene();
        }
        catch (Exception ecx) {
            ecx.printStackTrace();
            return null;
        }
    }

}