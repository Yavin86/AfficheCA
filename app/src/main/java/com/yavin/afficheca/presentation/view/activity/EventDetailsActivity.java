package com.yavin.afficheca.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.yavin.afficheca.R;
import com.yavin.afficheca.presentation.di.HasComponent;
import com.yavin.afficheca.presentation.di.components.DaggerEventComponent;
import com.yavin.afficheca.presentation.di.components.EventComponent;
import com.yavin.afficheca.presentation.view.fragment.EventDetailsFragment;

public class EventDetailsActivity extends BaseActivity implements HasComponent<EventComponent> {

    private static final String INTENT_EXTRA_PARAM_EVENT_ID = "com.yavin.INTENT_PARAM_EVENT_ID";
    private static final String INSTANCE_STATE_PARAM_EVENT_ID = "com.yavin.STATE_PARAM_EVENT_ID";

    public static Intent getCallingIntent(Context context, String eventId) {
        Intent callingIntent = new Intent(context, EventDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_EVENT_ID, eventId);
        return callingIntent;
    }

    private String eventId;
    private EventComponent eventComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INSTANCE_STATE_PARAM_EVENT_ID, this.eventId);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            this.eventId = getIntent().getStringExtra(INTENT_EXTRA_PARAM_EVENT_ID);
            addFragment(R.id.fragmentContainer, EventDetailsFragment.forEvent(eventId));
        } else {
            this.eventId = savedInstanceState.getString(INSTANCE_STATE_PARAM_EVENT_ID);
        }
    }

    private void initializeInjector() {
        this.eventComponent = DaggerEventComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public EventComponent getComponent() {
        return eventComponent;
    }
}
