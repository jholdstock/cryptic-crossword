package com.jamieholdstock.crossword.activities.tutorial.devices;

import android.support.v4.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.activities.tutorial.ClueListFragment;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class HomophoneActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {

        final ArrayList<SolvedClue> clues = new ArrayList<SolvedClue>(){{
            add(new SolvedClue("We hear [taxi cost] to be [reasonable] (4)", "FAIR"));
            add(new SolvedClue("[Octet] [had dinner] noisily (5)", "EIGHT"));
            add(new SolvedClue("[Part of play] heard, or otherwise [perceived] (4)", "SEEN"));
            add(new SolvedClue("[Massage] is a [requirement], according to hearsay (5)", "KNEAD"));
        }};

        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_homophone));
            add(new ClueListFragment(clues, "Here are some examples of homophone clues:"));
            add(IndicatorFragments.homophoneFragment(getBaseContext()));
        }};
    }
}
