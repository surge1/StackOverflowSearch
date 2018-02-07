package com.example.surge.stackoverflowsearch.app.component;

import android.content.res.Resources;

import com.example.surge.stackoverflowsearch.app.App;
import com.example.surge.stackoverflowsearch.app.module.AppModule;
import com.example.surge.stackoverflowsearch.app.module.NetworkModule;
import com.example.surge.stackoverflowsearch.data.RestClient;
import com.example.surge.stackoverflowsearch.ui.main.MainViewModel;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by surge on 2/6/18.
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)
public interface AppComponent {
    void inject(App app);

    void inject(MainViewModel mainViewModel);

    App getApp();

    Resources getResources();

    Gson getGson();

    RestClient getRestClient();
}
