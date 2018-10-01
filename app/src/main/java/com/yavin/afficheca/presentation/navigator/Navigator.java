package com.yavin.afficheca.presentation.navigator;

import android.content.Context;
import android.content.Intent;

import com.yavin.afficheca.presentation.view.activity.EventDetailsActivity;
import com.yavin.afficheca.presentation.view.activity.EventListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    /**
     * Goes to the event list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToEventList(Context context) {
        if (context != null) {
            Intent intentToLaunch = EventListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the event details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToEventDetails(Context context, String eventId) {
        if (context != null) {
            Intent intentToLaunch = EventDetailsActivity.getCallingIntent(context, eventId);
            context.startActivity(intentToLaunch);
        }
    }
}
