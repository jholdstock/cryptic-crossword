package com.jamieholdstock.crossword.activities.tutorial.devices;

import android.support.v4.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.activities.tutorial.ClueListFragment;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class ReversalActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {

        final ArrayList<SolvedClue> clues = new ArrayList<SolvedClue>(){{
            add(new SolvedClue("[Go no further] putting the crockery up (4)", "STOP"));
            add(new SolvedClue("Animal going round a shopping precinct (5)", "LLAMA"));
            add(new SolvedClue("[Turn] [to important person] making a comeback (5)", "PIVOT"));
            add(new SolvedClue("[Returned beer] [fit for a king] (5)", "LAGER"));
        }};

        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_reversal));
            add(new ClueListFragment(clues));
            add(IndicatorFragments.reversalFragment(getBaseContext()));
            add(IndicatorFragments.reversalAcrossFragment(getBaseContext()));
            add(IndicatorFragments.reversalDownFragment(getBaseContext()));
        }};
    }
}
