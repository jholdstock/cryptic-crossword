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

public class FragCrosswordGrid extends TutorialFragment {

    public FragCrosswordGrid(int layoutId) {
        super(layoutId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView3);
        imageView.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.grid);
        Bitmap bitmap = ((BitmapDrawable) draw).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                (int) (bitmap.getWidth() * 0.5), (int) (bitmap.getHeight() * 0.5), false);
        draw = new BitmapDrawable(getResources(), bitmapResized);
        imageView.setImageDrawable(draw);
        return rootView;
    }
}
