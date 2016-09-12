package com.jamieholdstock.crossworddictionary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WordDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_details);
        Word word = (Word) getIntent().getSerializableExtra("word_id");

        TextView wordView = (TextView) findViewById(R.id.textView);
        wordView.setText(word.getWord());

        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        LinearLayout indicatorContainer = (LinearLayout) findViewById(R.id.indactors_panel);
        if (word.hasIndicators()) {
            for (String indicator : word.getIndicators()) {
                TextView indicatorView = new TextView(indicatorContainer.getContext());
                indicatorView.setText(indicator);
                indicatorContainer.addView(indicatorView);
            }
        }
        else {
            rootLayout.removeView(indicatorContainer);
        }

        LinearLayout abbreviationContainer = (LinearLayout) findViewById(R.id.abbr_panel);
        if (word.hasAbbreviations())
        {
            TextView abbrView = new TextView(abbreviationContainer.getContext());
            abbrView.setText(word.getAbbreviations());
            abbreviationContainer.addView(abbrView);
        }
        else {
            rootLayout.removeView(abbreviationContainer);
        }
    }
}
