package com.jamieholdstock.crossworddictionary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Locale;

public class SearchAdapter extends BaseAdapter {

    public Context context;
    public WordList parkingList;
    public WordList arraylist;

    public SearchAdapter(WordList apps, Context context) {
        this.parkingList = apps;
        this.context = context;
        arraylist = new WordList();
        arraylist.addAll(parkingList);
    }

    @Override
    public int getCount() {
        return parkingList.size();
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
        Word word = parkingList.get(position);
        wv.displayWord(word);

        return wv;
    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        parkingList.clear();
        if (charText.length() == 0) {
            parkingList.addAll(arraylist);
        } else {
            for (Word word : arraylist) {
                String theWord = word.getWord().toLowerCase(Locale.getDefault());
                if (theWord.startsWith(charText)) {
                    parkingList.add(word);
                }
            }
            for (Word word : arraylist) {
                String theWord = word.getWord().toLowerCase(Locale.getDefault());
                if (theWord.contains(charText) && theWord.startsWith(charText) == false) {
                    parkingList.add(word);
                }
            }
        }
        notifyDataSetChanged();
    }
}
