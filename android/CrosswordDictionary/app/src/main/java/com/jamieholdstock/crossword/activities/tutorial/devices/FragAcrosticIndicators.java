package com.jamieholdstock.crossword.activities.tutorial.devices;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.WordList;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;
import com.jamieholdstock.crossword.adapters.WordsAdapter;
import com.jamieholdstock.crossword.database.DatabaseHelper;

public class FragAcrosticIndicators extends TutorialFragment {

    public FragAcrosticIndicators(int layoutId) {
        super(layoutId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        DatabaseHelper myDbHelper = new DatabaseHelper(getContext());

        WordList acrosticIndicators = myDbHelper.getAcrosticIndicators();

        ListView list = (ListView) rootView.findViewById(R.id.listView);
        WordsAdapter adapter = new WordsAdapter(getContext(), acrosticIndicators);

        list.setAdapter(adapter);

        return rootView;
    }
}
