package by.epamtc.service.builder;

import by.epamtc.entity.Device;
import by.epamtc.service.exception.BuilderException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseBuilder {
    protected List<Device> devices = new ArrayList<>();

    public List<Device> getDevices() {
        return devices;
    }

    public abstract void buildDevices(File inputFile) throws BuilderException;
}
