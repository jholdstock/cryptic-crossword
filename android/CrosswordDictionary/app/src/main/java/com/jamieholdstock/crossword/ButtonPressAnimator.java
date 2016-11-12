package com.jamieholdstock.crossword;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class ButtonPressAnimator {
    private Activity srcActivity;

    public ButtonPressAnimator(Activity srcActivity) {
        this.srcActivity = srcActivity;
    }

    public void slideLeftToActivity(int buttonId, Class targetActivity) {
        slideToActivity(buttonId, targetActivity, R.anim.slide_in_left, R.anim.slide_out_right);

    }

    public void slideRightToActivity(int buttonId, Class targetActivity) {
        slideToActivity(buttonId, targetActivity, R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void slideToActivity(int buttonId, final Class targetActivity, final int in, final int out) {
        Button button = (Button) srcActivity.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), targetActivity);
                v.getContext().startActivity(intent);
                srcActivity.overridePendingTransition(in, out);
            }
        });
    }
}
