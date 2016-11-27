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
    protected int getLayoutId() {
        return R.layout.activity_search_indicators;
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
