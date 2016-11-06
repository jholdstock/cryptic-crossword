package com.jamieholdstock.crossworddictionary.activities.tutorial;

import android.support.v4.app.FragmentActivity;

import com.jamieholdstock.crossworddictionary.R;

public abstract class SwipeTutorialActivity extends FragmentActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
