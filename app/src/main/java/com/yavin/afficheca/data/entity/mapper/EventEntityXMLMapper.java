package com.yavin.afficheca.data.entity.mapper;

import com.yavin.afficheca.data.entity.EventEntity;
import com.yavin.afficheca.data.entity.mapper.xmlModels.EventItem;
import com.yavin.afficheca.data.entity.mapper.xmlModels.EventsRoot;
import com.yavin.afficheca.data.net.XmlApi;

import java.util.ArrayList;
import java.util.List;

public class EventEntityXMLMapper {

    public List<EventEntity> transformEventEntityCollection(EventsRoot eventsRoot) {
        List<EventEntity> eventEntitiesResult = new ArrayList<>();
        List<String> eventsIdOnMainList = eventsRoot.getEventsOnMain().getIds();

        for (EventItem event : eventsRoot.getEventsItems()) {
            // TODO filter base on eventsIdOnMainList
            List<String> eventImgLinkList = new ArrayList<>();
            for (String link: event.getImages()) {
                eventImgLinkList.add(XmlApi.API_BASE_URL + link);
            }

            EventEntity eventEntity = new EventEntity(
                    event.getId(),
                    event.getTitle(),
                    event.getDetail(),
                    event.getDescription(),
                    eventImgLinkList);

            eventEntitiesResult.add(eventEntity);
        }

        return eventEntitiesResult;
    }
}
