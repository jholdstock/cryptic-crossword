package com.jamieholdstock.crossworddictionary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        DataBaseHelper myDbHelper = new DataBaseHelper(getBaseContext());

        Cursor cursor = myDbHelper.getAllWords();
        List<String> items = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                String word = cursor.getString(cursor.getColumnIndex("Word"));
                items.add(word);
            } while (cursor.moveToNext());
        }
        L.l("read from Db");

        ListView list = (ListView) findViewById(R.id.listView);
        WordsAdapter adapter = new WordsAdapter(this, items);

        list.setAdapter(adapter);
    }
}
