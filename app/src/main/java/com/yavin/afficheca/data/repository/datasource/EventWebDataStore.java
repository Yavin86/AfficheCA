package com.yavin.afficheca.data.repository.datasource;

import com.yavin.afficheca.data.cache.EventCache;
import com.yavin.afficheca.data.entity.EventEntity;
import com.yavin.afficheca.data.net.RestApi;
import com.yavin.afficheca.domain.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;

/**
 * {@link EventDataStore} implementation based on connections to the api (Cloud).
 */
public class EventWebDataStore implements EventDataStore {

    private final RestApi restApi;
    private final EventCache eventCache;

    /**
     * Construct a {@link EventDataStore} based on connections to the api (Cloud).
     *
     * @param restApi    The {@link RestApi} implementation to use.
     * @param eventCache A {@link EventCache} to cache data retrieved from the api.
     */
    EventWebDataStore(RestApi restApi, EventCache eventCache) {
        this.restApi = restApi;
        this.eventCache = eventCache;
    }

    @Override
    public Observable<List<EventEntity>> eventEntityList() {
        return restApi.eventEntityList();
    }

    @Override // TODO cache
    public Observable<EventEntity> eventEntityDetails(final String eventId) {
//        return this.restApi.eventEntityById(eventId).doOnNext(EventWebDataStore.this.eventCache::put);
        return this.eventCache.get(eventId);
    }
}
