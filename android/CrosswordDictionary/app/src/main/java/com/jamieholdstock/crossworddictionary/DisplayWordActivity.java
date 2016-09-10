package com.jamieholdstock.crossworddictionary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);
        TextView t = (TextView) findViewById(R.id.textView);
        String word = getIntent().getStringExtra("text");
        t.setText(word);
    }

}
