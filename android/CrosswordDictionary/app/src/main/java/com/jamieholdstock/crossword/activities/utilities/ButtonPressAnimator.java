package com.jamieholdstock.crossword.activities.utilities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.jamieholdstock.crossword.R;

public class ButtonPressAnimator {
    private Activity srcActivity;

    public ButtonPressAnimator(Activity srcActivity) {
        this.srcActivity = srcActivity;
    }

    public void slideRightToActivity(int buttonId, final Class targetActivity) {
        Button button = (Button) srcActivity.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), targetActivity);
                v.getContext().startActivity(intent);
                srcActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}
