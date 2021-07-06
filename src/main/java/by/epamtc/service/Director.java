package by.epamtc.service;

import by.epamtc.entity.Device;

import java.util.List;

public class Director {
    public static List<Device> createDevices(BaseBuilder builder){
        builder.buildName();
        builder.buildOrigin();
        builder.buildPrice();
        builder.buildType();
        builder.buildIsCritical();

        return builder.getDevices();
    }
}
