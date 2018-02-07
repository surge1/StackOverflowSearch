package com.example.surge.stackoverflowsearch.ui.main;

import android.arch.lifecycle.ViewModel;

import com.example.surge.stackoverflowsearch.app.App;
import com.example.surge.stackoverflowsearch.data.manager.StackoverflowManager;
import com.example.surge.stackoverflowsearch.data.model.QuestionContainer;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by surge on 2/6/18.
 */

public class MainViewModel extends ViewModel{

    //TODO: user constructor injection instead of this
    @Inject
    StackoverflowManager manager;

    public MainViewModel() {
        App.get().getAppComponent().inject(this);
    }

    public Observable<QuestionContainer> getQuestions(String title) {
        return manager.getQuestions(title)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturnItem(new QuestionContainer());
    }
}
