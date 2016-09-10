package com.jamieholdstock.crossworddictionary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        DatabaseHelper myDbHelper = new DatabaseHelper(getBaseContext());

        Cursor cursor = myDbHelper.getAllWords();
        WordList items = new WordList();

        if (cursor.moveToFirst()) {
            do {
                String word = cursor.getString(cursor.getColumnIndex("Word"));
                items.add(new Word(word, null));
            } while (cursor.moveToNext());
        }
        L.l("read from Db");

        items.sort();

        ListView list = (ListView) findViewById(R.id.listView);
        WordsAdapter adapter = new WordsAdapter(this, items);

        list.setAdapter(adapter);
    }
}
