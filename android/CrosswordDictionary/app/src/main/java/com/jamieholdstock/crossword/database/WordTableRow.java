package com.jamieholdstock.crossword.database;

import android.database.Cursor;

import com.jamieholdstock.crossword.Word;
import com.jamieholdstock.crossword.WordList;

import java.util.ArrayList;
import java.util.List;

public class WordTableRow {
    private List<String> indicators;
    private WordList items;

    public WordTableRow(Cursor cursor) {
        items = new WordList();
        cursor.moveToFirst();
        do {
            String word = cursor.getString(cursor.getColumnIndex("Word"));
            String abbr = cursor.getString(cursor.getColumnIndex("Abbr"));
            indicators = new ArrayList<>();

            isIndicator(cursor, "Acrostic");
            isIndicator(cursor, "Anagram");
            isIndicator(cursor, "DeletionEnd");
            isIndicator(cursor, "DeletionMiddle");
            isIndicator(cursor, "Deletion");
            isIndicator(cursor, "DeletionStart");
            isIndicator(cursor, "DeletionStartEnd");
            isIndicator(cursor, "HiddenWord");
            isIndicator(cursor, "Homophone");
            isIndicator(cursor, "Reversal");
            isIndicator(cursor, "ReversalAcross");
            isIndicator(cursor, "ReversalDown");

            items.add(new Word(word, indicators, abbr));
        } while (cursor.moveToNext());

        items.sort();
    }

    public WordList getWordList() {
        return items;
    }

    private void isIndicator(Cursor cursor, String indicatorType) {
        int i = cursor.getInt(cursor.getColumnIndex(indicatorType));
        if (i == 1) {
            indicators.add(indicatorType);
        }
    }
}
