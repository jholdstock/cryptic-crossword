package com.jamieholdstock.crossword.activities.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.basics.BasicsActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.AnagramActivity;

public class TutorialMenuActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_menu);

        Button useAppButton = (Button) findViewById(R.id.devices_button);
        assert useAppButton != null;
        useAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AnagramActivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        Button solveButton = (Button) findViewById(R.id.solve_cryptics_button);
        assert solveButton != null;
        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BasicsActivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
    }
}
