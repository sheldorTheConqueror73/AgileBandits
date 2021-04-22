package utility;
import scene.*;

import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;


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