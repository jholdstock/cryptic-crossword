package com.jamieholdstock.crossword;

import java.io.Serializable;
import java.util.List;

public class Word implements Serializable {
    private String word;
    private List<String> indicators;
    private String abbreviations;

    public Word(String word) {
        this(word, null, null);
    }

    public Word(String word, List<String> indicators, String abbreviations) {
        this.word = word;
        this.indicators = indicators;
        this.abbreviations = abbreviations;
    }

    public String getWord() {
        return word;
    }
    public List<String> getIndicators() {
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
