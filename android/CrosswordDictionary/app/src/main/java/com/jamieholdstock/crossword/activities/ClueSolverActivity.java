package com.jamieholdstock.crossword.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.utilities.ImageBugFixer;
import com.jamieholdstock.crossword.intents.IntentExtras;
import com.jamieholdstock.crossword.intents.MyIntents;
import com.jamieholdstock.crossword.service.ClueSolverService;
import com.jamieholdstock.crossword.service.SolvedClue;
import com.jamieholdstock.crossword.service.SolverSearchResults;

import java.util.ArrayList;

public class ClueSolverActivity extends AppCompatActivity {

    private boolean waitingForService = false;
    private LinearLayout resultsPanel;
    private LocalBroadcastManager bManager;
    private LayoutInflater lInf;
    private BroadcastReceiver bReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(MyIntents.DRAW_NETWORK_ERROR)) {
                displayError("Error!", "Check internet connection and try again.");
                stopText();
                waitingForService = false;
            }
            else if(intent.getAction().equals(MyIntents.DRAW_SOLUTIONS)) {
                SolverSearchResults solverSearchResults = (SolverSearchResults) intent.getExtras().get(IntentExtras.SOLUTIONS);
                displaySolvedClues(solverSearchResults.getResults());
                stopText();
                waitingForService = false;
            }
        }
    };

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

    private void displaySolvedClues(ArrayList<SolvedClue> solvedClues) {
        if (solvedClues.size() == 0) {
            displayError("No results found", "Check spelling and wording carefully");
            return;
        }

        for (SolvedClue clue: solvedClues) {
            View v = lInf.inflate(R.layout.view_clue, null);

            TextView solutionView = (TextView) v.findViewById(R.id.solution);
            TextView clueView = (TextView) v.findViewById(R.id.clue);

            solutionView.setText(clue.getSolution());
            clueView.setText(clue.getClue());

            resultsPanel.addView(v, 0, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clue_solver);
        resultsPanel = (LinearLayout) findViewById(R.id.results_panel);
        resultsPanel.removeAllViews();

        lInf = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyIntents.DRAW_NETWORK_ERROR);
        intentFilter.addAction(MyIntents.DRAW_SOLUTIONS);
        bManager.registerReceiver(bReceiver, intentFilter);

        Button button = (Button) findViewById(R.id.solve_clue_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultsPanel.removeAllViews();
                EditText searchBox = (EditText) findViewById(R.id.search_box);
                String searchTerm = searchBox.getText().toString();
                sendIntentToService(searchTerm, v.getContext());
            }
        });
    }
    private String loadingText = "●●●●";
    private int mIndex = 1;
    private int delay = 300;
    private Handler mHandler = new Handler();

    public void animateText() {
        mIndex = 1;
        Button button = (Button) findViewById(R.id.solve_clue_button);

        button.setText("●");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, 0);
    }

    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            Button button = (Button) findViewById(R.id.solve_clue_button);
            button.setText(loadingText.subSequence(0, mIndex++));
            if(mIndex <= loadingText.length()) {
                mHandler.postDelayed(characterAdder, delay);
            } else {
                animateText();
            }
        }
    };

    public void stopText() {
        Button button = (Button) findViewById(R.id.solve_clue_button);
        button.setText("Solve");
        mHandler.removeCallbacks(characterAdder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bManager.unregisterReceiver(bReceiver);
    }

    private void sendIntentToService(String searchTerm, Context context) {
        if (waitingForService) return;
        Intent msgIntent = new Intent(context, ClueSolverService.class);
        msgIntent.setAction(MyIntents.SOLVE_CLUE);
        msgIntent.putExtra(IntentExtras.SEARCH_TERM, searchTerm);
        context.startService(msgIntent);
        waitingForService = true;
        animateText();
    }
}
