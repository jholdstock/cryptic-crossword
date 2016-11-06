package com.jamieholdstock.crossword.activities.tutorial.basics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;
import com.jamieholdstock.crossword.activities.tutorial.SwipeTutorialActivity;
import com.jamieholdstock.crossword.adapters.SwipeAdapter;

import java.util.ArrayList;

public class BasicsActivity extends SwipeTutorialActivity {

    private ViewPager mPager;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_basics);

        mPager = (ViewPager) findViewById(R.id.tutorial_pager);
        ArrayList<Fragment> frags = new ArrayList<Fragment>() {{
            add(new FragCrosswordGrid(R.layout.fragment_crossword_grid));
            add(new TutorialFragment(R.layout.fragment_quick_clue));
            add(new TutorialFragment(R.layout.fragment_cryptic_clue));
            add(new TutorialFragment(R.layout.fragment_indicators));
            add(new TutorialFragment(R.layout.fragment_question_mark));
        }};

        PagerAdapter mPagerAdapter = new SwipeAdapter(getSupportFragmentManager(), frags);
        mPager.setAdapter(mPagerAdapter);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
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
}
