package com.jamieholdstock.crossword.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.intents.IntentExtras;
import com.jamieholdstock.crossword.intents.MyIntents;
import com.jamieholdstock.crossword.service.ClueSolverService;
import com.jamieholdstock.crossword.service.SolverSearchResults;

public class ClueSolverActivity extends AppCompatActivity {

    private boolean waitingForService = false;

    private LocalBroadcastManager bManager;
    private BroadcastReceiver bReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(MyIntents.DRAW_ERROR)) {
                findViewById(R.id.error_panel).setVisibility(View.VISIBLE);
                waitingForService = false;
            }
            else if(intent.getAction().equals(MyIntents.DRAW_SOLUTIONS)) {
                SolverSearchResults solverSearchResults = (SolverSearchResults) intent.getExtras().get(IntentExtras.SOLUTIONS);

                View clueView = findViewById(R.id.clue_view);
                clueView.setVisibility(View.VISIBLE);

                TextView clue = (TextView) clueView.findViewById(R.id.clue);
                TextView solution = (TextView) clueView.findViewById(R.id.solution);

                clue.setText(solverSearchResults.getClues().get(0));
                solution.setText(solverSearchResults.getSolutions().get(0));

                waitingForService = false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clue_solver);

        findViewById(R.id.clue_view).setVisibility(View.GONE);
        findViewById(R.id.error_panel).setVisibility(View.GONE);

        bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyIntents.DRAW_ERROR);
        intentFilter.addAction(MyIntents.DRAW_SOLUTIONS);
        bManager.registerReceiver(bReceiver, intentFilter);

        Button button = (Button) findViewById(R.id.solve_clue_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.clue_view).setVisibility(View.GONE);
                findViewById(R.id.error_panel).setVisibility(View.GONE);
                EditText searchBox = (EditText) findViewById(R.id.search_box);
                String searchTerm = searchBox.getText().toString();
                sendIntentToService(searchTerm, v.getContext());
            }
        });
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
    }
}
