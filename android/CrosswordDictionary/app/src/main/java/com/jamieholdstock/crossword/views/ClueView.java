package com.jamieholdstock.crossword.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridLayout;
import android.widget.TextView;

import com.jamieholdstock.crossword.R;

public class ClueView extends GridLayout {
    public ClueView(Context context) {
        this(context, null);
    }

    public ClueView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClueView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.view_clue, this);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ClueView,
                0, 0);

        try {
            String mShowText = a.getString(R.styleable.ClueView_clue);
            String mTextPos = a.getString(R.styleable.ClueView_solution);

            TextView clue = (TextView) findViewById(R.id.clue);
            TextView solution = (TextView) findViewById(R.id.solution);

            clue.setText(mShowText);
            solution.setText(mTextPos);

        } finally {
            a.recycle();
        }
    }
}
