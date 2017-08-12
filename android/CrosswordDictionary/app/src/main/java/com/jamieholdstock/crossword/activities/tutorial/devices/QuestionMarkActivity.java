package com.jamieholdstock.crossword.activities.tutorial.devices;

import android.support.v4.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.activities.tutorial.ClueListFragment;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class QuestionMarkActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {

        final ArrayList<SolvedClue> clues = new ArrayList<SolvedClue>(){{
            add(new SolvedClue("[Hair style] with comb in it? (7)", "BEEHIVE"));
            add(new SolvedClue("Mental block? (6,4)", "RUBIKS CUBE"));
            add(new SolvedClue("Hands up for an early lunch? (4)", "NOON"));
            add(new SolvedClue("[Poor opportunities] for snooker players? (3,6)", "BAD BREAKS"));
            add(new SolvedClue("Spirited community? (5,4", "GHOST TOWN"));
            add(new SolvedClue("Dollars for quarters? (4)", "RENT"));
        }};

        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_questionmark));
            add(new ClueListFragment(clues));
        }};
    }
}
