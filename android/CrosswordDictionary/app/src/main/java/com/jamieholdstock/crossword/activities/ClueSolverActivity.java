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
import com.jamieholdstock.crossword.activities.utilities.LoadingAnimator;
import com.jamieholdstock.crossword.intents.IntentExtras;
import com.jamieholdstock.crossword.intents.MyIntents;
import com.jamieholdstock.crossword.service.ClueSolverService;
import com.jamieholdstock.crossword.service.SolvedClue;
import com.jamieholdstock.crossword.service.SolverSearchResults;
import com.jamieholdstock.crossword.views.ClueView;

import java.util.ArrayList;
import java.util.Collections;

public class ClueSolverActivity extends SearchActivityBase {

    private boolean waitingForService = false;
    private LoadingAnimator animator;
    private LocalBroadcastManager bManager;

    private BroadcastReceiver bReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(MyIntents.DRAW_NETWORK_ERROR)) {
                displayError("Error!", "Check internet connection and try again.");
                animator.stop();
                waitingForService = false;
            }
            else if(intent.getAction().equals(MyIntents.DRAW_SOLUTIONS)) {
                SolverSearchResults solverSearchResults = (SolverSearchResults) intent.getExtras().get(IntentExtras.SOLUTIONS);
                displaySearchResults(solverSearchResults.getResults());
                animator.stop();
                waitingForService = false;
            }
        }
    };

    @Override
    protected String[] getIntro() {
        return new String[]{
                "How to use the clue solver",
                "Type a full clue into the box above and attempt to look it up in a database of previously solved clues.",
                "Clues can be reworded almost endlessly and new clues are being written every day, so don't expect this to work all the time. For brand new crosswords this may not work at all. "
        };
    }

    @Override
    protected String getSearchHint() {
        return "Clue...";
    }

    @Override
    protected int getBackgroundColor() {
        return R.color.Nephritis;
    }

    @Override
    protected boolean isSeachButtonVisible() {
        return true;
    }

    @Override
    protected void onCreate() {
        bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyIntents.DRAW_NETWORK_ERROR);
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

    private void displaySearchResults(ArrayList<SolvedClue> solvedClues) {
        if (solvedClues.size() == 0) {
            displayError("No results found", "Check spelling and wording carefully");
            return;
        }

        Collections.reverse(solvedClues);

        for (SolvedClue clue: solvedClues) {
            ClueView v = new ClueView(getApplicationContext());

            v.setSolution(clue.getSolution());
            v.setClue(clue.getClue());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 15, 0, 15);
            resultsPanel.addView(v, 0, params);
        }
    }

    private void sendIntentToService(String searchTerm) {
        if (waitingForService) return;
        Intent msgIntent = new Intent(getApplicationContext(), ClueSolverService.class);
        msgIntent.setAction(MyIntents.PERFORM_SEARCH);
        msgIntent.putExtra(IntentExtras.SEARCH_TERM, searchTerm);
        getApplicationContext().startService(msgIntent);
        waitingForService = true;
    }
}
