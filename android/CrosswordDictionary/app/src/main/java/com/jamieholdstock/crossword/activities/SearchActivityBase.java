package com.jamieholdstock.crossword.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.jamieholdstock.crossword.R;

public abstract class SearchActivityBase extends CrosswordBaseActivity {

    protected abstract int getLayoutId();
    protected abstract int getBackgroundColor();
    protected abstract String getSearchHint();
    protected abstract void onCreate();
    protected abstract boolean isSeachButtonVisible();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        attachDeleteButtonListener();
        setBackgroundColor();
        setSearchHint();
        hideSearchButton();
        onCreate();
    }

    private void hideSearchButton() {
        if (isSeachButtonVisible() == false) {
            View view = findViewById(R.id.search_button);
            view.setVisibility(View.GONE);
        }
    }

    private void setSearchHint() {
        final EditText searchBox = (EditText) findViewById(R.id.search_box);
        searchBox.setHint(getSearchHint());
    }

    private void setBackgroundColor() {
        View root = findViewById(R.id.activity_root);
        root.setBackgroundColor(ContextCompat.getColor(getBaseContext(), getBackgroundColor()));
    }

    private void attachDeleteButtonListener() {
        Button clearButton = (Button) findViewById(R.id.clear_button);
        final EditText searchBox = (EditText) findViewById(R.id.search_box);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBox.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(searchBox, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }
}
