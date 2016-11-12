package com.jamieholdstock.crossword.activities.tutorial;

import android.os.Bundle;

import com.jamieholdstock.crossword.ButtonPressAnimator;
import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.devices.AcrosticActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.AnagramActivity;

public class DevicesMenuActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_menu);

        ButtonPressAnimator anim = new ButtonPressAnimator(this);
        anim.slideRightToActivity(R.id.acrostics_button, AcrosticActivity.class);
        anim.slideRightToActivity(R.id.anagrams_button, AnagramActivity.class);
    }
}
