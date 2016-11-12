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
        String clue = null;
        String solution = null;
        try {
            clue = a.getString(R.styleable.ClueView_clue);
            solution = a.getString(R.styleable.ClueView_solution);
        } finally {
            a.recycle();
        }

        setClue(clue);
        setSolution(solution);
    }

    public void setClue(String clue) {
        TextView clueView = (TextView) findViewById(R.id.clue);
        clueView.setText(clue);
    }

    public void setSolution(String solution) {
        TextView solutionView = (TextView) findViewById(R.id.solution);
        solutionView.setText(solution);
    }
}
