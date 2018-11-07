package com.skx.tomikecommonlibrary.imageloader.transform;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * Returns the image with its original size if its dimensions match or are smaller
 * than the target's, couple with {@link android.widget.ImageView.ScaleType#CENTER_INSIDE}
 * in order to center it in Target. If not, then it is scaled so that one of the dimensions of
 * the image will be equal to the given dimension and the other will be less than the given
 * dimension (maintaining the image's aspect ratio).
 */
public final class CenterInside implements Transformation {
    private static final String ID = "com.skx.tomikecommonlibrary.imageloader.transform.CenterInside";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    @Override
    public boolean equals(Object o) {
        return o instanceof CenterInside;
    }

    @Override
    public Bitmap transform(@NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return null;
    }

    @Override
    public byte[] diskCacheKey() {
        return ID_BYTES;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}


