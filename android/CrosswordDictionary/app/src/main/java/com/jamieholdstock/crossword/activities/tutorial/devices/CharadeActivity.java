package com.jamieholdstock.crossword.activities.tutorial.devices;

import androidx.fragment.app.Fragment;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.activities.tutorial.ClueListFragment;
import com.jamieholdstock.crossword.activities.tutorial.FragmentSwiperActivity;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;

import java.util.ArrayList;

public class CharadeActivity extends FragmentSwiperActivity {

    @Override
    protected ArrayList<Fragment> getFragments() {

        final ArrayList<SolvedClue> clues = new ArrayList<SolvedClue>(){{
            add(new SolvedClue("[Little river]'s [carbon] [stink] (5)", "CREEK (C + REEK)"));
            add(new SolvedClue("[Regarding] the [Spanish] film (4)", "REEL (RE + EL)"));
            add(new SolvedClue("Pizza topping with [capsicum] [on] half of [i]t (9)", " PEPPERONI (PEPPER + ON + I)"));
            add(new SolvedClue("[Friend] follows [child] completely (7)", "TOTALLY (TOT + ALLY)"));
            add(new SolvedClue("[Damage] [church] with procession (5)", "MARCH (MAR + CH)"));
        }};

        return new ArrayList<Fragment>() {{
            add(new TutorialFragment(R.layout.devices_frag_charades));
            add(new TutorialFragment(R.layout.devices_frag_char_roman_num));
            add(new TutorialFragment(R.layout.devices_frag_char_phonetic));
            add(new TutorialFragment(R.layout.devices_frag_char_science));
            add(new TutorialFragment(R.layout.devices_frag_char_advanced));
            add(new ClueListFragment(clues, "Here are some examples of charade clues:"));
        }};
    }
}
