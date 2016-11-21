package com.jamieholdstock.crossword.activities.tutorial.devices;

import android.support.v4.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class AnagramActivity extends DevicesActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {
        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_anagram));
            add(new TutorialFragment(R.layout.devices_frag_anagram_examples));
            add(IndicatorFragments.anagramFragment(getBaseContext()));
        }};
    }
}
