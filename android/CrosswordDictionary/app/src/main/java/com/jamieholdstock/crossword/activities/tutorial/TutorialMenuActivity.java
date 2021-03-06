package com.jamieholdstock.crossword.activities.tutorial;

import android.os.Bundle;

import com.jamieholdstock.crossword.activities.CrosswordBaseActivity;
import com.jamieholdstock.crossword.activities.tutorial.advanced.AdvancedActivity;
import com.jamieholdstock.crossword.activities.utilities.ButtonPressAnimator;
import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.basics.BasicsActivity;

public class TutorialMenuActivity extends CrosswordBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_menu);

        ButtonPressAnimator anim = new ButtonPressAnimator(this);
        anim.slideRightToActivity(R.id.devices_button, DevicesMenuActivity.class);
        anim.slideRightToActivity(R.id.basics_button, BasicsActivity.class);
        anim.slideRightToActivity(R.id.advanced_button, AdvancedActivity.class);
    }
}
