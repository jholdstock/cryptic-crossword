package com.jamieholdstock.crossworddictionary.activities.tutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jamieholdstock.crossworddictionary.R;

public class FragCrypticClue extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_cryptic_clue, container, false);
        return rootView;
    }
}
