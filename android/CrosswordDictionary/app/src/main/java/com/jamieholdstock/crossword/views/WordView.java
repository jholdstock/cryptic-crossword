package com.jamieholdstock.crossword.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jamieholdstock.crossword.IndicatorType;
import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.Word;
import com.jamieholdstock.crossword.activities.tutorial.devices.CharadeActivity;

public class WordView extends LinearLayout {

    private Activity activity;

    public WordView(Context context, Activity activity) {
        super(context);
        this.activity = activity;
        LayoutInflater.from(context).inflate(R.layout.view_word, this);
    }

    public void displayWord(Word word) {
        TextView txtTitle = (TextView) findViewById(R.id.the_word);

        txtTitle.setText(word.getWord());

        LinearLayout indicatorContainer = (LinearLayout) findViewById(R.id.indicators_panel);
        indicatorContainer.removeAllViews();
        if (word.hasIndicators()) {
            for (final IndicatorType type : word.getIndicators()) {
                IndicatorView indicatorView = new IndicatorView(indicatorContainer.getContext());
                indicatorView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), type.getActivityToLaunch());
                        v.getContext().startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });
                indicatorView.setMainText(type.getDisplayName());
                indicatorView.setHeader("Could indicate:");
                indicatorContainer.addView(indicatorView);
            }
        }

        for (String charade : word.getCharades()) {
            IndicatorView charadeView = new IndicatorView(indicatorContainer.getContext());
            charadeView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CharadeActivity.class);
                    v.getContext().startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });
            charadeView.setMainText(charade);
            charadeView.setHeader("Charade:");
            indicatorContainer.addView(charadeView);
        }

    }
}
