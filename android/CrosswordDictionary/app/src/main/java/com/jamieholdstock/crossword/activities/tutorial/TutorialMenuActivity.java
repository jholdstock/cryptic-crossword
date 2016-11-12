package com.jamieholdstock.crossword.activities.tutorial;

import android.os.Bundle;

import com.jamieholdstock.crossword.activities.utilities.ButtonPressAnimator;
import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.basics.BasicsActivity;

public class TutorialMenuActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_menu);

        ButtonPressAnimator anim = new ButtonPressAnimator(this);
        anim.slideRightToActivity(R.id.devices_button, DevicesMenuActivity.class);
        anim.slideRightToActivity(R.id.solve_cryptics_button, BasicsActivity.class);
    }
}
