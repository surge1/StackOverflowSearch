package com.example.surge.stackoverflowsearch.data.api;

import com.example.surge.stackoverflowsearch.data.model.QuestionContainer;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by surge on 2/6/18.
 */

public interface StackoverflowApiService {

    @GET("search?order=desc&sort=activity&intitle={title}&site=stackoverflow")
    Flowable<QuestionContainer> getQuestions(@Path("title") String title);

}
