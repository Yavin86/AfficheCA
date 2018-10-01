package com.yavin.afficheca.domain.interactor;

import com.yavin.afficheca.domain.Event;
import com.yavin.afficheca.domain.executor.PostExecutionThread;
import com.yavin.afficheca.domain.executor.ThreadExecutor;
import com.yavin.afficheca.domain.repository.EventRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Event}.
 */
public class GetEventList extends UseCase<List<Event>, Void> {

    private final EventRepository eventRepository;

    @Inject
    GetEventList(EventRepository eventRepository, ThreadExecutor threadExecutor,
                PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.eventRepository = eventRepository;
    }

    @Override
    Observable<List<Event>> buildUseCaseObservable(Void unused) {
        return this.eventRepository.events();
    }
}
