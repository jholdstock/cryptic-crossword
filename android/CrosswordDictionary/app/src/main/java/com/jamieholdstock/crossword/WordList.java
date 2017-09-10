package com.jamieholdstock.crossword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class WordList extends ArrayList<Word> {

    public void sort() {
        Collections.sort(this, new Comparator<Word>(){
            public int compare(Word emp1, Word emp2) {
                return emp1.getWord().compareToIgnoreCase(emp2.getWord());
            }
        });
    }

    public Word getWord(String word) {
        for (Word existingWord : this) {
            if (existingWord.getWord().equals(word)) {
                return existingWord;
            }
        }
        return null;
    }

    public WordList filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault()).replaceAll("'", "");

        WordList list = new WordList();

        if (charText.length() > 0) {
            for (Word word : this) {
                String theWord = word.getWord().toLowerCase(Locale.getDefault()).replaceAll("'", "");
                if (theWord.startsWith(charText)) {
                    list.add(word);
                }
            }
            for (Word word : this) {
                String theWord = word.getWord().toLowerCase(Locale.getDefault()).replaceAll("'", "");
                if (theWord.contains(charText) && theWord.startsWith(charText) == false) {
                    list.add(word);
                }
            }
        }

        return list;
    }
}
