package com.jamieholdstock.crossword.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.jamieholdstock.crossword.activities.AnagramSolverActivity;
import com.jamieholdstock.crossword.datastore.FullDictionary;
import com.jamieholdstock.crossword.intents.IntentExtras;
import com.jamieholdstock.crossword.intents.MyIntents;

import java.util.ArrayList;

public class AnagramSolverService extends IntentService {
    private static FullDictionary fullDictionary;

    public AnagramSolverService() {
        super("AnagramSolverService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (fullDictionary == null) {
            fullDictionary = new FullDictionary(getResources());
        }
        String action = intent.getAction();
        if (action.equals(MyIntents.PERFORM_SEARCH)) {
            String clue = intent.getStringExtra(IntentExtras.SEARCH_TERM);
            solveAnagram(clue);
        }
    }

    private void sendWordsToActivity(ArrayList<String> wordList) {
        Intent i = new Intent(this.getApplicationContext(), AnagramSolverActivity.class);
        i.setAction(MyIntents.DRAW_SOLUTIONS);
        i.putStringArrayListExtra(IntentExtras.SOLUTIONS, wordList);

        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

    private void solveAnagram(String clue) {
        ArrayList<String> wordList = fullDictionary.searchAnagram(clue);
        sendWordsToActivity(wordList);
    }
}
