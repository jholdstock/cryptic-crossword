package com.jamieholdstock.crossword.activities;

import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.Word;
import com.jamieholdstock.crossword.datastore.FullDictionary;
import com.jamieholdstock.crossword.views.WordView;

import java.util.ArrayList;
import java.util.Collections;

public class AnagramSolverActivity extends SearchActivityBase {

    private FullDictionary fullDictionary;

    @Override
    protected String[] getIntro() {
        return new String[]{
                "How to use the anagram solver",
                "Enter letters in any order to search for possible anagrams",
                ""
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
    protected void onSearchButtonPressed(View v) {
        String searchTerm = searchBox.getText().toString();
        if (searchTerm.trim().equals("")) {
            return;
        }

        resultsPanel.removeAllViews();
        boolean valid = validateInput(searchTerm);
        if (!valid) {
            displayError("Invalid characters", "Only letters are valid characters");
            return;
        }

        AnagramAsync anagramAsync = new AnagramAsync() ;
        anagramAsync.execute(searchTerm);
    }

    private boolean validateInput(String searchStringOriginal) {
        for (char x : searchStringOriginal.toCharArray()) {
            if (Character.isLetter(x) || x == '.') {
            } else {
                return false;
            }
        }
        return true;
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

    private class AnagramAsync extends AsyncTask<String,Void,String> {
        ArrayList<String> answers = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            animator.start();
            searchButton.setClickable(false);
        }

        @Override
        protected String doInBackground(String... clue) {
            if (fullDictionary == null) {
                fullDictionary = new FullDictionary(getResources());
            }

            answers = fullDictionary.searchAnagram(clue[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            animator.stop();
            displaySearchResults(answers);
            searchButton.setClickable(true);
        }
    }
}
