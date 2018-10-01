package com.yavin.afficheca.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yavin.afficheca.data.cache.EventCache;
import com.yavin.afficheca.data.entity.mapper.EventEntityXMLMapper;
import com.yavin.afficheca.data.net.RestApi;
import com.yavin.afficheca.data.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link EventDataStore}.
 */
@Singleton
public class EventDataStoreFactory {
    
    private final Context context;
    private final EventCache eventCache;

    @Inject
    EventDataStoreFactory(@NonNull Context context, @NonNull EventCache eventCache) {
        this.context = context.getApplicationContext();
        this.eventCache = eventCache;
    }

    /**
     * Create {@link EventDataStore} from a event id.
     */
    public EventDataStore create(String eventId) {
        EventDataStore eventDataStore;

        if (!this.eventCache.isExpired() && this.eventCache.isCached(eventId)) {
            eventDataStore = new EventLocalDataStore(this.eventCache);
        } else {
            eventDataStore = createCloudDataStore();
        }

        return eventDataStore;
    }

    /**
     * Create {@link EventDataStore} to retrieve data from the Cloud.
     */
    public EventDataStore createCloudDataStore() {
        final EventEntityXMLMapper eventEntityXMLMapper = new EventEntityXMLMapper();
        final RestApi restApi = new RestApiImpl(this.context, eventEntityXMLMapper);
        return new EventWebDataStore(restApi, this.eventCache);
    }
}