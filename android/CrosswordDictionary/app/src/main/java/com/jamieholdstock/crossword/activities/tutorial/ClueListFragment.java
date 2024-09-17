package com.jamieholdstock.crossword.activities.tutorial;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.SolvedClue;
import com.jamieholdstock.crossword.views.ClueView;

import java.util.ArrayList;

public class ClueListFragment extends Fragment {

    private ArrayList<SolvedClue> clues;
    private String pageHeader;

    public ClueListFragment(ArrayList<SolvedClue> clues, String pageHeader) {
        this.clues = clues;
        this.pageHeader = pageHeader;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.clue_list_fragment, container, false);
        TextView title = (TextView) rootView.findViewById(R.id.clue_intro);
        title.setText(pageHeader);

        final LinearLayout lin = (LinearLayout) rootView.findViewById(R.id.clue_list);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 15, 0, 15);

        for(SolvedClue clue : clues) {
            ClueView v = new ClueView(this.getContext(), false);
            v.setClue(clue.getClue());
            v.setSolution(clue.getSolution());
            lin.addView(v, params);
        }

        return rootView;
    }
}
