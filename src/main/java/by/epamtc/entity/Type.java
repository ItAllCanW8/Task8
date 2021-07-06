package by.epamtc.entity;

public class Type {
    private boolean isPeripheral;
    private int powerConsumption;
    private boolean isCoolerPresent;
    private GroupOfDevices groupOfDevices;
    private Port port;

    public Type() {
    }

    public Type(boolean isPeripheral, int powerConsumption, boolean isCoolerPresent, GroupOfDevices groupOfDevices,
                Port port) {
        this.isPeripheral = isPeripheral;
        this.powerConsumption = powerConsumption;
        this.isCoolerPresent = isCoolerPresent;
        this.groupOfDevices = groupOfDevices;
        this.port = port;
    }

    public boolean isPeripheral() {
        return isPeripheral;
    }

    public void setPeripheral(boolean peripheral) {
        isPeripheral = peripheral;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public boolean isCoolerPresent() {
        return isCoolerPresent;
    }

    public void setCoolerPresent(boolean coolerPresent) {
        isCoolerPresent = coolerPresent;
    }

    public GroupOfDevices getGroupOfDevices() {
        return groupOfDevices;
    }

    public void setGroupOfDevices(GroupOfDevices groupOfDevices) {
        this.groupOfDevices = groupOfDevices;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        if (isPeripheral != type.isPeripheral) return false;
        if (powerConsumption != type.powerConsumption) return false;
        if (isCoolerPresent != type.isCoolerPresent) return false;
        if (groupOfDevices != type.groupOfDevices) return false;
        return port == type.port;
    }

    @Override
    public int hashCode() {
        int result = (isPeripheral ? 1 : 0);
        result = 31 * result + powerConsumption;
        result = 31 * result + (isCoolerPresent ? 1 : 0);
        result = 31 * result + (groupOfDevices != null ? groupOfDevices.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Type{" +
                "isPeripheral=" + isPeripheral +
                ", powerConsumption=" + powerConsumption +
                ", isCoolerPresent=" + isCoolerPresent +
                ", groupOfDevices=" + groupOfDevices +
                ", port=" + port +
                '}';
    }
}
