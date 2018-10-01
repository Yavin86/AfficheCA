package com.yavin.afficheca.presentation.presenter;

import android.support.annotation.NonNull;

import com.yavin.afficheca.domain.Event;
import com.yavin.afficheca.domain.exception.DefaultErrorBundle;
import com.yavin.afficheca.domain.exception.ErrorBundle;
import com.yavin.afficheca.domain.interactor.DefaultObserver;
import com.yavin.afficheca.domain.interactor.GetEventList;
import com.yavin.afficheca.presentation.di.PerActivity;
import com.yavin.afficheca.presentation.exception.ErrorMessageFactory;
import com.yavin.afficheca.presentation.mapper.EventModelDataMapper;
import com.yavin.afficheca.presentation.model.EventModel;
import com.yavin.afficheca.presentation.view.EventListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class EventListPresenter implements Presenter {

    private EventListView viewListView;

    private final GetEventList getEventListUseCase;
    private final EventModelDataMapper eventModelDataMapper;

    @Inject
    public EventListPresenter(GetEventList getEventListEventCase,
                             EventModelDataMapper eventModelDataMapper) {
        this.getEventListUseCase = getEventListEventCase;
        this.eventModelDataMapper = eventModelDataMapper;
    }

    public void setView(@NonNull EventListView view) {
        this.viewListView = view;
    }

    @Override public void resume() {}

    @Override public void pause() {}

    @Override public void destroy() {
        this.getEventListUseCase.dispose();
        this.viewListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the event list.
     */
    public void initialize() {
        this.loadEventList();
    }

    /**
     * Loads all events.
     */
    private void loadEventList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getEventList();
    }

    public void onEventClicked(EventModel eventModel) {
        this.viewListView.viewEvent(eventModel);
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
                errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showEventsCollectionInView(Collection<Event> eventsCollection) {
        final Collection<EventModel> eventModelsCollection =
                this.eventModelDataMapper.transform(eventsCollection);
        this.viewListView.renderEventList(eventModelsCollection);
    }

    private void getEventList() {
        this.getEventListUseCase.execute(new EventListObserver(), null);
    }

    private final class EventListObserver extends DefaultObserver<List<Event>> {

        @Override public void onComplete() {
            EventListPresenter.this.hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            EventListPresenter.this.hideViewLoading();
            EventListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            EventListPresenter.this.showViewRetry();
        }

        @Override public void onNext(List<Event> events) {
            EventListPresenter.this.showEventsCollectionInView(events);
        }
    }
}
