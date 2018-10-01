package com.yavin.afficheca.presentation;

import android.app.Application;

import com.yavin.afficheca.presentation.di.components.ApplicationComponent;
import com.yavin.afficheca.presentation.di.components.DaggerApplicationComponent;
import com.yavin.afficheca.presentation.di.module.ApplicationModule;

public class AfficheCaApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

//    private void initializeLeakDetection() {
//        if (BuildConfig.DEBUG) {
//            LeakCanary.install(this);
//        }
//    }
}
