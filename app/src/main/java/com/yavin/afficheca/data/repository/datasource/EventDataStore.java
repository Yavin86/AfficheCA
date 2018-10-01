package com.yavin.afficheca.data.repository.datasource;

import com.yavin.afficheca.data.entity.EventEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface EventDataStore {
    /**
     * Get an {@link Observable} which will emit a List of {@link EventEntity}.
     */
    Observable<List<EventEntity>> eventEntityList();

    /**
     * Get an {@link Observable} which will emit a {@link EventEntity} by its id.
     *
     * @param eventId The id to retrieve event data.
     */
    Observable<EventEntity> eventEntityDetails(final String eventId);
}