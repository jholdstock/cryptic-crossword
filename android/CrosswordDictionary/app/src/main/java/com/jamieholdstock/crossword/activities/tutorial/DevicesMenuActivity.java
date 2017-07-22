package com.jamieholdstock.crossword.activities.tutorial;

import android.os.Bundle;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.CrosswordBaseActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.AcrosticActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.AnagramActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.CharadeActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.DeletionActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.DoubleDefinitionActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.HiddenWordActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.HomophoneActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.QuestionMarkActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.ReversalActivity;
import com.jamieholdstock.crossword.activities.utilities.ButtonPressAnimator;

public class DevicesMenuActivity extends CrosswordBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_menu);

        ButtonPressAnimator anim = new ButtonPressAnimator(this);
        anim.slideRightToActivity(R.id.acrostics_button, AcrosticActivity.class);
        anim.slideRightToActivity(R.id.anagrams_button, AnagramActivity.class);
        anim.slideRightToActivity(R.id.hiddenword_button, HiddenWordActivity.class);
        anim.slideRightToActivity(R.id.homophone_button, HomophoneActivity.class);
        anim.slideRightToActivity(R.id.doubledef_button, DoubleDefinitionActivity.class);
        anim.slideRightToActivity(R.id.deletion_button, DeletionActivity.class);
        anim.slideRightToActivity(R.id.questionmark_button, QuestionMarkActivity.class);
        anim.slideRightToActivity(R.id.reversal_button, ReversalActivity.class);
        anim.slideRightToActivity(R.id.charade_button, CharadeActivity.class);
    }
}
