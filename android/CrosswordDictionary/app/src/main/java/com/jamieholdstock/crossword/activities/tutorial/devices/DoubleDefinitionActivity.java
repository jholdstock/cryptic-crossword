package com.jamieholdstock.crossword.activities.tutorial.devices;

import androidx.fragment.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.activities.tutorial.ClueListFragment;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class DoubleDefinitionActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {

        final ArrayList<SolvedClue> clues = new ArrayList<SolvedClue>(){{
            add(new SolvedClue("[Sketch] a [one-all score] (4)", "DRAW"));
            add(new SolvedClue("[Uses axe] on [meat cutlets] (5)", "CHOPS"));
            add(new SolvedClue("[Eastern European] [buff] (6)", "POLISH"));
            add(new SolvedClue("[Superior] [gambler] (6)", "BETTER"));
            add(new SolvedClue("[Charm] ones [way in] (8)", "ENTRANCE"));
        }};

        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_doubledef));
            add(new ClueListFragment(clues, "Here are some examples of double definition clues:"));
        }};
    }
}
