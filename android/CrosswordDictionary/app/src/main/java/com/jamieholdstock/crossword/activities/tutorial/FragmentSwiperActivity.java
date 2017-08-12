package com.jamieholdstock.crossword.activities.tutorial;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.adapters.SwipeAdapter;

import java.util.ArrayList;

public abstract class FragmentSwiperActivity extends FragmentActivity {

    protected abstract ArrayList<Fragment> getFragments();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swiper_activity);

        ArrayList<Fragment> frags = getFragments();
        PagerAdapter mPagerAdapter = new SwipeAdapter(getSupportFragmentManager(), frags);
        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        for (int i = 0; i < frags.size(); i++){
            AppCompatRadioButton button = new AppCompatRadioButton(this);
            button.setSupportButtonTintList(
                    ContextCompat.getColorStateList(this,
                            R.drawable.swiper_radio_button));
            button.setClickable(false);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 5, 5, 5);

            radioGroup.addView(button, layoutParams);
        }

        radioGroup.check(radioGroup.getChildAt(0).getId());
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                radioGroup.clearCheck();
                radioGroup.check(radioGroup.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
