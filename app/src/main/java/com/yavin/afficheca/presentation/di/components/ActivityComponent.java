package com.yavin.afficheca.presentation.di.components;

import android.app.Activity;

import com.yavin.afficheca.presentation.di.PerActivity;
import com.yavin.afficheca.presentation.di.module.ActivityModule;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link com.yavin.afficheca.presentation.di.PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}