package com.skx.tomike.customview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Scroller;

/**
 * ImageView 2.0
 * 功能：
 * 1.实现配置缩放类型
 * 2.实现初始化位置
 * 3.图片位移动画
 *
 * @author shiguotao
 * Created on 2016/11/30.
 */
public class SkxImageView extends android.support.v7.widget.AppCompatImageView implements ViewTreeObserver.OnGlobalLayoutListener {
    private final String TAG = SkxImageView.class.getName();

    private boolean once = true;
    private Matrix mMatrix;
    private Scroller mScroller;

    /**
     * 初始化方位
     */
    private Position mPosition;
    /**
     * 扩展的ScaleType
     */
    private ScaleTypeEx mScaleTypeEx;

    /**
     * 位移偏移量X
     */
    private float mTranslateOffsetX;
    /**
     * 位移偏移量Y
     */
    private float mTranslateOffsetY;

    private float mScale = 1.0f;
    private ISmoothScrollAnimatorListener mScrollAnimatorListener;
    /**
     * 用于控制动画的标记位
     */
    private boolean mAnimatorFlag = false;

    public SkxImageView(Context context) {
        this(context, null);
    }

    public SkxImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkxImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSkxImageView(context);
    }

    private void initSkxImageView(Context context) {
        mMatrix = new Matrix();
        mScroller = new Scroller(context);
    }

    /**
     * 平滑移动动画
     *
     * @param dx       移动距离
     * @param dy       移动距离
     * @param duration 时间
     */
    public void smoothScrollAnimator(int dx, int dy, int duration) {
        if (mScrollAnimatorListener != null) {
            mScrollAnimatorListener.onAnimatorStart();
        }
        mAnimatorFlag = true;
        mScroller.startScroll(mScroller.getCurrX(), mScroller.getCurrY(), dx, dy, duration);
        invalidate();// 这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    /**
     * 重置
     */
    public void reset() {
        mScroller.setFinalX(0);
        mScroller.setFinalY(0);
        scrollTo(0, 0);
        postInvalidate();
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            if (Math.abs(mScroller.getCurrX()) > Math.abs(mTranslateOffsetX)) {
                Log.e(TAG, "computeScroll 位移超过X轴的最大限制边界，不能再移动");
                return;
            }

            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        } else {
            if (mScrollAnimatorListener != null && mAnimatorFlag) {
                mAnimatorFlag = false;
                mScrollAnimatorListener.onAnimatorEnd();
            }
        }
    }

    /**
     * 设置扩展ScaleType
     *
     * @param scaleTypeEx 缩放枚举
     */
    public void setScaleTypeEx(ScaleTypeEx scaleTypeEx) {
        if (mScaleTypeEx != scaleTypeEx) {
            setScaleType(ScaleType.MATRIX);
            this.mScaleTypeEx = scaleTypeEx;
        }
    }

    public void setPosition(Position position) {
        if (mPosition != position) {
            setScaleType(ScaleType.MATRIX);
            mPosition = position;
        }
    }

    @Override
    public void onGlobalLayout() {
        if (once) {
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }

            // 控件的宽高
            int width = getWidth() - getPaddingLeft() - getPaddingRight();
            int height = getHeight() - getPaddingTop() - getPaddingBottom();

            // 图片的宽和高
            int dw = drawable.getIntrinsicWidth();
            int dh = drawable.getIntrinsicHeight();

            configureScale(width, height, dw, dh);
            configurePosition(width, height, dw, dh);

            setImageMatrix(mMatrix);

            once = false;
        }
    }

    /**
     * 配置缩放。
     * mScaleTypeEx 枚举值等于 CROP：和ScaleType.CENTER_CROP 的缩放原则一致，只是没有指定位置为中间；
     * mScaleTypeEx 枚举值等于 INSIDE：和ScaleType.CENTER_INSIDE 的缩放原则一致，只是没有指定位置为中间；
     *
     * @param vwidth  view的宽度
     * @param vheight view高度
     * @param dwidth  图片的宽度
     * @param dheight 图片的高度
     */
    private void configureScale(int vwidth, int vheight, int dwidth, int dheight) {
        if (mScaleTypeEx == null) {
            return;
        }
        switch (mScaleTypeEx) {
            case CROP:
                if (dwidth * vheight > vwidth * dheight) {
                    mScale = (float) vheight / (float) dheight;
                } else {
                    mScale = (float) vwidth / (float) dwidth;
                }
                mMatrix.setScale(mScale, mScale);
                break;

            case INSIDE:
                if (dwidth <= vwidth && dheight <= vheight) {
                    mScale = 1.0f;
                } else {
                    mScale = Math.min((float) vwidth / (float) dwidth,
                            (float) vheight / (float) dheight);
                }
                mMatrix.setScale(mScale, mScale);
                break;

            default:
                break;
        }
    }

    /**
     * 配置位移。
     * 当前支持的初始化位置为：LEFT、RIGHT、BOTTOM、RIGHT_BOTTOM。
     * 分别表示初始位置在view的左上、右上、左下、右下。
     *
     * @param vwidth  view的宽度
     * @param vheight view高度
     * @param dwidth  图片的宽度
     * @param dheight 图片的高度
     */
    private void configurePosition(int vwidth, int vheight, int dwidth, int dheight) {
        if (mPosition == null) {
            return;
        }
        switch (mPosition) {
            case LEFT:
                mTranslateOffsetX = mTranslateOffsetY = 0;
                mMatrix.postTranslate(0, 0);
                break;

            case RIGHT:
                mTranslateOffsetX = Math.round(vwidth - dwidth * mScale);
                mMatrix.postTranslate(mTranslateOffsetX, 0);
                break;

            case BOTTOM:
                mTranslateOffsetY = Math.round(vheight - dheight * mScale);
                mMatrix.postTranslate(0, mTranslateOffsetY);
                break;

            case RIGHT_BOTTOM:
                mTranslateOffsetX = Math.round(vwidth - dwidth * mScale);
                mTranslateOffsetY = Math.round(vheight - dheight * mScale);
                mMatrix.postTranslate(mTranslateOffsetX, mTranslateOffsetY);
                break;
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    /**
     * 返回在X轴上的位移偏移距离
     *
     * @return mTranslateOffsetX
     */
    public float getTranslateOffsetX() {
        return mTranslateOffsetX;
    }

    /**
     * 返回在Y轴上的位移偏移距离
     *
     * @return mTranslateOffsetY
     */
    public float getTranslateOffsetY() {
        return mTranslateOffsetY;
    }

    public void setScrollAnimatorListener(ISmoothScrollAnimatorListener scrollAnimatorListener) {
        this.mScrollAnimatorListener = scrollAnimatorListener;
    }

    /**
     * 缩放类型枚举
     * ScaleTypeEx.CROP：和ScaleType.CENTER_CROP 的缩放原则一致；
     * ScaleTypeEx.INSIDE：和ScaleType.CENTER_INSIDE 的缩放原则一致；
     */
    public enum ScaleTypeEx {
        CROP(0),
        INSIDE(2);

        ScaleTypeEx(int ni) {
            nativeInt = ni;
        }

        final int nativeInt;
    }

    /**
     * 位置枚举
     * Position.LEFT：在view的左上角
     * Position.RIGHT：在view的右上角
     * Position.BOTTOM：在view的左下角
     * Position.RIGHT_BOTTOM : 在view 的右下角
     */
    public enum Position {
        LEFT(0),
        RIGHT(1),
        BOTTOM(2),
        RIGHT_BOTTOM(3);

        Position(int ni) {
            nativeInt = ni;
        }

        final int nativeInt;
    }


    /**
     * 平滑滑动动画监听
     */
    public interface ISmoothScrollAnimatorListener {
        void onAnimatorStart();

        void onAnimatorEnd();
    }


    public static abstract class SmoothScrollAnimatorAdapter implements ISmoothScrollAnimatorListener {

        @Override
        public void onAnimatorStart() {

        }

        @Override
        public void onAnimatorEnd() {

        }
    }
}
