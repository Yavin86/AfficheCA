package com.yavin.afficheca.data.entity.mapper.xmlModels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "root")
public class EventsVersionRoot {
    @Element(name = "version")
    private String version;

    @Element(name = "dataFile")
    private String dataFileName;

    public String getVersion() {
        return version;
    }

    public String getDataFileName() {
        return dataFileName;
    }

    @Override
    public String toString() {
        return "[version: " + version + ", dataFileName: " + dataFileName + "]";
    }
}