package com.jamieholdstock.crossword.activities.tutorial;

import android.support.v7.app.AppCompatActivity;

import com.jamieholdstock.crossword.R;

public class TutorialActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
