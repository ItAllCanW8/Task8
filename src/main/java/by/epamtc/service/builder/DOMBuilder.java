package by.epamtc.service.builder;

import by.epamtc.entity.Device;
import by.epamtc.entity.GroupOfDevices;
import by.epamtc.entity.Port;

import by.epamtc.service.exception.BuilderException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMBuilder extends BaseBuilder {
    public DOMBuilder(){}

    @Override
    public void buildDevices(File inputFile) throws BuilderException {
        Document doc;

        try {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        doc = dBuilder.parse(inputFile);
        } catch (ParserConfigurationException e) {
            throw new BuilderException("Oops! Parser configuration failed ", e);
        } catch (IOException e) {
            throw new BuilderException("Oops! Error reading file " + inputFile, e);
        } catch (SAXException e) {
            throw new BuilderException("Oops! Error parsing file " + inputFile, e);
        }

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("device");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                Device device = new Device();

                device.setId(element.getAttributes().item(0).getTextContent());

                if(element.getAttributes().getLength() > 1)
                    device.setDateTime(element.getAttributes().item(1).getTextContent());

                device.setName(getElementTextContent(element,"name"));
                device.setOrigin(getElementTextContent(element,"origin"));
                device.setPrice(Integer.parseInt(getElementTextContent(element,"price")));
                device.setCritical(Boolean.parseBoolean(getElementTextContent(element,"critical")));

                Element temp = (Element) element.getElementsByTagName("type").item(0);

                device.getType().setPeripheral(Boolean.parseBoolean(getElementTextContent(temp,"peripheral")));
                device.getType().setPowerConsumption(Integer.parseInt(getElementTextContent(temp,"power_consumption")));
                device.getType().setCoolerPresent(Boolean.parseBoolean(getElementTextContent(temp,"cooler_present")));

                try {
                    device.getType().setGroupOfDevices(GroupOfDevices.fromString(getElementTextContent(temp,
                            "group_of_devices")));
                    device.getType().setPort(Port.fromString(getElementTextContent(temp,"port")));
                } catch (IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                }

                devices.add(device);
            }
        }
    }

    private String getElementTextContent(Element element, String name) {
        NodeList nList = element.getElementsByTagName(name);
        Node node = nList.item(0);

        return node.getTextContent();
    }
}
