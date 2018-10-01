package com.yavin.afficheca.data.entity.mapper.xmlModels;

import org.simpleframework.xml.ElementList;
import java.util.List;

public class EventsOnMain {
    @ElementList(inline = true, entry = "id")
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }
}

