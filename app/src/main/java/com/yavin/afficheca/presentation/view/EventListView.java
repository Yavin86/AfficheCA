package com.yavin.afficheca.presentation.view;

import com.yavin.afficheca.presentation.model.EventModel;

import java.util.Collection;

public interface EventListView extends LoadDataView {
    /**
     * Render a event list in the UI.
     *
     * @param eventModelCollection The collection of {@link EventModel} that will be shown.
     */
    void renderEventList(Collection<EventModel> eventModelCollection);

    /**
     * View a {@link EventModel} profile/details.
     *
     * @param eventModel The event that will be shown.
     */
    void viewEvent(EventModel eventModel);
}
