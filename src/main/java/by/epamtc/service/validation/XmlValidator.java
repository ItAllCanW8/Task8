package by.epamtc.service.validation;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    public static boolean isValidXml(File pathToFile, File pathToSchema) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(pathToSchema);

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(pathToFile));
        } catch (IOException e){
            System.out.println("Exception validating xml: "+e.getMessage());
            return false;
        }catch(SAXException e){
            System.out.println("SAX Exception validating xml: "+e.getMessage());
            return false;
        }

        return true;
    }
}
