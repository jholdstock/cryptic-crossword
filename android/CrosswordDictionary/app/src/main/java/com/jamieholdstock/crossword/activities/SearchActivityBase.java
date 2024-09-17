package com.jamieholdstock.crossword.activities;

import android.content.Context;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.utilities.ImageBugFixer;
import com.jamieholdstock.crossword.activities.utilities.LoadingAnimator;
import com.jamieholdstock.crossword.datastore.FullDictionary;

public abstract class SearchActivityBase extends CrosswordBaseActivity {

    protected abstract int getBackgroundColor();
    protected abstract String getSearchHint();
    protected abstract void onSearchButtonPressed(View v);
    protected abstract String[] getIntro();

    protected String getSearchButtonText() {
        return "Solve";
    }

    private static FullDictionary fullDictionary;
    protected LoadingAnimator animator;
    protected EditText searchBox;
    protected Button searchButton;
    protected LinearLayout resultsPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        resultsPanel = (LinearLayout) findViewById(R.id.results_panel);
        searchBox = (EditText) findViewById(R.id.search_box);
        searchButton = (Button) findViewById(R.id.search_button);

        attachSearchBoxActionListener();
        attachSearchButtonListener();
        attachDeleteButtonListener();
        setBackgroundColor();
        setSearchHint();
        setIntroText();
        setButtonText();

        animator = new LoadingAnimator(searchButton);
    }

    protected FullDictionary getDictionary() {
        if (fullDictionary == null) {
            fullDictionary = new FullDictionary(getResources());
        }
        return fullDictionary;
    }

    private void attachSearchBoxActionListener() {
        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            searchButton.callOnClick();
            return true;
            }
        });
    }
    
    private void setButtonText() {
        Button search_button = (Button) findViewById(R.id.search_button);
        search_button.setText(getSearchButtonText());
    }

    private void attachSearchButtonListener() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonPressed(v);
            }
        });
    }

    private void setIntroText() {
        TextView intro1 = (TextView) findViewById(R.id.intro1);
        intro1.setText(getIntro()[0]);
        TextView intro2 = (TextView) findViewById(R.id.intro2);
        intro2.setText(getIntro()[1]);
        TextView intro3 = (TextView) findViewById(R.id.intro3);
        intro3.setText(getIntro()[2]);
    }

    private void setSearchHint() {
        searchBox.setHint(getSearchHint());
    }

    private void setBackgroundColor() {
        View root = findViewById(R.id.activity_root);
        root.setBackgroundColor(ContextCompat.getColor(getBaseContext(), getBackgroundColor()));
    }

    private void attachDeleteButtonListener() {
        Button clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            searchBox.setText("");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(searchBox, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    protected void displayError(String topMessage, String bottomMessage) {
        LayoutInflater lInf = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = lInf.inflate(R.layout.view_warning, null);
        TextView topText = (TextView) v.findViewById(R.id.top_message);
        topText.setText(topMessage);

        TextView bottomText = (TextView) v.findViewById(R.id.bottom_message);
        bottomText.setText(bottomMessage);

        resultsPanel.addView(v, 0, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ImageBugFixer.fix(R.id.error_img, R.mipmap.warning, v, getResources());
    }
}
