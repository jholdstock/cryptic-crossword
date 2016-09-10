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

        LinearLayout indicatorContainer = (LinearLayout) findViewById(R.id.indactors_panel);
        for (String indicator : word.getIndicators()) {
            TextView indicatorView = new TextView(indicatorContainer.getContext());
            indicatorView.setText(indicator);
            indicatorContainer.addView(indicatorView);
        }
    }
}
