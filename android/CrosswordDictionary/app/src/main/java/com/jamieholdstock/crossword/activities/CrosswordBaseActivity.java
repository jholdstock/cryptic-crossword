package com.jamieholdstock.crossword.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.jamieholdstock.crossword.R;

public abstract class CrosswordBaseActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
