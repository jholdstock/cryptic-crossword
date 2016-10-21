package com.jamieholdstock.crossworddictionary.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.jamieholdstock.crossworddictionary.R;
import com.jamieholdstock.crossworddictionary.WordList;
import com.jamieholdstock.crossworddictionary.adapters.SearchAdapter;
import com.jamieholdstock.crossworddictionary.database.DatabaseHelper;

public class SearchActivity extends AppCompatActivity {

    private ListView listView;
    private SearchAdapter myAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        DatabaseHelper myDbHelper = new DatabaseHelper(getBaseContext());
        WordList allWords = myDbHelper.getAllWords();
        myAppAdapter = new SearchAdapter(allWords, SearchActivity.this);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myAppAdapter);

        EditText search = (EditText) findViewById(R.id.search_box);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myAppAdapter.filter(charSequence.toString().trim());
                listView.invalidate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

   @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
