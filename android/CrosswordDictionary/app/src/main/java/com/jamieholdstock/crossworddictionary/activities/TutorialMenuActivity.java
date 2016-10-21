package com.jamieholdstock.crossworddictionary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jamieholdstock.crossworddictionary.R;

public class TutorialMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_menu);

        Button useAppButton = (Button) findViewById(R.id.use_app_button);
        assert useAppButton != null;
        useAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WordListActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button solveButton = (Button) findViewById(R.id.solve_cryptics_button);
        assert solveButton != null;
        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }
}
