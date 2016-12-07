package com.jamieholdstock.crossword.datastore;

import android.content.res.Resources;

import com.jamieholdstock.crossword.L;
import com.jamieholdstock.crossword.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FullDictionary {

    private ArrayList<String[]> dictionaryByLetter= new ArrayList<String[]>() ;
    private ArrayList<String> dictionary = new ArrayList<String>() ;
    private int hashMapSize = 315000 ;
    private HashMap<String, ArrayList<String>> dictionaryHM = new HashMap<String, ArrayList<String>>(hashMapSize);

    public FullDictionary(Resources res) {
        dictionaryByLetter.add(res.getStringArray(R.array.wordsA));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsB));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsC));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsD));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsE));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsF));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsG));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsH));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsI));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsJ));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsK));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsL));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsM));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsN));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsO));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsP));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsQ));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsR));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsS));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsT));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsU));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsV));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsW));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsX));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsY));
        dictionaryByLetter.add(res.getStringArray(R.array.wordsZ));

        for (int i = 0 ; i < 26 ; i++) {
            for (int j =0 ; j < dictionaryByLetter.get(i).length ; j ++) {
                String word = dictionaryByLetter.get(i)[j];
                if (word != null) {
                    dictionary.add(word);
                    // Sort the string and add it to the ordered dictionary
                    String sortedWord = sortWord(word);
                    // Add to the HashMap
                    if (dictionaryHM.containsKey(sortedWord)) {
                        ArrayList<String> possibleWords = dictionaryHM.get(sortedWord);
                        possibleWords.add(word);
                        dictionaryHM.put(sortedWord, possibleWords);
                    } else {
                        ArrayList<String> possibleWords = new ArrayList<String>();
                        possibleWords.add(word);
                        dictionaryHM.put(sortedWord, possibleWords);
                    }
                }
            }
        }
        L.l("end");
    }

    private String sortWord(String input) {
        // Sort the letters into alphabetical order
        char[] wordChars = input.toLowerCase().toCharArray();
        Arrays.sort(wordChars);
        return new String(wordChars);
    }

    public ArrayList<String> searchAnagram(String input) {
        input = input.replaceAll(" ", "");
        ArrayList<String> wordList = new ArrayList<>();

        String inputSorted = sortWord(input);

        if (dictionaryHM.containsKey(inputSorted)) {
            ArrayList<String> answers = dictionaryHM.get(inputSorted);
            for (String answer : answers) {
                wordList.add(answer);
            }
        }

        return wordList;
    }
}
