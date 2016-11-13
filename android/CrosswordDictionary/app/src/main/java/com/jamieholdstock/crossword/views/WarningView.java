package com.jamieholdstock.crossword.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.jamieholdstock.crossword.R;

public class WarningView extends LinearLayout {
    public WarningView(Context context) {
        this(context, null);
    }

    public WarningView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WarningView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.view_warning, this);
    }
}
