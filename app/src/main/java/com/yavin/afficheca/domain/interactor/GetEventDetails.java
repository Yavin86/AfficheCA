package com.yavin.afficheca.domain.interactor;

import com.yavin.afficheca.domain.Event;
import com.yavin.afficheca.domain.executor.PostExecutionThread;
import com.yavin.afficheca.domain.executor.ThreadExecutor;
import com.yavin.afficheca.domain.repository.EventRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Event}.
 */
public class GetEventDetails extends UseCase<Event, GetEventDetails.Params> {

    private final EventRepository eventRepository;

    @Inject
    GetEventDetails(EventRepository eventRepository, ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.eventRepository = eventRepository;
    }

    @Override
    Observable<Event> buildUseCaseObservable(Params params) {
        return this.eventRepository.event(params.eventId);
    }

    public static final class Params {

        private final String eventId;

        private Params(String eventId) {
            this.eventId = eventId;
        }

        public static Params forEvent(String eventId) {
            return new Params(eventId);
        }
    }
}