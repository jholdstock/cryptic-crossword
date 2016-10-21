package com.jamieholdstock.crossworddictionary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jamieholdstock.crossworddictionary.R;

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
            }
        });

        Button fullListButton = (Button) findViewById(R.id.full_list_button);
        assert fullListButton != null;
        fullListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WordListActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button searchButton = (Button) findViewById(R.id.search_button);
        assert searchButton != null;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
