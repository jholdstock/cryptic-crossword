package com.jamieholdstock.crossworddictionary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridLayout;
import android.widget.TextView;

import com.jamieholdstock.crossworddictionary.R;

public class IndicatorView extends GridLayout {
    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.view_indicator, this);
        setBackgroundResource(R.drawable.indicator_background);
    }

    public void setHeader(String text) {
        TextView textview = (TextView) findViewById(R.id.header_text);
        textview.setText(text);
    }

    public void setMainText(String text) {
        TextView textview = (TextView) findViewById(R.id.main_text);
        textview.setText(text);
    }
}
