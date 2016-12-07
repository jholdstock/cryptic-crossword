package com.jamieholdstock.crossword.datastore;

import android.database.Cursor;

import com.jamieholdstock.crossword.IndicatorType;
import com.jamieholdstock.crossword.Word;
import com.jamieholdstock.crossword.WordList;

import java.util.ArrayList;

public class WordTableRow {
    private ArrayList<IndicatorType> indicators;
    private WordList items;

    public WordTableRow(Cursor cursor) {
        items = new WordList();
        cursor.moveToFirst();
        do {
            String word = cursor.getString(cursor.getColumnIndex("Word"));
            String abbr = cursor.getString(cursor.getColumnIndex("Abbr"));
            indicators = new ArrayList<>();

            for (IndicatorType type : IndicatorType.values()) {
                isIndicator(cursor, type);
            }

            items.add(new Word(word, indicators, abbr));
        } while (cursor.moveToNext());

        items.sort();
    }

    public WordList getWordList() {
        return items;
    }

    private void isIndicator(Cursor cursor, IndicatorType type) {
        String columnName = type.getColumnName();
        int i = cursor.getInt(cursor.getColumnIndex(columnName));
        if (i == 1) {
            indicators.add(type);
        }
    }
}
