package com.yavin.afficheca.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yavin.afficheca.R;
import com.yavin.afficheca.presentation.di.components.EventComponent;
import com.yavin.afficheca.presentation.model.EventModel;
import com.yavin.afficheca.presentation.presenter.EventDetailsPresenter;
import com.yavin.afficheca.presentation.view.EventDetailsView;
import com.yavin.afficheca.presentation.view.component.AutoLoadImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Fragment that shows details of a certain event.
 */
public class EventDetailsFragment extends BaseFragment implements EventDetailsView {

    private static final String PARAM_EVENT_ID = "param_event_id";

    @Inject
    EventDetailsPresenter eventDetailsPresenter;

    @BindView(R.id.iv_image)
    AutoLoadImageView iv_image;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_detail)
    TextView tv_detail;
    @BindView(R.id.tv_description)
    TextView tv_description;

    private Unbinder unbinder;

    public static EventDetailsFragment forEvent(String eventId) {
        final EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(PARAM_EVENT_ID, eventId);
        eventDetailsFragment.setArguments(arguments);
        return eventDetailsFragment;
    }

    public EventDetailsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(EventComponent.class).inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_event_details, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.eventDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadEventDetails();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.eventDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.eventDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.eventDetailsPresenter.destroy();
    }

    @Override
    public void renderEvent(EventModel event) {
        if (event != null) {
            if (event.getImageLinks() != null) {
                this.iv_image.setImageUrl(event.getImageLinks().get(0));
            }
            this.tv_title.setText(event.getTitle());
            this.tv_detail.setText(event.getDetail());
            this.tv_detail.setVisibility(event.getDetail().isEmpty() ? View.GONE : View.VISIBLE);
            this.tv_description.setText(event.getDescription());
            this.tv_description.setVisibility(event.getDescription().isEmpty() ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void showLoading() {
//        this.rl_progress.setVisibility(View.VISIBLE); // TODO remove this
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
//        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
//        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
//        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    /**
     * Load event details.
     */
    private void loadEventDetails() {
        if (this.eventDetailsPresenter != null) {
            this.eventDetailsPresenter.initialize(currentEventId());
        }
    }

    /**
     * Get current event id from fragments arguments.
     */
    private String currentEventId() {
        final Bundle arguments = getArguments();
//        Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
        return arguments.getString(PARAM_EVENT_ID);
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        EventDetailsFragment.this.loadEventDetails();
    }

}

