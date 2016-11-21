package com.jamieholdstock.crossword.activities.tutorial.devices;

import android.support.v4.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class HiddenWordActivity extends DevicesActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {
        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_hiddenword));
            add(new TutorialFragment(R.layout.devices_frag_hiddenword_examples));
            add(IndicatorFragments.hiddenWordFragment(getBaseContext()));
        }};
    }
}
