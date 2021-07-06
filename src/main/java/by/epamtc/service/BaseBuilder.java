package by.epamtc.service;

import by.epamtc.entity.Device;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBuilder {
    protected List<Device> devices = new ArrayList<>();

    public List<Device> getDevices() {
        return devices;
    }

    public abstract void buildName();
    public abstract void buildOrigin();
    public abstract void buildPrice();
    public abstract void buildType();
    public abstract void buildIsCritical();
}
