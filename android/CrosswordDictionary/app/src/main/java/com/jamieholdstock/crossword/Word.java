package com.jamieholdstock.crossword;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private String word;
    private List<IndicatorType> indicators;
    private String abbreviations;

    public Word(String word) {
        this(word, null, null);
    }

    public Word(String word, ArrayList<IndicatorType> indicators, String abbreviations) {
        this.word = word;
        this.indicators = indicators;
        this.abbreviations = abbreviations;
    }

    public String getWord() {
        return word;
    }
    public List<IndicatorType> getIndicators() {
        return indicators;
    }
    public String getAbbreviations() {
        return abbreviations;
    }

    public boolean hasAbbreviations() {
        return abbreviations != null;
    }
    public boolean hasIndicators() {
        return indicators != null && indicators.size() > 0;
    }
}
