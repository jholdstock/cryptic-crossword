package com.jamieholdstock.crossword.activities.tutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.views.ClueView;

import java.util.ArrayList;

public class ClueListFragment extends Fragment {

    private ArrayList<SolvedClue> clues;

    public ClueListFragment(ArrayList<SolvedClue> clues) {
        this.clues = clues;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.clue_list_fragment, container, false);
        final LinearLayout lin = (LinearLayout) rootView.findViewById(R.id.clue_list);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 15, 0, 15);

        for(SolvedClue clue : clues) {
            ClueView v = new ClueView(this.getContext(), false);
            v.setClue(clue.getClue());
            v.setSolution(clue.getSolution());
            lin.addView(v, 0, params);
        }

        return rootView;
    }
}
