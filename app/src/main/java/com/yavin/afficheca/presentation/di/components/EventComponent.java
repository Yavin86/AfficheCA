package com.yavin.afficheca.presentation.di.components;

import com.yavin.afficheca.presentation.di.PerActivity;
import com.yavin.afficheca.presentation.di.module.ActivityModule;
import com.yavin.afficheca.presentation.di.module.EventModule;
import com.yavin.afficheca.presentation.view.fragment.EventDetailsFragment;
import com.yavin.afficheca.presentation.view.fragment.EventListFragment;

import dagger.Component;

/**
 * A scope {@link com.yavin.afficheca.presentation.di.PerActivity} component.
 * Injects event specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, EventModule.class})
public interface EventComponent extends ActivityComponent {
    void inject(EventListFragment eventListFragment);
    void inject(EventDetailsFragment eventDetailsFragment);
}
