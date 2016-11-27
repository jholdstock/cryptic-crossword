package com.jamieholdstock.crossword.activities;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.WordList;
import com.jamieholdstock.crossword.adapters.SearchIndicatorsAdapter;
import com.jamieholdstock.crossword.datastore.DatabaseHelper;

public class SearchIndicatorsActivity extends SearchActivityBase {

    private ListView listView;
    private SearchIndicatorsAdapter myAppAdapter;

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
    protected void onCreate() {
        DatabaseHelper myDbHelper = new DatabaseHelper(getBaseContext());
        WordList allWords = myDbHelper.getAllWords();
        myAppAdapter = new SearchIndicatorsAdapter(allWords, SearchIndicatorsActivity.this);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myAppAdapter);

        final EditText searchBox = (EditText) findViewById(R.id.search_box);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                findViewById(R.id.help_panel).setVisibility(View.GONE);
                myAppAdapter.filter(charSequence.toString().trim());
                listView.invalidate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
        });
    }

}
