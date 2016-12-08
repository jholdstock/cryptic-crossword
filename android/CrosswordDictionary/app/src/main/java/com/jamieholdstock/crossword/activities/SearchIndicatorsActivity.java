package com.jamieholdstock.crossword.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.Word;
import com.jamieholdstock.crossword.WordList;
import com.jamieholdstock.crossword.datastore.DatabaseHelper;
import com.jamieholdstock.crossword.views.WordView;

import java.util.Collections;

public class SearchIndicatorsActivity extends SearchActivityBase {

    private WordList allWords;

    @Override
    protected String[] getIntro() {
        return new String[]{
                "How to use the indicator lookup",
                "Search for words in your clue to see if they might indicate which wordplay device is in use.",
                "Remember that words in your clue may be used differently to how this lookup suggests. Treat this as a rough guide and a source of inspiration, not a rule book."
        };
    }

    @Override
    protected int getBackgroundColor() {
        return R.color.Alizarin;
    }

    @Override
    protected String getSearchHint() {
        return "Indicator...";
    }

    @Override
    protected void onSearchButtonPressed(View v) {
        String searchTerm = searchBox.getText().toString();
        if (searchTerm.trim().equals("")) {
            return;
        }

        resultsPanel.removeAllViews();

        SearchIndicatorsAsync searchIndicatorsAsync = new SearchIndicatorsAsync();
        searchIndicatorsAsync.execute(searchTerm);
    }

    private void displaySearchResults(Context context, WordList solvedClues) {
        if (solvedClues.size() == 0) {
            displayError("No results found", "Check spelling carefully");
            return;
        }

        Collections.reverse(solvedClues);

        for (Word word: solvedClues) {
            WordView v = new WordView(context);

            v.displayWord(word);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 15, 0, 15);
            resultsPanel.addView(v, 0, params);
        }
    }

    private class SearchIndicatorsAsync extends AsyncTask<String,Void,String> {

        private WordList answers;

        @Override
        protected void onPreExecute() {
            animator.start();
            resultsPanel.removeAllViews();
            searchButton.setClickable(false);
        }
        @Override
        protected String doInBackground(String... input) {
            if (allWords == null) {
                DatabaseHelper myDbHelper = new DatabaseHelper(getBaseContext());
                allWords = myDbHelper.getAllWords();
            }

            answers = allWords.filter(input[0]);
            return null ;
        }

        @Override
        protected void onPostExecute(String result) {
            animator.stop();
            searchButton.setClickable(true);
            displaySearchResults(getBaseContext(), answers);
        }
    }
}
