package com.yavin.afficheca.data.entity.mapper;

import com.yavin.afficheca.data.entity.EventEntity;
import com.yavin.afficheca.data.entity.mapper.xmlModels.EventItem;
import com.yavin.afficheca.data.entity.mapper.xmlModels.EventsRoot;

import java.util.ArrayList;
import java.util.List;

public class EventEntityXMLMapper {

    public List<EventEntity> transformEventEntityCollection(EventsRoot eventsRoot) {
        List<EventEntity> eventEntitiesResult = new ArrayList<>();
        List<String> eventsIdOnMainList = eventsRoot.getEventsOnMain().getIds();

        for (EventItem eventItem : eventsRoot.getEventsItems()) {
            // TODO filter base on eventsIdOnMainList
            EventEntity eventEntity = new EventEntity(
                    eventItem.getId(),
                    eventItem.getTitle(),
                    eventItem.getDetail(),
                    eventItem.getDescription(),
                    eventItem.getImages());

            eventEntitiesResult.add(eventEntity);
        }

        return eventEntitiesResult;
    }
}
