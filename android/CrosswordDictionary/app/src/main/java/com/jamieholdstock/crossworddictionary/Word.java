package com.jamieholdstock.crossworddictionary;

import java.io.Serializable;
import java.util.List;

public class Word implements Serializable {
    private String word;
    private List<String> indicators;

    public Word(String word, List<String> indicators) {
        this.word = word;
        this.indicators = indicators;
    }

    public String getWord() {
        return word;
    }

    public List<String> getIndicators() {
        return indicators;
    }
}
