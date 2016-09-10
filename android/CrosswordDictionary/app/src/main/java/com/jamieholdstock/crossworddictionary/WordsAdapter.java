package com.jamieholdstock.crossworddictionary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WordsAdapter extends ArrayAdapter<Word> {

    public WordsAdapter(Context context, List<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DisplayWordActivity.class);
                    TextView text = (TextView) v.findViewById(android.R.id.text1);
                    String message = text.getText().toString();
                    intent.putExtra("text", message);
                    v.getContext().startActivity(intent);
                }
            });
        }

        TextView text = (TextView) convertView.findViewById(android.R.id.text1);
        Word word = this.getItem(position);
        text.setText(word.getWord());

        return convertView;
    }
}
