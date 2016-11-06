package com.jamieholdstock.crossword.activities.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.devices.AcrosticActivity;
import com.jamieholdstock.crossword.activities.tutorial.devices.AnagramActivity;

public class DevicesMenuActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_menu);

        Button acrosticButton = (Button) findViewById(R.id.acrostics_button);
        assert acrosticButton != null;
        acrosticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AcrosticActivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        Button anagramButton = (Button) findViewById(R.id.anagrams_button);
        assert anagramButton != null;
        anagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AnagramActivity.class);
                v.getContext().startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
    }
}
