package com.example.surge.stackoverflowsearch.app.module;

import android.content.res.Resources;

import com.example.surge.stackoverflowsearch.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by surge on 2/6/18.
 */

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        app = app;
    }

    @Provides
    @Singleton
    public App provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public Resources provideResources() {
        return app.getResources();
    }
}
