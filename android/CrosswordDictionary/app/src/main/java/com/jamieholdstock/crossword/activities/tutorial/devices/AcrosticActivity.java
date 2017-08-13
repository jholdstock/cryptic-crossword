package com.jamieholdstock.crossword.activities.tutorial.devices;

import android.support.v4.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.activities.tutorial.ClueListFragment;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class AcrosticActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {

        final ArrayList<SolvedClue> clues = new ArrayList<SolvedClue>(){{
            add(new SolvedClue("[Biting] heads off [t]arantulas, [e]ating [e]ven [t]iny [h]airs (5)", "TEETH"));
            add(new SolvedClue("[Teams] beginners [s]tuck [i]n [d]epressing [e]arly [s]lump (5)", "SIDES"));
            add(new SolvedClue("[Fat] [o]nly [b]y [e]ating [s]o [e]xcessively initially (5)", "OBESE"));
            add(new SolvedClue("Initially [a]miable [p]erson [e]ats [primate] (3)", "APE"));
            add(new SolvedClue("[S]ome [U]RLs [r]ecommended [f]or beginners to [explore online] (4)", "SURF"));
        }};

        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_acrostic));
            add(new ClueListFragment(clues, "Here are some examples of acrostic clues:"));
            add(IndicatorFragments.acrosticFragment(getBaseContext()));
        }};
    }
}
