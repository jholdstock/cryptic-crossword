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
        WordList words = myDbHelper.getDeletionIndicators();
        return new IndicatorFragments(words, prefix + "that a charade has had some letters deleted:");
    }

    public static Fragment deletionMidFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getMidDeletionIndicators();
        return new IndicatorFragments(words, prefix + "that a charade has had a group of letters removed from its center:");
    }

    public static Fragment deletionEndFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getEndDeletionIndicators();
        return new IndicatorFragments(words, prefix + "that a charade has had one or both of its ends removed:");
    }

    public static Fragment reversalFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getReversalIndicators();
        return new IndicatorFragments(words, prefix + "that a charade has been reversed to give the solution:");
    }

    public static Fragment reversalDownFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getReversalDownIndicators();
        return new IndicatorFragments(words, "These words or phrases may indicate a reversal if they are included in a down clue:");
    }

    public static Fragment reversalAcrossFragment(Context context) {
        DatabaseHelper myDbHelper = new DatabaseHelper(context);
        WordList words = myDbHelper.getReversalAcrossIndicators();
        return new IndicatorFragments(words, "These words or phrases may indicate a reversal if they are included in an across clue:");
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
