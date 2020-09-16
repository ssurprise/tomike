package com.skx.common.imageloader;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.gif.GifDrawable;

public class SGifDrawable extends GifDrawable {
    public SGifDrawable(Context context, GifDecoder gifDecoder, BitmapPool bitmapPool, Transformation<Bitmap> frameTransformation, int targetFrameWidth, int targetFrameHeight, Bitmap firstFrame) {
        super(context, gifDecoder, bitmapPool, frameTransformation, targetFrameWidth, targetFrameHeight, firstFrame);
    }

    public SGifDrawable(Context context, GifDecoder gifDecoder, Transformation<Bitmap> frameTransformation, int targetFrameWidth, int targetFrameHeight, Bitmap firstFrame) {
        super(context, gifDecoder, frameTransformation, targetFrameWidth, targetFrameHeight, firstFrame);
    }
}
