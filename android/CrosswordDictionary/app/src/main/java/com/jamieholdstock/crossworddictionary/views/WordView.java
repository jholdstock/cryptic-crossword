package com.jamieholdstock.crossworddictionary.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jamieholdstock.crossworddictionary.R;
import com.jamieholdstock.crossworddictionary.Word;

public class WordView extends LinearLayout {

    public WordView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_word, this);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("jamie.log", "Clicked");
                View indicators = view.findViewById(R.id.indicators_panel);
                if (indicators.getVisibility() == VISIBLE) {
                    Log.e("jamie.log", "Setting gone");
                    indicators.setVisibility(GONE);
                }
                else {
                    Log.e("jamie.log", "Setting visible");
                    indicators.setVisibility(VISIBLE);
                }
            }
        });
    }

    public void displayWord(Word word) {
        TextView txtTitle = (TextView) findViewById(R.id.the_word);

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
