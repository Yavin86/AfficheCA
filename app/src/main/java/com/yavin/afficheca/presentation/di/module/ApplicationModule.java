package com.yavin.afficheca.presentation.di.module;

import android.content.Context;

import com.yavin.afficheca.data.cache.EventCache;
import com.yavin.afficheca.data.cache.EventCacheImpl;
import com.yavin.afficheca.data.executor.JobExecutor;
import com.yavin.afficheca.data.repository.EventDataRepository;
import com.yavin.afficheca.domain.executor.PostExecutionThread;
import com.yavin.afficheca.domain.executor.ThreadExecutor;
import com.yavin.afficheca.domain.repository.EventRepository;
import com.yavin.afficheca.presentation.AfficheCaApplication;
import com.yavin.afficheca.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AfficheCaApplication application;

    public ApplicationModule(AfficheCaApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    EventCache provideEventCache(EventCacheImpl eventCache) {
        return eventCache;
    }

    @Provides
    @Singleton
    EventRepository provideEventRepository(EventDataRepository eventDataRepository) {
        return eventDataRepository;
    }
}
