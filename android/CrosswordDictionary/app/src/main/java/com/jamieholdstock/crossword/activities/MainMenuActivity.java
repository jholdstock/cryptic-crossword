package com.jamieholdstock.crossword.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.TutorialMenuActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button tutorialButton = (Button) findViewById(R.id.tutorial_button);
        assert tutorialButton != null;
        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TutorialMenuActivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        Button searchButton = (Button) findViewById(R.id.search_button);
        assert searchButton != null;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });

        Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fade.setStartOffset(500);
        findViewById(R.id.title_container).startAnimation(fade);
        Animation slowfade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        slowfade.setStartOffset(1250);
        findViewById(R.id.button_panel).startAnimation(slowfade);
    }
}
