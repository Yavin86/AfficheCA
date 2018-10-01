package com.yavin.afficheca.data.entity.mapper;

import com.yavin.afficheca.data.entity.EventEntity;
import com.yavin.afficheca.domain.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link EventEntity} (in the data layer) to {@link Event} in the
 * domain layer.
 */
@Singleton
public class EventEntityDataMapper {

    @Inject
    EventEntityDataMapper() {}

    /**
     * Transform a {@link EventEntity} into an {@link Event}.
     *
     * @param eventEntity Object to be transformed.
     * @return {@link Event} if valid {@link EventEntity} otherwise null.
     */
    public Event transform(EventEntity eventEntity) {
        Event event = null;
        if (eventEntity != null) {
            event = new Event(eventEntity.getId());
            event.setTitle(eventEntity.getTitle());
            event.setDescription(eventEntity.getDescription());
            event.setDetail(eventEntity.getDetail());
            event.setImageLinks(eventEntity.getImageLinks());
        }
        return event;
    }

    /**
     * Transform a List of {@link EventEntity} into a Collection of {@link Event}.
     *
     * @param eventEntityCollection Object Collection to be transformed.
     * @return {@link Event} if valid {@link EventEntity} otherwise null.
     */
    public List<Event> transform(Collection<EventEntity> eventEntityCollection) {
        final List<Event> eventList = new ArrayList<>(20);
        for (EventEntity eventEntity : eventEntityCollection) {
            final Event event = transform(eventEntity);
            if (event != null) {
                eventList.add(event);
            }
        }
        return eventList;
    }
}
