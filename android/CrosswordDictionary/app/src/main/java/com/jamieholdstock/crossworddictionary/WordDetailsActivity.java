package com.jamieholdstock.crossworddictionary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class WordDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_details);
        Word word = (Word) getIntent().getSerializableExtra("word_id");

        LinearLayout indicatorContainer = (LinearLayout) findViewById(R.id.word_details_panel);
        indicatorContainer.removeAllViews();
        WordView wv = new WordView(getBaseContext());
        wv.displayWord(word);
        indicatorContainer.addView(wv);
    }
}
