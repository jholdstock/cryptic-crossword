package com.jamieholdstock.crossworddictionary;

import android.content.Context;
import android.database.Cursor;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "cryptic_crossword_dictionary.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getAllWords() {
        return this.getReadableDatabase().rawQuery("SELECT * FROM 'Word'", null);
    }
}
