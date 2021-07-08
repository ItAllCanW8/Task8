package by.epamtc.service.builder;

import by.epamtc.entity.Device;
import by.epamtc.entity.GroupOfDevices;
import by.epamtc.entity.Port;
import by.epamtc.service.XmlTag;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class DeviceHandler extends DefaultHandler {
    private List<Device> devices;
    private Device currDevice;
    private XmlTag currTag;
    private EnumSet<XmlTag> tags;

    private static final String DEVICE = XmlTag.DEVICE.getValue();

    public DeviceHandler() {
        devices = new ArrayList<>();
        tags = EnumSet.range(XmlTag.NAME, XmlTag.CRITICAL);
    }

    public List<Device> getDevices() {
        return devices;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (DEVICE.equals(qName)) {
            currDevice = new Device();
            currDevice.setId(attributes.getValue(0));

            if(attributes.getLength() > 1)
                currDevice.setDateTime(attributes.getValue(1));
        }
        else{
            XmlTag tag = XmlTag.valueOf(qName.toUpperCase());

            if (tags.contains(tag))
                currTag = tag;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(XmlTag.DEVICE.getValue())) {
            devices.add(currDevice);
            currDevice = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();

        if (currTag != null) {
            switch (currTag) {
                case NAME:
                    currDevice.setName(data);
                    break;
                case ORIGIN:
                    currDevice.setOrigin(data);
                    break;
                case PRICE:
                    currDevice.setPrice(Integer.parseInt(data));
                    break;
                case PERIPHERAL:
                    currDevice.getType().setPeripheral(Boolean.parseBoolean(data));
                    break;
                case POWER_CONSUMPTION:
                    currDevice.getType().setPowerConsumption(Integer.parseInt(data));
                    break;
                case COOLER_PRESENT:
                    currDevice.getType().setCoolerPresent(Boolean.parseBoolean(data));
                    break;
                case GROUP_OF_DEVICES:
                    currDevice.getType().setGroupOfDevices(GroupOfDevices.fromString(data));
                    break;
                case PORT:
                    currDevice.getType().setPort(Port.fromString(data));
                    break;
                case CRITICAL:
                    currDevice.setCritical(Boolean.parseBoolean(data));
                    break;
            }
        }
        currTag = null;
    }
}

