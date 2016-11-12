package com.jamieholdstock.crossword.activities.tutorial.basics;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jamieholdstock.crossword.R;
import com.jamieholdstock.crossword.activities.tutorial.TutorialFragment;
import com.jamieholdstock.crossword.activities.utilities.ImageBugFixer;

public class FragCrosswordGrid extends TutorialFragment {

    public FragCrosswordGrid(int layoutId) {
        super(layoutId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ImageBugFixer.fix(R.id.imageView3, R.drawable.grid, rootView, getResources());

        return rootView;
    }
}
