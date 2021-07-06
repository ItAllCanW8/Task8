package by.epamtc.service;

import by.epamtc.entity.Device;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class DomBuilder extends BaseBuilder {
    NodeList nodeList;

    public DomBuilder(File inputFile){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);

            Element root = doc.getDocumentElement();
            nodeList = root.getElementsByTagName("device");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    System.out.println("Name : "
                            + eElement
                            .getElementsByTagName("name")
                            .item(0)
                            .getTextContent());
                    System.out.println("Origin : "
                            + eElement
                            .getElementsByTagName("origin")
                            .item(0)
                            .getTextContent());
                    System.out.println("Price : "
                            + eElement
                            .getElementsByTagName("price")
                            .item(0)
                            .getTextContent());
                    System.out.println("Type : "
                            + eElement
                            .getElementsByTagName("type")
                            .item(0)
                            .getTextContent());

                    Element temp = (Element) eElement
                            .getElementsByTagName("type")
                            .item(0);

                    System.out.println(temp.getElementsByTagName("is_peripheral").item(0).getTextContent());
                    System.out.println(temp.getElementsByTagName("power_consumption").item(0).getTextContent());
                    System.out.println(temp.getElementsByTagName("is_cooler_present").item(0).getTextContent());
                    System.out.println(temp.getElementsByTagName("group_of_devices").item(0).getTextContent());
                    System.out.println(temp.getElementsByTagName("port").item(0).getTextContent());

//                    NodeList nodes = eElement
//                            .getElementsByTagName("type")
//                            .item(0)
//                            .getChildNodes();
//
//                    for (int j = 0; i < nodes.getLength(); j++){
//                        System.out.println("a " + nodes.item(j).getNodeValue());
//                    }

//                    System.out.println("Is Peripheral : "
//                            + eElement
//                            .getElementsByTagName("type")
//                            .item(0)
//                            .getChildNodes()
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Port : "
//                            + eElement
//                            .getElementsByTagName("type")
//                            .item(0)
//                            .getChildNodes()
//                            .item(4)
//                            .getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildName() {
        System.out.println("builded");
//        device.setName();
    }

    @Override
    public void buildOrigin() {
        System.out.println("builded");
    }

    @Override
    public void buildPrice() {
        System.out.println("builded");
    }

    @Override
    public void buildType() {
        System.out.println("builded");
    }

    @Override
    public void buildIsCritical() {
        System.out.println("builded");
    }
}
