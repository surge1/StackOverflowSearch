package com.example.surge.stackoverflowsearch.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.surge.stackoverflowsearch.R;
import com.example.surge.stackoverflowsearch.data.model.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by surge on 2/6/18.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Question> questions = new ArrayList<>();
    private Context context;

    public QuestionAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.view_question_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question question = questions.get(position);

        Glide.with(context)
                .load(question.owner.profileImage)
                .into(holder.profileImage);

        holder.arrow.setRotation(0);
        holder.detailsView.setVisibility(View.GONE);
        holder.title.setText(question.title);
        holder.author.setText(question.owner.displayName);
        holder.viewCount.setText(Integer.toString(question.viewCount));
        holder.answerCount.setText(Integer.toString(question.answerCount));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;

        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profile_image)
        ImageView profileImage;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.question_container)
        LinearLayout questionContainer;

        @BindView(R.id.arrow)
        ImageView arrow;

        @BindView(R.id.details_view)
        RelativeLayout detailsView;

        @BindView(R.id.author)
        TextView author;

        @BindView(R.id.view_count)
        TextView viewCount;

        @BindView(R.id.answer_count)
        TextView answerCount;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            questionContainer.setOnClickListener(click -> onCardClick());
        }

        private void onCardClick() {
            arrow.animate().rotation(detailsView.getVisibility() == View.GONE ? 180 : 0);
            detailsView.setVisibility(detailsView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        }
    }
}
