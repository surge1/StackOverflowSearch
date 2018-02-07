package com.example.surge.stackoverflowsearch.data;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by surge on 2/6/18.
 */

public class RestClient {
    public static final int READ_TIMEOUT = 10;
    public static final int CONNECT_TIMEOUT = 10;

    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public Retrofit getRetrofit() {
        return createRestAdapter(getGsonConverter(), getOkClient());
    }

    private Retrofit createRestAdapter(GsonConverterFactory gsonConverter, OkHttpClient okClient) {
        return new Retrofit.Builder()
                .client(okClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverter)
                .baseUrl(BASE_URL)
                .build();
    }

    private GsonConverterFactory getGsonConverter() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        return GsonConverterFactory.create(gson);
    }

    private OkHttpClient getOkClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }
}
