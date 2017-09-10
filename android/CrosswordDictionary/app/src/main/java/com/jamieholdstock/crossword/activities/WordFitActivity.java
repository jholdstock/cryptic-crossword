package com.jamieholdstock.crossword.activities;

import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jamieholdstock.crossword.IndicatorType;
import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.Word;
import com.jamieholdstock.crossword.views.WordView;

import java.util.ArrayList;
import java.util.Collections;

public class WordFitActivity extends SearchActivityBase {


    @Override
    protected String[] getIntro() {
        return new String[]{
                "How to use Wordfit",
                "Use wordfit to search for words when you already have some of the letters.",
                "Enter the letters you already have and use . for the ones you don't. For example \"Cro.sword\""
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

        WordFitAsync wordfitAsync = new WordFitAsync() ;
        wordfitAsync.execute(searchTerm);
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

    private void displaySearchResults(ArrayList<String> words) {
        if (words.size() == 0) {
            displayError("No results found", "");
            return;
        }

        Collections.reverse(words);

        for (String clue: words) {
            WordView v = new WordView(getApplicationContext(), this);
            Word word = new Word(clue);
            v.displayWord(word);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 15, 0, 15);
            resultsPanel.addView(v, 0, params);
        }
    }

    private class WordFitAsync extends AsyncTask<String,Void,String> {
        ArrayList<String> answers = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            animator.start();
            resultsPanel.removeAllViews();
            searchButton.setClickable(false);
        }

        @Override
        protected String doInBackground(String... clue) {
            answers = getDictionary().wordfit(clue[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            animator.stop();
            searchButton.setClickable(true);
            displaySearchResults(answers);
        }
    }
}
