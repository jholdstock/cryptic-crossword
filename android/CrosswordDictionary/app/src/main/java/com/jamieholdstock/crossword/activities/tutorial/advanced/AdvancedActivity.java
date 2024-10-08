package com.jamieholdstock.crossword.activities.tutorial.advanced;

import androidx.fragment.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class AdvancedActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {
        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.advanced_frag));
            add(new TutorialFragment(R.layout.advanced2_frag));
            add(new TutorialFragment(R.layout.advanced3_frag));
        }};
    }

}
