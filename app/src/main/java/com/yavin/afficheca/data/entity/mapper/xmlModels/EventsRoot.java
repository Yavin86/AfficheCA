package com.yavin.afficheca.data.entity.mapper.xmlModels;

import java.util.List;

public class EventsRoot {
    private EventsOnMain eventsOnMain;

    private List<EventItem> eventsItems;

    public EventsOnMain getEventsOnMain() {
        return eventsOnMain;
    }

    public List<EventItem> getEventsItems() {
        return eventsItems;
    }
}