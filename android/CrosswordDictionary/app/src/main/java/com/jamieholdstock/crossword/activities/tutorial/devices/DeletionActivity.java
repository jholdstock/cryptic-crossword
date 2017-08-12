package com.jamieholdstock.crossword.activities.tutorial.devices;

import android.support.v4.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.activities.tutorial.ClueListFragment;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class DeletionActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {

        final ArrayList<SolvedClue> clues = new ArrayList<SolvedClue>(){{
            add(new SolvedClue("Little shark edges away from diver's equipment (3)", "CUB (from SCUBA)"));
            add(new SolvedClue("Head off champion worker (7)", "ARTISAN (from PARTISAN)"));
            add(new SolvedClue("Circuits almost falling (4)", "LAPS (from LAPSE)"));
            add(new SolvedClue("Work the land, without first limb (3)", "ARM (from FARM)"));
        }};

        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_deletion));
            add(new ClueListFragment(clues));
            add(IndicatorFragments.deletionFragment(getBaseContext()));
        }};
    }
}
