package com.jamieholdstock.crossword.views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.jamieholdstock.crossword.R;

public class ClueView extends GridLayout {

    private boolean clicked = false;
    private int flipTime = 650;
    private final View front;
    private final View back;

    public ClueView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        LayoutInflater.from(context).inflate(R.layout.view_clue, this);

        back = findViewById(R.id.clue_back);
        front = findViewById(R.id.clue_front);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ClueView, 0, 0);

        try {
            String clue = a.getString(R.styleable.ClueView_clue);
            if (clue != null) setClue(clue);
            String solution = a.getString(R.styleable.ClueView_solution);
            if (solution != null) setSolution(solution);
        } finally {
            a.recycle();
        }

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clicked) {
                    clicked = true;
                    flip();
                }
            }
        });
    }
    public ClueView(Context context, boolean clicked) {
        this(context, null);
        if (clicked) {
            front.setVisibility(GONE);
            back.setVisibility(VISIBLE);
        }
        this.clicked = clicked;
    }

    private void flip() {
        flipIn(back);
        flipOut(front);
    }

    private void flipIn(final View v) {
        ObjectAnimator flipIn = ObjectAnimator.ofFloat(v, "rotationY", -180.0f, 0f);
        flipIn.setDuration(flipTime);
        flipIn.start();

        ObjectAnimator appear = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        appear.setDuration(0);
        appear.setStartDelay(flipTime/2);
        appear.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationCancel(Animator a) { }
            @Override public void onAnimationRepeat(Animator a) { }
            @Override public void onAnimationStart(Animator a) { }

            @Override
            public void onAnimationEnd(Animator a) {
                v.setVisibility(VISIBLE);
            }
        });
        appear.start();
    }

    private void flipOut(final View v) {
        ObjectAnimator flipOut = ObjectAnimator.ofFloat(v, "rotationY", 0.0f, 180f);
        flipOut.setDuration(flipTime);
        flipOut.start();

        ObjectAnimator disappear = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
        disappear.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationCancel(Animator a) { }
            @Override public void onAnimationRepeat(Animator a) { }
            @Override public void onAnimationStart(Animator a) { }

            @Override
            public void onAnimationEnd(Animator a) {
                v.setVisibility(GONE);
            }
        });
        disappear.setDuration(0);
        disappear.setStartDelay(flipTime/2);
        disappear.start();
    }

    public void setClue(String clue) {
        TextView clueView = (TextView) findViewById(R.id.clue_text_front);
        clueView.setText(dontUnderline(clue));
        TextView clueView2 = (TextView) findViewById(R.id.clue2);
        clueView2.setText(underline(clue));
    }

    public void setSolution(String solution) {
        TextView solutionView = (TextView) findViewById(R.id.solution_text);
        solutionView.setText(underline(solution));
    }

    private Spanned dontUnderline(String src) {
        src = src.replaceAll("\\[", "").replaceAll("\\]", "");
        return Html.fromHtml(src);
    }

    private Spanned underline(String src) {
        src = src.replaceAll("\\[", "<u>").replaceAll("\\]", "</u>");
        return Html.fromHtml(src);
    }
}
