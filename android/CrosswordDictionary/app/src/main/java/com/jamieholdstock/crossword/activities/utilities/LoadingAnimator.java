package com.jamieholdstock.crossword.activities.utilities;

import android.graphics.Typeface;
import android.os.Handler;
import android.widget.TextView;

public class LoadingAnimator {
    private String s1 = "●   ";
    private String s2 = "●●  ";
    private String s3 = "●●● ";
    private String s4 = "●●●●";

    private int mIndex = 1;
    private int delay = 400;
    private Handler mHandler;
    private TextView view;

    private CharSequence previousText;
    private Typeface previous;

    public LoadingAnimator(TextView view) {
        this.view = view;
        this.mHandler = new Handler();
    }

    public void start() {
        mIndex = 1;

        previousText = view.getText();
        view.setText(s1);

        previous = view.getTypeface();
        view.setTypeface(Typeface.MONOSPACE);

        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, delay);
    }

    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            mIndex++;
            switch(mIndex) {
                case 1:
                    view.setText(s1);
                    break;
                case 2:
                    view.setText(s2);
                    break;
                case 3:
                    view.setText(s3);
                    break;
                case 4:
                    view.setText(s4);
                    break;
            }

            if(mIndex == 4) {
                mIndex = 0;
            }

            mHandler.postDelayed(characterAdder, delay);
        }
    };

    public void stop() {
        view.setText(previousText);
        view.setTypeface(previous);
        mHandler.removeCallbacks(characterAdder);
    }
}
