package com.jamieholdstock.crossword.activities.tutorial.devices;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.WordList;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;
import com.jamieholdstock.crossword.adapters.WordsAdapter;
import com.jamieholdstock.crossword.datastore.DatabaseHelper;

public class IndicatorFragments extends TutorialFragment {

    private WordList words;
    private String headingText;
    private static String prefix = "Here are some words and phrases which could indicate ";

    private IndicatorFragments(WordList words, String headingSuffix) {
        super(R.layout.devices_frag_indicator_list);
        this.words = words;
        this.headingText = headingSuffix;
    }

    public static IndicatorFragments doubleDefinitionFragment() {
        WordList words = new WordList();
        return new IndicatorFragments(words, "Double definition clues do not regularly contain indicator words as they are normally just two literal definitions placed side by side");
    }

    public static IndicatorFragments hiddenWordFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getHiddenWordIndicators();
        return new IndicatorFragments(words, prefix + "that the solution is hidden somewhere inside a clue:");
    }

    public static IndicatorFragments acrosticFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getAcrosticIndicators();
        return new IndicatorFragments(words, prefix + "that a clue has an acrostic solution:");
    }

    public static IndicatorFragments anagramFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getAnagramIndicators();
        return new IndicatorFragments(words, prefix + "the presence of an anagram in a clue:");
    }

    public static Fragment homophoneFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getHomophoneIndicators();
        return new IndicatorFragments(words, prefix + "there is a homophone in a clue:");
    }

    public static Fragment deletionFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getAllDeletionIndicators();
        return new IndicatorFragments(words, prefix + "that a charade has had some letters deleted to give the solution:");
    }

    public static Fragment reversalFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getReversalIndicators();
        return new IndicatorFragments(words, prefix + "that a charade has been reversed to give the solution:");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        ListView list = (ListView) rootView.findViewById(R.id.listView);
        WordsAdapter adapter = new WordsAdapter(getContext(), words);

        list.setAdapter(adapter);

        TextView heading = (TextView) rootView.findViewById(R.id.indicator_list_heading);
        heading.setText(headingText);

        return rootView;
    }
}
