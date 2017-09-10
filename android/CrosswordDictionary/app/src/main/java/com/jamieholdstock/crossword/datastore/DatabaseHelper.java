package com.jamieholdstock.crossword.datastore;

import android.content.Context;
import android.database.Cursor;

import com.jamieholdstock.crossword.IndicatorType;
import com.jamieholdstock.crossword.Word;
import com.jamieholdstock.crossword.WordList;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHelper {

    private static final String DATABASE_NAME = "cryptic_crossword_dictionary.db";
    private static final int DATABASE_VERSION = 10;
    private Context context;

    public DatabaseHelper(Context context) {
        this.context = context;
    }

    private String constructQuery(String whereClause) {
        return "SELECT Word.*, Charade.Charade" +
        " FROM Word LEFT JOIN Charade" +
        " ON Word.Id = Charade.Word_Id " + whereClause +
        " ORDER BY WORD;";
    }

    public WordList getAllWords() {
        return runQuery(constructQuery(""));
    }

    public WordList getAcrosticIndicators() {
        return runQuery(constructQuery("WHERE Word.Acrostic = 1"));
    }

    public WordList getAnagramIndicators() {
        return runQuery(constructQuery("WHERE Word.Anagram = 1"));
    }

    public WordList getHiddenWordIndicators() {
        return runQuery(constructQuery("WHERE Word.HiddenWord = 1"));
    }

    public WordList getHomophoneIndicators() {
        return runQuery(constructQuery("WHERE Word.Homophone = 1"));
    }

    public WordList getDeletionIndicators() {
        return runQuery(constructQuery("WHERE Word.Deletion = 1"));
    }

    public WordList getEndDeletionIndicators() {
        return runQuery(constructQuery("WHERE Word.DeletionEnd = 1 OR Word.DeletionStart = 1 OR Word.DeletionStartEnd = 1"));
    }

    public WordList getMidDeletionIndicators() {
        return runQuery(constructQuery("WHERE Word.DeletionMiddle = 1"));
    }

    public WordList getReversalIndicators() {
        return runQuery(constructQuery("WHERE Word.Reversal = 1"));
    }

    public WordList getReversalAcrossIndicators() {
        return runQuery(constructQuery("WHERE Word.ReversalAcross = 1"));
    }

    public WordList getReversalDownIndicators() {
        return runQuery(constructQuery("WHERE Word.ReversalDown = 1"));
    }

    private WordList runQuery(String query) {
        SQLiteAssetHelper db_helper = new SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        db_helper.setForcedUpgrade();
        Cursor cursor = db_helper.getReadableDatabase().rawQuery(query, null);
        WordTableRow tr = new WordTableRow(cursor);
        cursor.close();
        db_helper.close();
        return tr.getWordList();
    }
}
