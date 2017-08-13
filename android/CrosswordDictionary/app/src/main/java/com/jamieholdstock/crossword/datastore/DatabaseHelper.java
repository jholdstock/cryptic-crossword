package com.jamieholdstock.crossword.datastore;

import android.content.Context;
import android.database.Cursor;

import com.jamieholdstock.crossword.WordList;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "cryptic_crossword_dictionary.db";
    private static final int DATABASE_VERSION = 7;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }

    public WordList getAllWords() {
        return runQuery("SELECT * FROM Word;");
    }

    public WordList getAcrosticIndicators() {
        return runQuery("SELECT * FROM Word WHERE Word.Acrostic = 1;");
    }

    public WordList getAnagramIndicators() {
        return runQuery("SELECT * FROM Word WHERE Word.Anagram = 1;");
    }

    public WordList getHiddenWordIndicators() {
        return runQuery("SELECT * FROM Word WHERE Word.HiddenWord = 1;");
    }

    public WordList getHomophoneIndicators() {
        return runQuery("SELECT * FROM Word WHERE Word.Homophone = 1;");
    }

    public WordList getDeletionIndicators() {
        return runQuery("SELECT * FROM Word WHERE Word.Deletion = 1;");
    }

    public WordList getEndDeletionIndicators() {
        return runQuery("SELECT * FROM Word WHERE Word.DeletionEnd = 1 OR Word.DeletionStart = 1 OR Word.DeletionStartEnd = 1 ;");
    }

    public WordList getMidDeletionIndicators() {
        return runQuery("SELECT * FROM Word WHERE Word.DeletionMiddle = 1;");
    }

    public WordList getReversalIndicators() {
        return runQuery("SELECT * FROM Word WHERE Word.Reversal = 1;");
    }

    public WordList getReversalAcrossIndicators() {
        return runQuery("SELECT * FROM Word WHERE Word.ReversalAcross = 1;");
    }

    public WordList getReversalDownIndicators() {
        return runQuery("SELECT * FROM Word WHERE Word.ReversalDown = 1;");
    }

    private WordList runQuery(String query) {
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        WordTableRow tr = new WordTableRow(cursor);
        cursor.close();
        return tr.getWordList();
    }
}
