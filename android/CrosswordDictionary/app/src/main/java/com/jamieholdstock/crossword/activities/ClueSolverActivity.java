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

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.intents.MyIntents;
import com.jamieholdstock.crossword.service.ClueSolverService;

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
                findViewById(R.id.clue_view).setVisibility(View.VISIBLE);
                waitingForService = false;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bManager.unregisterReceiver(bReceiver);
    }

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
                sendIntentToService(v.getContext());
            }
        });
    }

    private void sendIntentToService(Context context) {
        if (waitingForService) return;
        Intent msgIntent = new Intent(context, ClueSolverService.class);
        msgIntent.setAction(MyIntents.SOLVE_CLUE);
        context.startService(msgIntent);
        waitingForService = true;
    }
}
