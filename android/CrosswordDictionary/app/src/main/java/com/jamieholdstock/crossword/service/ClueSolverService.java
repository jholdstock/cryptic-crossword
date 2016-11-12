package com.jamieholdstock.crossword.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.jamieholdstock.crossword.L;
import com.jamieholdstock.crossword.activities.ClueSolverActivity;
import com.jamieholdstock.crossword.intents.IntentExtras;
import com.jamieholdstock.crossword.intents.MyIntents;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ClueSolverService extends IntentService {

    private String solverUrl = "http://www.wordplays.com/crossword-solver/";

    public ClueSolverService() {
        super("ClueSolverService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (action.equals(MyIntents.SOLVE_CLUE)) {
            String clue = "clue here";
            solveClue(clue);
        }
    }

    private void sendErrorToActivity() {
        Intent i = new Intent(this.getApplicationContext(), ClueSolverActivity.class);
        i.setAction(MyIntents.DRAW_ERROR);
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

    private void sendSolutionsToActivity(Solutions solutions) {
        Intent i = new Intent(this.getApplicationContext(), ClueSolverActivity.class);
        i.setAction(MyIntents.DRAW_SOLUTIONS);
        i.putExtra(IntentExtras.SOLUTIONS, solutions);

        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

    private void solveClue(String clue) {
        String query = null;
        try {
            query = URLEncoder.encode(clue, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            L.l("Service sending GET to " + solverUrl + query);
            String html= Jsoup.connect(solverUrl + query).execute().body();
            L.l("Service received non-error response");
            Solutions stats = new Solutions(html);
            sendSolutionsToActivity(stats);
        } catch (IOException e) {
            sendErrorToActivity();
            L.l("Service received error response");
            return;
        }
    }
}
