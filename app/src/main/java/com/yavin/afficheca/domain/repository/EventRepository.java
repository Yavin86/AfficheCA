package com.yavin.afficheca.domain.repository;

import com.yavin.afficheca.domain.Event;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link Event} related data.
 */

public interface EventRepository {
    /**
     * Get an {@link Observable} which will emit a List of {@link Event}.
     */
    Observable<List<Event>> events();

    /**
     * Get an {@link Observable} which will emit a {@link Event}.
     *
     * @param eventId The event id used to retrieve event data.
     */
    Observable<Event> event(final String eventId);
}
