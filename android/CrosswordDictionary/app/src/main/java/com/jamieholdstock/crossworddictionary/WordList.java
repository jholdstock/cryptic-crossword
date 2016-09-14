package com.jamieholdstock.crossworddictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordList extends ArrayList<Word> {

    public void sort() {
        Collections.sort(this, new Comparator<Word>(){
            public int compare(Word emp1, Word emp2) {
                return emp1.getWord().compareToIgnoreCase(emp2.getWord());
            }
        });
    }
}
