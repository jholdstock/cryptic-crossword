package com.jamieholdstock.crossword.datastore;

import android.database.Cursor;

import com.jamieholdstock.crossword.IndicatorType;
import com.jamieholdstock.crossword.Word;
import com.jamieholdstock.crossword.WordList;

public class WordTableRow {
    private WordList list;

    public WordTableRow(Cursor cursor) {
        list = new WordList();
        if (cursor.moveToFirst()) {
            do {
                String s = cursor.getString(cursor.getColumnIndex("Word"));

                Word word = list.getWord(s);
                if (word == null){
                    word = new Word(s);

                    for (IndicatorType type : IndicatorType.values()) {
                        String columnName = type.getColumnName();
                        int i = cursor.getInt(cursor.getColumnIndex(columnName));
                        if (i == 1) {
                            word.addIndicator(type);
                        }
                    }

                    list.add(word);
                }

                String charade = cursor.getString(cursor.getColumnIndex("Charade"));
                if (charade != null) {
                    word.addCharade(charade);
                }
            } while (cursor.moveToNext());
        }
        list.sort();
    }

    public WordList getWordList() {
        return list;
    }
}
