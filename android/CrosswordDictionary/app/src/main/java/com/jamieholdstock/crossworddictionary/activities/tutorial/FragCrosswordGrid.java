package com.jamieholdstock.crossworddictionary.activities.tutorial;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jamieholdstock.crossworddictionary.R;

public class FragCrosswordGrid extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_crossword_grid, container, false);
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
