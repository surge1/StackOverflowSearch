package com.example.surge.stackoverflowsearch.data.api;

import com.example.surge.stackoverflowsearch.data.model.QuestionContainer;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by surge on 2/6/18.
 */

public interface StackoverflowApiService {

    @GET("search?page=1&pagesize=15&order=desc&sort=activity&site=stackoverflow")
    Flowable<QuestionContainer> getQuestions(@Query("intitle") String title);

}
