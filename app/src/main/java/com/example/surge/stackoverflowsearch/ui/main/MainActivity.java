package com.example.surge.stackoverflowsearch.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.surge.stackoverflowsearch.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        model = ViewModelProviders.of(this).get(MainViewModel.class);
    }
}
