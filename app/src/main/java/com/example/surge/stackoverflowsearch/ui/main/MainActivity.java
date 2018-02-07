package com.example.surge.stackoverflowsearch.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.surge.stackoverflowsearch.R;
import com.example.surge.stackoverflowsearch.data.model.QuestionContainer;
import com.example.surge.stackoverflowsearch.ui.main.adapter.QuestionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static final long ANIM_RECYCLERVIEW_DELAY = 8000;

    @BindView(R.id.search_bar)
    EditText searchBar;
    @BindView(R.id.no_results_label)
    TextView noResultsLabel;
    @BindView(R.id.questions_view)
    RecyclerView questionsView;

    MainViewModel model;
    private QuestionAdapter questionsAdapter;
    private Handler handler;
    private Runnable animateRecyclerviewRunnable;
    private int viewPosition = 0;
    boolean stopSliding = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        questionsAdapter = new QuestionAdapter(this);

        questionsView.setLayoutManager(linearLayoutManager);
        questionsView.setAdapter(questionsAdapter);

        questionsView.setOnTouchListener(this::onTouch);

        model = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();

        initCarouselAnimation();
    }

    @OnClick(R.id.search_enter)
    public void onSearchClick() {
        model.getQuestions(searchBar.getEditableText().toString())
                .subscribe(this::refreshQuestions);
    }

    private void refreshQuestions(QuestionContainer container) {
        Timber.d("Question Count: " + container.items.size());
        noResultsLabel.setVisibility(container.items.size() == 0 ? View.VISIBLE : View.GONE);
        questionsAdapter.setQuestions(container.items);
    }

    private boolean onTouch(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {

            case MotionEvent.ACTION_CANCEL:
                break;

            case MotionEvent.ACTION_UP:
                // calls when touch release on ViewPager
                stopSliding = false;
                animateRecyclerviewRunnable();
                break;

            case MotionEvent.ACTION_MOVE:
                // calls when ViewPager touch
                if (handler != null && !stopSliding) {
                    stopSliding = true;
                    handler.removeCallbacks(animateRecyclerviewRunnable);
                }
                break;
        }
        return false;
    }

    public void initCarouselAnimation() {
        if (handler == null) {
            animateRecyclerviewRunnable();
        }
    }

    public void animateRecyclerviewRunnable() {
        handler = new Handler();

        animateRecyclerviewRunnable = () -> {
            if (!stopSliding) {
                viewPosition = viewPosition == questionsAdapter.getItemCount() - 1 ? 0 : viewPosition + 1;

                questionsView.smoothScrollToPosition(viewPosition);
                handler.postDelayed(animateRecyclerviewRunnable, ANIM_RECYCLERVIEW_DELAY);
            }
        };

        handler.postDelayed(animateRecyclerviewRunnable,
                ANIM_RECYCLERVIEW_DELAY);
    }
}
