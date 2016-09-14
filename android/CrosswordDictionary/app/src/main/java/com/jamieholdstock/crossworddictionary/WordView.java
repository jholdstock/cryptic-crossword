package com.jamieholdstock.crossworddictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WordView extends LinearLayout {

    public WordView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_word, this);

    }

    public void displayWord(Word word) {
        TextView txtTitle = (TextView) findViewById(R.id.title);

        txtTitle.setText(word.getWord());

        LinearLayout indicatorContainer = (LinearLayout) findViewById(R.id.indicators_panel);
        indicatorContainer.removeAllViews();
        if (word.hasIndicators()) {
            for (String indicator : word.getIndicators()) {
                IndicatorView indicatorView = new IndicatorView(indicatorContainer.getContext());
                indicatorView.setMainText(indicator);
                indicatorView.setHeader("May Indicate");
                indicatorContainer.addView(indicatorView);
            }
        }

        if (word.hasAbbreviations())
        {
            IndicatorView abbrView = new IndicatorView(indicatorContainer.getContext());
            abbrView.setMainText(word.getAbbreviations());
            abbrView.setHeader("Abbreviation:");
            indicatorContainer.addView(abbrView);
        }

    }
}
