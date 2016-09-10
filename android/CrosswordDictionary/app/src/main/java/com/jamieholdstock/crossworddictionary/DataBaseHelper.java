package com.jamieholdstock.crossworddictionary;

import android.content.Context;
import android.database.Cursor;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "cryptic_crossword_dictionary.db";
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }

    public Cursor getAllWords() {
        return this.getReadableDatabase().rawQuery("SELECT * FROM 'Word';", null);
    }
}
