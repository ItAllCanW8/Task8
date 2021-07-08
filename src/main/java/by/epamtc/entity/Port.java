package by.epamtc.entity;

public enum Port {
    COM("com"),
    USB("usb"),
    LPT("lpt"),
    NONE("none");

    private final String value;

    Port(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Port fromString(String value) {
        for (Port p : Port.values()) {
            if (p.value.equalsIgnoreCase(value))
                return p;
        }

        throw new IllegalArgumentException("No constant Port with value " + value + " found");
    }
}