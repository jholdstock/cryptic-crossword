package com.jamieholdstock.crossword;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private String word;
    private List<IndicatorType> indicators;
    private String charades;

    public Word(String word, ArrayList<IndicatorType> indicators, String charades) {
        this.word = word;
        this.indicators = indicators;
        this.charades = charades;
    }

    public String getWord() {
        return word;
    }
    public List<IndicatorType> getIndicators() {
        return indicators;
    }
    public String getCharades() {
        return charades;
    }

    public boolean hasCharades() {
        return charades != null;
    }
    public boolean hasIndicators() {
        return indicators != null && indicators.size() > 0;
    }
}
