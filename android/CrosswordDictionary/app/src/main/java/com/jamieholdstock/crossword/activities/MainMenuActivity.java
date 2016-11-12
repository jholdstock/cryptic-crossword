package com.jamieholdstock.crossword.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.jamieholdstock.crossword.ButtonPressAnimator;
import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.TutorialMenuActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ButtonPressAnimator anim = new ButtonPressAnimator(this);
        anim.slideRightToActivity(R.id.tutorial_button, TutorialMenuActivity.class);
        anim.slideLeftToActivity(R.id.clue_solver_button, ClueSolverActivity.class);
        anim.slideLeftToActivity(R.id.search_button, SearchActivity.class);

        Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fade.setStartOffset(500);
        findViewById(R.id.title_container).startAnimation(fade);
        Animation slowFade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        slowFade.setStartOffset(1250);
        findViewById(R.id.button_panel).startAnimation(slowFade);
    }


}
