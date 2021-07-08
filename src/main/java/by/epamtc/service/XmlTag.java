package by.epamtc.service;

public enum XmlTag {
    DEVICES("devices"),
    DEVICE("device"),
    NAME("name"),
    ORIGIN("origin"),
    PRICE("price"),
    TYPE("type"),
    PERIPHERAL("peripheral"),
    POWER_CONSUMPTION("power_consumption"),
    COOLER_PRESENT("cooler_present"),
    GROUP_OF_DEVICES("group_of_devices"),
    PORT("port"),
    CRITICAL("critical");

    private final String value;

    XmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
