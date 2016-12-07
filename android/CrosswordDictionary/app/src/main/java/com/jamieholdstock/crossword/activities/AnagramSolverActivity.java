package com.jamieholdstock.crossword.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.Word;
import com.jamieholdstock.crossword.activities.utilities.LoadingAnimator;
import com.jamieholdstock.crossword.intents.IntentExtras;
import com.jamieholdstock.crossword.intents.MyIntents;
import com.jamieholdstock.crossword.service.AnagramSolverService;
import com.jamieholdstock.crossword.views.WordView;

import java.util.ArrayList;
import java.util.Collections;

public class AnagramSolverActivity extends SearchActivityBase {

    private boolean waitingForService = false;
    private LoadingAnimator animator;
    private LocalBroadcastManager bManager;

    private BroadcastReceiver bReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(MyIntents.DRAW_SOLUTIONS)) {
            ArrayList<String> solverSearchResults = intent.getExtras().getStringArrayList(IntentExtras.SOLUTIONS);
            displaySearchResults(solverSearchResults);
            animator.stop();
            waitingForService = false;
        }
        }
    };

    @Override
    protected String[] getIntro() {
        return new String[]{
                "How to use the anagram solver",
                "Explain two modes",
                "Anagram and word fit"
        };
    }

    @Override
    protected String getSearchHint() {
        return "Letters...";
    }

    @Override
    protected int getBackgroundColor() {
        return R.color.Amethyst;
    }

    @Override
    protected boolean isSeachButtonVisible() {
        return true;
    }

    @Override
    protected void onCreate() {
        bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyIntents.DRAW_SOLUTIONS);
        bManager.registerReceiver(bReceiver, intentFilter);

        animator = new LoadingAnimator(searchButton);
    }

    @Override
    protected void onSearchButtonPressed(View v) {
        String searchTerm = searchBox.getText().toString();

        if (searchTerm.trim().equals("")) {
            return;
        }
        resultsPanel.removeAllViews();

        animator.start();
        sendIntentToService(searchTerm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bManager.unregisterReceiver(bReceiver);
    }

    private void displaySearchResults(ArrayList<String> solvedClues) {
        if (solvedClues.size() == 0) {
            displayError("No results found", "");
            return;
        }

        Collections.reverse(solvedClues);

        for (String clue: solvedClues) {
            WordView v = new WordView(getApplicationContext());
            Word word = new Word(clue, new ArrayList<String>(), null);
            v.displayWord(word);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 15, 0, 15);
            resultsPanel.addView(v, 0, params);
        }
    }

    private void sendIntentToService(String searchTerm) {
        if (waitingForService) return;
        Intent msgIntent = new Intent(getApplicationContext(), AnagramSolverService.class);
        msgIntent.setAction(MyIntents.PERFORM_SEARCH);
        msgIntent.putExtra(IntentExtras.SEARCH_TERM, searchTerm);
        getApplicationContext().startService(msgIntent);
        waitingForService = true;
    }
}
