package com.yavin.afficheca.presentation.view;

import com.yavin.afficheca.presentation.model.EventModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a event.
 */
public interface EventDetailsView extends LoadDataView {
    /**
     * Render a event in the UI.
     *
     * @param eventModel The {@link EventModel} that will be shown.
     */
    void renderEvent(EventModel eventModel);
}

