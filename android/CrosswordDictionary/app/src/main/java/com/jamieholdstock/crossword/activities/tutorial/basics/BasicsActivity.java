package com.jamieholdstock.crossword.activities.tutorial.basics;

import android.support.v4.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class BasicsActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {
        return new ArrayList<Fragment>() {{
            add(new FragCrosswordGrid(R.layout.basics_frag_crossword_grid));
            add(new TutorialFragment(R.layout.basics_frag_quick_clue));
            add(new TutorialFragment(R.layout.basics_frag_cryptic_clue));
            add(new TutorialFragment(R.layout.basics_frag_indicators));
        }};
    }

    @Override
    protected int getActivityId() {
        return R.layout.basics_activity;
    }
}
