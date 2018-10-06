package com.yavin.afficheca.data.cache;

import com.yavin.afficheca.data.entity.EventEntity;

import java.util.List;

import io.reactivex.Observable;


/**
 * An interface representing a event Cache.
 */
public interface EventCache {
    /**
     * Gets an {@link Observable} which will emit a {@link EventEntity}.
     *
     * @param eventId The event id to retrieve data.
     */
    Observable<EventEntity> get(final String eventId);

    /**
     * Puts and element into the cache.
     *
     * @param eventEntity Element to insert in the cache.
     */
    void put(EventEntity eventEntity);

    void putAll(List<EventEntity> eventEntities);

    /**
     * Checks if an element (Event) exists in the cache.
     *
     * @param eventId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final String eventId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}