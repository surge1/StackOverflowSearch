package com.example.surge.stackoverflowsearch.app.module;

import com.example.surge.stackoverflowsearch.data.RestClient;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by surge on 2/6/18.
 */

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return new RestClient();
    }
}