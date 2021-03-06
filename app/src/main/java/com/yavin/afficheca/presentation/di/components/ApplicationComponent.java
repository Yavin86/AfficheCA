package com.yavin.afficheca.presentation.di.components;

import android.content.Context;

import com.yavin.afficheca.domain.executor.PostExecutionThread;
import com.yavin.afficheca.domain.executor.ThreadExecutor;
import com.yavin.afficheca.domain.repository.EventRepository;
import com.yavin.afficheca.presentation.view.activity.BaseActivity;
import com.yavin.afficheca.presentation.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    EventRepository eventRepository();
}
