package com.jamieholdstock.crossword.activities.tutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.adapters.SwipeAdapter;

import java.util.ArrayList;

public abstract class FragmentSwiperActivity extends FragmentActivity {

    protected abstract ArrayList<Fragment> getFragments();
    protected abstract int getActivityId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityId());

        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        ArrayList<Fragment> frags = getFragments();

        PagerAdapter mPagerAdapter = new SwipeAdapter(getSupportFragmentManager(), frags);
        mPager.setAdapter(mPagerAdapter);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
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
