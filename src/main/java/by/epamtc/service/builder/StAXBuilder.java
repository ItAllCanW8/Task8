package by.epamtc.service.builder;

import by.epamtc.entity.Device;
import by.epamtc.entity.GroupOfDevices;
import by.epamtc.entity.Port;
import by.epamtc.service.exception.BuilderException;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class StAXBuilder extends BaseBuilder {
//    private File inputFile;
    private Device currDevice;

//    public StAXBuilder(File inputFile) {
//        this.inputFile = inputFile;
//    }

    @Override
    public void buildDevices(File inputFile) throws BuilderException {
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader;
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(inputFile));

            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "device":
                            currDevice = new Device();
                            currDevice.setId(startElement.getAttributeByName(new QName("device_id")).getValue());

                            if(startElement.getAttributeByName(new QName("market_launch_date")) != null)
                                currDevice.setDateTime(startElement.getAttributeByName(new QName(
                                        "market_launch_date")).getValue());
                            break;
                        case "name":
                            currDevice.setName(parseFieldFromXml(reader));
                            break;
                        case "origin":
                            currDevice.setOrigin(parseFieldFromXml(reader));
                            break;
                        case "price":
                            currDevice.setPrice(Integer.parseInt(Objects.requireNonNull(parseFieldFromXml(reader))));
                            break;
                        case "peripheral":
                            currDevice.getType().setPeripheral(Boolean.parseBoolean(parseFieldFromXml(reader)));
                            break;
                        case "power_consumption":
                            currDevice.getType().setPowerConsumption(Integer
                                    .parseInt(Objects.requireNonNull(parseFieldFromXml(reader))));
                            break;
                        case "cooler_present":
                            currDevice.getType().setCoolerPresent(Boolean.parseBoolean(parseFieldFromXml(reader)));
                            break;
                        case "group_of_devices":
                            currDevice.getType().setGroupOfDevices(GroupOfDevices.fromString(parseFieldFromXml(reader)));
                            break;
                        case "port":
                            currDevice.getType().setPort(Port.fromString(parseFieldFromXml(reader)));
                            break;
                        case "critical":
                            currDevice.setCritical(Boolean.parseBoolean(parseFieldFromXml(reader)));
                            break;
                    }
                }

                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("device")) {
                        devices.add(currDevice);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new BuilderException("Oops! File " + inputFile + " not found", e);
        } catch (XMLStreamException e) {
            throw new BuilderException("Oops! Error parsing " + inputFile, e);
        }
    }

    private String parseFieldFromXml(XMLEventReader reader){
        try {
            return reader.nextEvent().asCharacters().getData();
        } catch (XMLStreamException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
