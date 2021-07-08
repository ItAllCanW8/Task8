package by.epamtc.entity;

public enum GroupOfDevices {
    MULTIMEDIA("multimedia"),
    INPUT_OUTPUT("input_output"),
    CPU("CPU"),
    RAM("RAM");

    private final String value;

    GroupOfDevices(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GroupOfDevices fromString(String value) {
        for (GroupOfDevices g : GroupOfDevices.values()) {
            if (g.value.equalsIgnoreCase(value))
                return g;
        }

        return null;
    }
}
