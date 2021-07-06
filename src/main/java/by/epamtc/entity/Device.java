package by.epamtc.entity;

public class Device {
    private String name;
    private String origin;
    private int price;
    private Type type;
    private boolean isCritical;

    public Device(){}

    public Device(String name, String origin, int price, Type type, boolean isCritical) {
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.type = type;
        this.isCritical = isCritical;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (price != device.price) return false;
        if (isCritical != device.isCritical) return false;
        if (name != null ? !name.equals(device.name) : device.name != null) return false;
        if (origin != null ? !origin.equals(device.origin) : device.origin != null) return false;
        return type != null ? type.equals(device.type) : device.type == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (isCritical ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", isCritical=" + isCritical +
                '}';
    }
}
