package com.jamieholdstock.crossword.activities;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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

    @Override
    protected String[] getIntro() {
        return new String[]{
                "How to use the indicator lookup",
                "Search for words in your clue to see if they might indicate which wordplay device is in use.",
                "Remember that this there are thousands of potential indicator words and the word from your clue could be missing from this list. It's also possible that words in your clue are being used differently to how the lookup suggests. Treat this lookup as rough guide and a source of inspiration, not a rule book."
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
    protected boolean isSeachButtonVisible() {
        return false;
    }

    @Override
    protected void onSearchButtonPressed(View v) {}

    @Override
    protected void onCreate() {
        DatabaseHelper myDbHelper = new DatabaseHelper(getBaseContext());
        final WordList allWords = myDbHelper.getAllWords();

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                LinearLayout resultsPanel = (LinearLayout) findViewById(R.id.results_panel);
                resultsPanel.removeAllViews();

                WordList list = allWords.filter(charSequence.toString());
                displaySolvedClues(getBaseContext(), list);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void displaySolvedClues(Context context, WordList solvedClues) {
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
}
