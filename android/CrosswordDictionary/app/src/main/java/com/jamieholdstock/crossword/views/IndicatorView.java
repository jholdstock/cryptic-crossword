package com.jamieholdstock.crossword.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jamieholdstock.crossword.IndicatorType;
import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.devices.CharadeActivity;

import static android.R.attr.left;

public class IndicatorView extends GridLayout {

    private IndicatorType type;

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


        GridLayout.MarginLayoutParams lp = new GridLayout.MarginLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,10,0,10);
        setLayoutParams(lp);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (type == null) {
                    intent = new Intent(v.getContext(), CharadeActivity.class);
                } else {
                    intent = new Intent(v.getContext(), type.getActivityToLaunch());
                }
                v.getContext().startActivity(intent);
            }
        });
    }

    public void setIndicatorType(IndicatorType type) {
        this.type = type;
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
