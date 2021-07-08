package by.epamtc.service.builder;

import by.epamtc.controller.ServiceException;
import by.epamtc.entity.Device;
import by.epamtc.service.exception.BuilderException;
import by.epamtc.service.validation.XmlValidator;

import java.io.File;
import java.util.List;

public class Director {
    public static List<Device> createDevices(BaseBuilder builder, File inputFile, File xsd) throws ServiceException {

        if (XmlValidator.isValidXml(inputFile, xsd)) {
            try {
                builder.buildDevices(inputFile);
            } catch (BuilderException e) {
                throw new ServiceException("Oops! Error parsing file");
            }

            return builder.getDevices();
        }

        throw new ServiceException("Oops! Not valid XML");
    }

}
