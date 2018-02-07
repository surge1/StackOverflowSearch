package com.example.surge.stackoverflowsearch.data.manager;

import com.example.surge.stackoverflowsearch.data.api.StackoverflowApiService;
import com.example.surge.stackoverflowsearch.data.model.QuestionContainer;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by surge on 2/6/18.
 */

public class StackoverflowManager {
    private final StackoverflowApiService stackoverflowApiService;

    public StackoverflowManager(Retrofit retrofit) {
        stackoverflowApiService = retrofit.create(StackoverflowApiService.class);
    }

    public Observable<QuestionContainer> getQuestions(String title) {
        // we don't care about backpressure since we'll only display 15 tweets at a time for this
        return stackoverflowApiService.getQuestions(title)
                .toObservable();
    }
}
