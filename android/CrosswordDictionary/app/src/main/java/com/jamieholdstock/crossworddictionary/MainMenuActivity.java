package com.jamieholdstock.crossworddictionary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        DatabaseHelper myDbHelper = new DatabaseHelper(getBaseContext());

        WordTableRow row = myDbHelper.getAllWords();

        ListView list = (ListView) findViewById(R.id.listView);
        WordsAdapter adapter = new WordsAdapter(this, row.getWordList());

        list.setAdapter(adapter);
    }

}
