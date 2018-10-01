package com.yavin.afficheca.data.repository.datasource;

import com.yavin.afficheca.data.cache.EventCache;
import com.yavin.afficheca.data.entity.EventEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link EventDataStore} implementation based on file system data store.
 */
public class EventLocalDataStore implements EventDataStore {
    private final EventCache eventCache;

    /**
     * Construct a {@link EventDataStore} based file system data store.
     *
     * @param eventCache A {@link EventCache} to cache data retrieved from the api.
     */
    EventLocalDataStore(EventCache eventCache) {
        this.eventCache = eventCache;
    }

    @Override
    public Observable<List<EventEntity>> eventEntityList() {
        //TODO: implement simple cache (e.g. room) for storing/retrieving collections of events.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<EventEntity> eventEntityDetails(final String eventId) {
        return this.eventCache.get(eventId);
    }
}
