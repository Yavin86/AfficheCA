package com.yavin.afficheca.presentation.mapper;

import com.yavin.afficheca.domain.Event;
import com.yavin.afficheca.presentation.di.PerActivity;
import com.yavin.afficheca.presentation.model.EventModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Event} (in the domain layer) to {@link EventModel} in the
 * presentation layer.
 */
@PerActivity
public class EventModelDataMapper {

    @Inject
    public EventModelDataMapper() {}

    /**
     * Transform a {@link Event} into an {@link EventModel}.
     *
     * @param event Object to be transformed.
     * @return {@link EventModel}.
     */
    public EventModel transform(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final EventModel eventModel = new EventModel(event.getId());
        eventModel.setTitle(event.getTitle());
        eventModel.setDetail(event.getDetail());
        eventModel.setDescription(event.getDescription());
        eventModel.setImageLinks(event.getImageLinks());

        return eventModel;
    }

    /**
     * Transform a Collection of {@link Event} into a Collection of {@link EventModel}.
     *
     * @param eventsCollection Objects to be transformed.
     * @return List of {@link EventModel}.
     */
    public Collection<EventModel> transform(Collection<Event> eventsCollection) {
        Collection<EventModel> eventModelsCollection;

        if (eventsCollection != null && !eventsCollection.isEmpty()) {
            eventModelsCollection = new ArrayList<>();
            for (Event event : eventsCollection) {
                eventModelsCollection.add(transform(event));
            }
        } else {
            eventModelsCollection = Collections.emptyList();
        }

        return eventModelsCollection;
    }
}
