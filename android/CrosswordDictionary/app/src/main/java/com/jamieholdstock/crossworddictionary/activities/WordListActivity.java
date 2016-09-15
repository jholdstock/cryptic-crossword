package com.jamieholdstock.crossworddictionary.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jamieholdstock.crossworddictionary.R;
import com.jamieholdstock.crossworddictionary.adapters.WordsAdapter;
import com.jamieholdstock.crossworddictionary.database.DatabaseHelper;
import com.jamieholdstock.crossworddictionary.database.WordTableRow;

public class WordListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        DatabaseHelper myDbHelper = new DatabaseHelper(getBaseContext());

        WordTableRow row = myDbHelper.getAllWords();

        ListView list = (ListView) findViewById(R.id.listView);
        WordsAdapter adapter = new WordsAdapter(this, row.getWordList());

        list.setAdapter(adapter);
    }

}
