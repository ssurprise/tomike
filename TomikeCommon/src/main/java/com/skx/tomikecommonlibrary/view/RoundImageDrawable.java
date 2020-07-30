package com.skx.tomikecommonlibrary.view;


import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

/**
 * @author shiguotao
 * 
 *         圆角图片Drawable
 */
public class RoundImageDrawable extends Drawable {

	private Paint mPaint;
	private Bitmap mBitmap;

	private RectF rectF;
	private float rx;
	private float ry;

	/**
	 * @param bitmap
	 * @param rx
	 *            水平圆角半径
	 * @param ry
	 *            垂直圆角半径
	 */
	public RoundImageDrawable(Bitmap bitmap, float rx, float ry) {
		this.rx = rx;
		this.ry = ry;
		mBitmap = bitmap;
		BitmapShader bitmapShader = new BitmapShader(mBitmap, TileMode.CLAMP, TileMode.CLAMP);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);// 抗锯齿
		mPaint.setShader(bitmapShader);
	}

	@Override
	public void setBounds(int left, int top, int right, int bottom) {
		super.setBounds(left, top, right, bottom);
		// 设置绘制的范围
		rectF = new RectF(left, top, right, bottom);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRoundRect(rectF, rx, ry, mPaint);
	}

	@Override
	public int getIntrinsicWidth() {
		return mBitmap.getWidth();
	}

	@Override
	public int getIntrinsicHeight() {
		return mBitmap.getHeight();
	}

	@Override
	public void setAlpha(int alpha) {
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

}
