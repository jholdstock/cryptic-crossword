package com.jamieholdstock.crossword.activities.tutorial.devices;

import androidx.fragment.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.activities.tutorial.ClueListFragment;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class AnagramActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {

        final ArrayList<SolvedClue> clues = new ArrayList<SolvedClue>(){{
            add(new SolvedClue("[Stinging insect] damaged [paws] (4)", "WASP"));
            add(new SolvedClue("[Teach] about [swindler] (5)", "CHEAT"));
            add(new SolvedClue("[Lisa] mistaken to [travel the sea] (4)", "SAIL"));
            add(new SolvedClue("Disorderly [piper eats] [canapé] (9)", "APPETISER"));
            add(new SolvedClue("[Musical group] unsettled the [carthorse] (9)", "ORCHESTRA"));
        }};

        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_anagram));
            add(new ClueListFragment(clues, "Here are some examples of anagram clues:"));
            add(IndicatorFragments.anagramFragment(getBaseContext()));
        }};
    }
}
