package com.jamieholdstock.crossword.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SwipeAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> frags;

    public SwipeAdapter(FragmentManager fm, ArrayList<Fragment> frags) {
        super(fm);
        this.frags = frags;
    }

    @Override
    public Fragment getItem(int position) {
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return frags.size();
    }
}
