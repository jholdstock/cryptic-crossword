package com.jamieholdstock.crossword.activities.tutorial.devices;

import android.support.v4.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.activities.tutorial.ClueListFragment;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class HiddenWordActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {

        final ArrayList<SolvedClue> clues = new ArrayList<SolvedClue>(){{
            add(new SolvedClue("[A short distance] from W[inch]ester (4)", "INCH"));
            add(new SolvedClue("[Not the same] in pr[une qual]ity (7)", "UNEQUAL"));
            add(new SolvedClue("[Metal] concealed by env[iron]mentalist (4)", "IRON"));
            add(new SolvedClue("[Hide] in Arthur'[s kin]gdom (4)", "SKIN"));
        }};

        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_hiddenword));
            add(new ClueListFragment(clues));
            add(IndicatorFragments.hiddenWordFragment(getBaseContext()));
        }};
    }
}
