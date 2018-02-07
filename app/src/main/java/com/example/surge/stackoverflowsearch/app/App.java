package com.example.surge.stackoverflowsearch.app;

import android.app.Application;

import com.example.surge.stackoverflowsearch.app.component.AppComponent;
import com.example.surge.stackoverflowsearch.app.component.DaggerAppComponent;
import com.example.surge.stackoverflowsearch.app.module.AppModule;
import com.example.surge.stackoverflowsearch.app.module.NetworkModule;

/**
 * Created by surge on 2/6/18.
 */

public class App extends Application {

    private static App app;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        app = this;
        super.onCreate();

        setupGraph();
    }

    void setupGraph() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(getAppModule())
                    .networkModule(new NetworkModule())
                    .build();
        }

        appComponent.inject(this);
    }

    public static App get() {
        return app;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppModule getAppModule() {
        return new AppModule(this);
    }
}
