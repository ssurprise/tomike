package com.skx.tomike.customview;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

/**
 * @author shiguotao
 * 
 *         圆形Drawable
 */
public class CircleImageDrawable extends Drawable {
	private Paint mPaint;
	private Bitmap mBitmap;
	private int mWidth;

	public CircleImageDrawable(Bitmap bitmap) {
		super();
		mBitmap = bitmap;
		mPaint = new Paint();
		BitmapShader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
		mPaint.setAntiAlias(true);
		mPaint.setShader(bitmapShader);
		mWidth = Math.min(mBitmap.getWidth(), mBitmap.getHeight());
	}

	@Override
	public void draw(Canvas canvas) {
		/**
		 * cx：圆心的x坐标。 cy：圆心的y坐标。 radius：圆的半径。 paint：绘制时所使用的画笔。
		 * */
		canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, mPaint);
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub
		mPaint.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		mPaint.setColorFilter(cf);
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}

	@Override
	public int getIntrinsicWidth() {
		return mWidth;
	}

	@Override
	public int getIntrinsicHeight() {
		return mWidth;
	}

}
