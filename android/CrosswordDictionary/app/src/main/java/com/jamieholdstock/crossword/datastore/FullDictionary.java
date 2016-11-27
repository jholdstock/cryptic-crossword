package com.jamieholdstock.crossword.datastore;

import android.app.Activity;

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

    public FullDictionary(Activity activity) {
        L.l("Start");
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsA));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsB));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsC));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsD));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsE));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsF));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsG));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsH));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsI));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsJ));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsK));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsL));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsM));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsN));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsO));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsP));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsQ));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsR));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsS));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsT));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsU));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsV));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsW));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsX));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsY));
        dictionaryByLetter.add(activity.getResources().getStringArray(R.array.wordsZ));

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

    public void searchAnagram(String input) {
//        // Order letters, then check if hashset contains a match. If so, print the output options
//        // Sort letters
//        String inputSorted = sortWord(input);
//        // Check if in HashMap
//        if (dictionaryHM.containsKey(inputSorted)) {
//            Log.d(LOG_TAG, "Match found for: " + inputSorted);
//            ArrayList<String> answers = dictionaryHM.get(inputSorted);
//            // Add results to resultsLinearLayout
//            resultCount = 0 ;   // Set to 0 to allow for correct timing delays to entry animation
//            for (String answer : answers) {
//                Log.d(LOG_TAG, "An answer for " + input + " is: " + answer);
//                addToResults(answer);
//            }
//        } else {
//            Log.d(LOG_TAG, "No match found for: " + inputSorted + ", which originally came from " + input);
//            addToResults(getString(R.string.no_match_found), false);
//        }
    }
}
