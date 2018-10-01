package com.yavin.afficheca.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.yavin.afficheca.R;
import com.yavin.afficheca.presentation.di.HasComponent;
import com.yavin.afficheca.presentation.di.components.DaggerEventComponent;
import com.yavin.afficheca.presentation.di.components.EventComponent;
import com.yavin.afficheca.presentation.model.EventModel;
import com.yavin.afficheca.presentation.view.fragment.EventListFragment;

public class EventListActivity extends BaseActivity implements HasComponent<EventComponent>,
        EventListFragment.EventListListener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, EventListActivity.class);
    }

    private EventComponent eventComponent;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new EventListFragment());
        }
    }

    private void initializeInjector() {
        this.eventComponent = DaggerEventComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override public EventComponent getComponent() {
        return eventComponent;
    }

    @Override public void onEventClicked(EventModel eventModel) {
        this.navigator.navigateToEventDetails(this, eventModel.getId());
    }
}
