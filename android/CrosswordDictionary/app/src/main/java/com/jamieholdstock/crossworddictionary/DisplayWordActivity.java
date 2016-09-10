package com.jamieholdstock.crossworddictionary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);
        Word word = (Word) getIntent().getSerializableExtra("word_id");

        TextView t = (TextView) findViewById(R.id.textView);
        t.setText(word.getWord());

        TextView t2 = (TextView) findViewById(R.id.textView2);
        String s = "";
        for (String indicator : word.getIndicators()) {
            s += indicator + "\n";
        }

        t2.setText(s);
    }

}
