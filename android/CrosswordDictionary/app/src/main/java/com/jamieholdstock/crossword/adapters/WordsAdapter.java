package com.jamieholdstock.crossword.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.Word;

import java.util.List;

public class WordsAdapter extends ArrayAdapter<Word> {

    public WordsAdapter(Context context, List<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_word, parent, false);

        final Word word = this.getItem(position);

        TextView text = (TextView) convertView.findViewById(R.id.the_word);
        text.setText(word.getWord());

        return convertView;
    }
}
