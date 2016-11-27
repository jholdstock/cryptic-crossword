package com.jamieholdstock.crossword.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.jamieholdstock.crossword.activities.utilities.ButtonPressAnimator;
import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.TutorialMenuActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ButtonPressAnimator anim = new ButtonPressAnimator(this);
        anim.slideRightToActivity(R.id.tutorial_button, TutorialMenuActivity.class);
        anim.slideRightToActivity(R.id.clue_solver_button, ClueSolverActivity.class);
        anim.slideRightToActivity(R.id.search_indicators_button, SearchIndicatorsActivity.class);

        Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fade.setStartOffset(450);
        findViewById(R.id.title_container).startAnimation(fade);
        Animation slowFade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        slowFade.setStartOffset(1050);
        findViewById(R.id.button_panel).startAnimation(slowFade);
    }
}
