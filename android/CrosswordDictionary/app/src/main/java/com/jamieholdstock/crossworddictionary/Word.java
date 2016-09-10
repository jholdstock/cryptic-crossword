package com.jamieholdstock.crossworddictionary;

import java.util.Map;

public class Word {
    private String word;
    private Map<String, Boolean> indicators;

    public Word(String word, Map<String, Boolean> indicators) {
        this.word = word;
        this.indicators = indicators;
    }

    public String getWord() {
        return word;
    }
}
