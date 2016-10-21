package com.jamieholdstock.crossworddictionary.database;

import android.content.Context;
import android.database.Cursor;

import com.jamieholdstock.crossworddictionary.WordList;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "cryptic_crossword_dictionary.db";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }

    public WordList getAllWords() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM 'Word';", null);
        WordTableRow tr = new WordTableRow(cursor);
        cursor.close();
        return tr.getWordList();
    }
}
