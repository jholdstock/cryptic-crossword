package com.jamieholdstock.crossword.activities.utilities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class ImageBugFixer {

    public static void fix(int imageViewId, int imageId, View rootView, Resources resources) {
        ImageView imageView = (ImageView) rootView.findViewById(imageViewId);
        imageView.setImageResource(0);
        Drawable draw = resources.getDrawable(imageId);
        Bitmap bitmap = ((BitmapDrawable) draw).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                (int) (bitmap.getWidth() * 0.5), (int) (bitmap.getHeight() * 0.5), false);
        draw = new BitmapDrawable(resources, bitmapResized);
        imageView.setImageDrawable(draw);
    }
}
