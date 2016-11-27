package com.jamieholdstock.crossword.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jamieholdstock.crossword.Word;
import com.jamieholdstock.crossword.WordList;
import com.jamieholdstock.crossword.views.WordView;

import java.util.Locale;

public class SearchIndicatorsAdapter extends BaseAdapter {

    private Context context;
    private WordList currentlyDisplayedList;
    private WordList fullList;

    public SearchIndicatorsAdapter(WordList allWords, Context context) {
        this.context = context;

        currentlyDisplayedList = new WordList();

        fullList = new WordList();
        fullList.addAll(allWords);
    }

    @Override
    public int getCount() {
        return currentlyDisplayedList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new WordView(context);
        }

        WordView wv = (WordView) convertView;
        Word word = currentlyDisplayedList.get(position);
        wv.displayWord(word);

        return wv;
    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        currentlyDisplayedList.clear();
        if (charText.length() > 0) {
            for (Word word : fullList) {
                String theWord = word.getWord().toLowerCase(Locale.getDefault());
                if (theWord.startsWith(charText)) {
                    currentlyDisplayedList.add(word);
                }
            }
            for (Word word : fullList) {
                String theWord = word.getWord().toLowerCase(Locale.getDefault());
                if (theWord.contains(charText) && theWord.startsWith(charText) == false) {
                    currentlyDisplayedList.add(word);
                }
            }
        }
        notifyDataSetChanged();
    }
}
