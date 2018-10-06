package com.yavin.afficheca.presentation.view.fragment;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.yavin.afficheca.R;
import com.yavin.afficheca.presentation.di.components.EventComponent;
import com.yavin.afficheca.presentation.model.EventModel;
import com.yavin.afficheca.presentation.presenter.EventListPresenter;
import com.yavin.afficheca.presentation.view.EventListView;
import com.yavin.afficheca.presentation.view.adapter.EventsAdapter;
import com.yavin.afficheca.presentation.view.adapter.EventsLayoutManager;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class EventListFragment extends BaseFragment implements EventListView {
    /**
     * Interface for listening event list events.
     */
    public interface EventListListener {
        void onEventClicked(final EventModel EventModel);
    }

    @Inject
    EventListPresenter eventListPresenter;
    @Inject
    EventsAdapter eventsAdapter;

    @BindView(R.id.rv_events)
    RecyclerView rv_events;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.rl_retry)
    RelativeLayout rl_retry;
    @BindView(R.id.bt_retry)
    Button bt_retry;

    private Unbinder unbinder;

    private EventListListener eventListListener;

    public EventListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof EventListListener) {
            this.eventListListener = (EventListListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(EventComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_event_list, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setupRecyclerView();

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.eventListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadEventList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.eventListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.eventListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_events.setAdapter(null);
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.eventListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.eventListListener = null;
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void renderEventList(Collection<EventModel> eventModelCollection) {
        if (eventModelCollection != null) {
            this.eventsAdapter.setEventsCollection(eventModelCollection);
        }
    }

    @Override
    public void viewEvent(EventModel eventModel) {
        if (this.eventListListener != null) {
            this.eventListListener.onEventClicked(eventModel);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        this.eventsAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_events.setLayoutManager(new EventsLayoutManager(context()));
        this.rv_events.setAdapter(eventsAdapter);
    }

    /**
     * Loads all events.
     */
    private void loadEventList() {
        this.eventListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        EventListFragment.this.loadEventList();
    }

    private EventsAdapter.OnItemClickListener onItemClickListener =
            new EventsAdapter.OnItemClickListener() {
                @Override
                public void onEventItemClicked(EventModel eventModel) {
                    if (EventListFragment.this.eventListPresenter != null && eventModel != null) {
                        EventListFragment.this.eventListPresenter.onEventClicked(eventModel);
                    }
                }
            };
}
