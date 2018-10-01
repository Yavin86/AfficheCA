package com.yavin.afficheca.data.net;

import com.yavin.afficheca.data.entity.EventEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
    /**
     * Retrieves an {@link Observable} which will emit a List of {@link EventEntity}.
     */
    Observable<List<EventEntity>> eventEntityList(); // TODO return some object

}
