package com.jamieholdstock.crossword;

import android.content.Context;

import com.jamieholdstock.crossword.datastore.DatabaseHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Word {
    private String word;
    private ArrayList<IndicatorType> indicators;
    private ArrayList<String> charades;

    public Word(String word) {
        this.word = word;
        this.charades = new ArrayList<String>();
        this.indicators = new ArrayList<IndicatorType>();
    }

    public String getWord() {
        return word;
    }
    public List<IndicatorType> getIndicators() {
        return indicators;
    }

    public void addIndicator(IndicatorType type) {
        this.indicators.add(type);
    }

    public ArrayList<String> getCharades() {
        return charades;
    }

    public void addCharade(String charade) {
        this.charades.add(charade);
    }

    public boolean hasIndicators() {
        return indicators.size() > 0;
    }
}
