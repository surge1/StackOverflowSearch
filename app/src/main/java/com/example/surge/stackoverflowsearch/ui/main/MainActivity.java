package com.example.surge.stackoverflowsearch.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.surge.stackoverflowsearch.R;
import com.example.surge.stackoverflowsearch.data.model.QuestionContainer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.search_bar)
    EditText searchBar;

    MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        model = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @OnClick(R.id.search_enter)
    public void onSearchClick() {
        model.getQuestions(searchBar.getEditableText().toString())
                .subscribe(this::refreshQuestions);
    }

    private void refreshQuestions(QuestionContainer container) {
        Timber.d("Question Count: " + container.items.size());
    }
}
