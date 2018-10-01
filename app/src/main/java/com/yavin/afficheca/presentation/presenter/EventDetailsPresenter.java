package com.yavin.afficheca.presentation.presenter;

import android.support.annotation.NonNull;

import com.yavin.afficheca.domain.Event;
import com.yavin.afficheca.domain.exception.DefaultErrorBundle;
import com.yavin.afficheca.domain.exception.ErrorBundle;
import com.yavin.afficheca.domain.interactor.DefaultObserver;
import com.yavin.afficheca.domain.interactor.GetEventDetails;
import com.yavin.afficheca.domain.interactor.GetEventDetails.Params;
import com.yavin.afficheca.presentation.exception.ErrorMessageFactory;
import com.yavin.afficheca.presentation.mapper.EventModelDataMapper;
import com.yavin.afficheca.presentation.model.EventModel;
import com.yavin.afficheca.presentation.view.EventDetailsView;

import javax.inject.Inject;

public class EventDetailsPresenter implements Presenter {

    private EventDetailsView viewDetailsView;

    private final GetEventDetails getEventDetailsUseCase;
    private final EventModelDataMapper eventModelDataMapper;

    @Inject
    public EventDetailsPresenter(GetEventDetails getEventDetailsUseCase,
                                 EventModelDataMapper eventModelDataMapper) {
        this.getEventDetailsUseCase = getEventDetailsUseCase;
        this.eventModelDataMapper = eventModelDataMapper;
    }

    public void setView(@NonNull EventDetailsView view) {
        this.viewDetailsView = view;
    }

    @Override public void resume() {}

    @Override public void pause() {}

    @Override public void destroy() {
        this.getEventDetailsUseCase.dispose();
        this.viewDetailsView = null;
    }

    /**
     * Initializes the presenter by showing/hiding proper views
     * and retrieving event details.
     */
    public void initialize(String eventId) {
        this.hideViewRetry();
        this.showViewLoading();
        this.getEventDetails(eventId);
    }

    private void getEventDetails(String eventId) {
        this.getEventDetailsUseCase.execute(new EventDetailsObserver(), Params.forEvent(eventId));
    }

    private void showViewLoading() {
        this.viewDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.viewDetailsView.hideLoading();
    }

    private void showViewRetry() {
        this.viewDetailsView.showRetry();
    }

    private void hideViewRetry() {
        this.viewDetailsView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.context(),
                errorBundle.getException());
        this.viewDetailsView.showError(errorMessage);
    }

    private void showEventDetailsInView(Event event) {
        final EventModel eventModel = this.eventModelDataMapper.transform(event);
        this.viewDetailsView.renderEvent(eventModel);
    }

    private final class EventDetailsObserver extends DefaultObserver<Event> {

        @Override public void onComplete() {
            EventDetailsPresenter.this.hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            EventDetailsPresenter.this.hideViewLoading();
            EventDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            EventDetailsPresenter.this.showViewRetry();
        }

        @Override public void onNext(Event event) {
            EventDetailsPresenter.this.showEventDetailsInView(event);
        }
    }
}
