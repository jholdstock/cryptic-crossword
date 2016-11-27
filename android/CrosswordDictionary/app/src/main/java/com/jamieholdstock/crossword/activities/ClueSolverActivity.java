package com.jamieholdstock.crossword.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.utilities.ImageBugFixer;
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
    private LinearLayout resultsPanel;
    private LocalBroadcastManager bManager;
    private LayoutInflater lInf;
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
                displaySolvedClues(context, solverSearchResults.getResults());
                animator.stop();
                waitingForService = false;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clue_solver;
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
        resultsPanel = (LinearLayout) findViewById(R.id.results_panel);

        lInf = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyIntents.DRAW_NETWORK_ERROR);
        intentFilter.addAction(MyIntents.DRAW_SOLUTIONS);
        bManager.registerReceiver(bReceiver, intentFilter);

        final EditText searchBox = (EditText) findViewById(R.id.search_box);
        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Button button = (Button) findViewById(R.id.search_button);
                button.callOnClick();
                return true;
            }
        });

        Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = searchBox.getText().toString();

                if (searchTerm.trim().equals("")) {
                    return;
                }
                resultsPanel.removeAllViews();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);

                sendIntentToService(searchTerm, v.getContext());
            }
        });
        animator = new LoadingAnimator(button);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bManager.unregisterReceiver(bReceiver);
    }

    private void displayError(String topMessage, String bottomMessage) {
        View v = lInf.inflate(R.layout.view_warning, null);
        TextView topText = (TextView) v.findViewById(R.id.top_message);
        topText.setText(topMessage);

        TextView bottomText = (TextView) v.findViewById(R.id.bottom_message);
        bottomText.setText(bottomMessage);

        resultsPanel.addView(v, 0, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ImageBugFixer.fix(R.id.error_img, R.mipmap.warning, v, getResources());
    }

    private void displaySolvedClues(Context context, ArrayList<SolvedClue> solvedClues) {
        if (solvedClues.size() == 0) {
            displayError("No results found", "Check spelling and wording carefully");
            return;
        }

        Collections.reverse(solvedClues);

        for (SolvedClue clue: solvedClues) {
            ClueView v = new ClueView(context);

            v.setSolution(clue.getSolution());
            v.setClue(clue.getClue());


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 15, 0, 15);
            resultsPanel.addView(v, 0, params);
        }
    }

    private void sendIntentToService(String searchTerm, Context context) {
        if (waitingForService) return;
        Intent msgIntent = new Intent(context, ClueSolverService.class);
        msgIntent.setAction(MyIntents.SOLVE_CLUE);
        msgIntent.putExtra(IntentExtras.SEARCH_TERM, searchTerm);
        context.startService(msgIntent);
        waitingForService = true;
        animator.start();
    }
}
