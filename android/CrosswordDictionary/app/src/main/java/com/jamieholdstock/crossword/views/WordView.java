package com.jamieholdstock.crossword.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jamieholdstock.crossword.IndicatorType;
import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.Word;

public class WordView extends LinearLayout {

    public WordView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_word, this);
    }

    public void displayWord(Word word) {
        TextView txtTitle = (TextView) findViewById(R.id.the_word);

        txtTitle.setText(word.getWord());

        LinearLayout indicatorContainer = (LinearLayout) findViewById(R.id.indicators_panel);
        indicatorContainer.removeAllViews();
        if (word.hasIndicators()) {
            for (IndicatorType type : word.getIndicators()) {
                IndicatorView indicatorView = new IndicatorView(indicatorContainer.getContext());
                indicatorView.setMainText(type.getDisplayName());
                indicatorView.setHeader("Could indicate:");
                indicatorView.setIndicatorType(type);
                indicatorContainer.addView(indicatorView);
            }
        }

        for (String charade : word.getCharades()) {
            IndicatorView charadeView = new IndicatorView(indicatorContainer.getContext());
            charadeView.setMainText(charade);
            charadeView.setHeader("Charade:");
            indicatorContainer.addView(charadeView);
        }

    }
}
