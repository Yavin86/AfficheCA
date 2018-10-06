package com.yavin.afficheca.data.repository;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.yavin.afficheca.data.entity.mapper.EventEntityDataMapper;
import com.yavin.afficheca.data.repository.datasource.EventDataStore;
import com.yavin.afficheca.data.repository.datasource.EventDataStoreFactory;
import com.yavin.afficheca.domain.Event;
import com.yavin.afficheca.domain.repository.EventRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link EventRepository} for retrieving event data.
 */
@Singleton
public class EventDataRepository implements EventRepository{

    private final EventDataStoreFactory eventDataStoreFactory;
    private final EventEntityDataMapper eventEntityDataMapper;

    /**
     * Constructs a {@link EventRepository}.
     *
     * @param dataStoreFactory     A factory to construct different data source implementations.
     * @param eventEntityDataMapper {@link EventEntityDataMapper}.
     */
    @Inject
    EventDataRepository(EventDataStoreFactory dataStoreFactory,
                       EventEntityDataMapper eventEntityDataMapper) {
        this.eventDataStoreFactory = dataStoreFactory;
        this.eventEntityDataMapper = eventEntityDataMapper;
    }

    @Override
    public Observable<List<Event>> events() {
        final EventDataStore eventDataStore = this.eventDataStoreFactory.createCloudDataStore();
        return eventDataStore.eventEntityList()
                .map(this.eventEntityDataMapper::transform);
    }

    @Override
    public Observable<Event> event(String eventId) {
        final EventDataStore eventDataStore = this.eventDataStoreFactory.create(eventId);
        return eventDataStore.eventEntityDetails(eventId)
                .map(this.eventEntityDataMapper::transform);
    }
}
