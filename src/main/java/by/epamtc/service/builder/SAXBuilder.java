package by.epamtc.service.builder;

import by.epamtc.service.exception.BuilderException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXBuilder extends BaseBuilder {
//    private File inputFile;
//
//    public SAXBuilder(File inputFile){
//        this.inputFile = inputFile;
//    }

    @Override
    public void buildDevices(File inputFile) throws BuilderException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            DeviceHandler handler = new DeviceHandler();
            reader.setContentHandler(handler);
            reader.parse(String.valueOf(inputFile));

            devices = handler.getDevices();
        } catch (ParserConfigurationException e) {
            throw new BuilderException("Oops! Parser configuration failed ", e);
        } catch (IOException e) {
            throw new BuilderException("Oops! Error reading file " + inputFile, e);
        } catch (SAXException e) {
            throw new BuilderException("Oops! Error parsing file " + inputFile, e);
        }
    }
}
