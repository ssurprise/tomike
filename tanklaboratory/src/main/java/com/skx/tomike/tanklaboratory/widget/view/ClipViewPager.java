package com.skx.tomike.tanklaboratory.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

import com.skx.common.utils.DpPxSpToolKt;
import com.skx.common.utils.ScreenUtilKt;

/**
 * ViewPager 展示多个子页面，可点击移动
 */
public class ClipViewPager extends ViewPager {
    /**
     * 第一个可点击的区域（左边第一个位置）
     */
    private Rect firstRect = null;
    /**
     * 第二个可点击的区域（左边第二个位置）
     */
    private Rect SecondRect = null;
    /**
     * 第三个可点击的区域（中间区域，当前展示的位置）
     */
    private Rect thirdRect = null;
    /**
     * 第四个可点击的区域（从右边第二个位置）
     */
    private Rect fourthRect = null;
    /**
     * 第五个可点击的区域（最右边的位置）
     */
    private Rect fifthRect = null;
    /**
     * 页面间距
     */
    private int pageMargin;
    /**
     * 父容器的左右内边距
     */
    private int parentPadding;
    /**
     * 单位 Rect 的宽度
     */
    private int rectWidth;
    private Paint paint;

    public ClipViewPager(Context context) {
        super(context, null);
    }

    public ClipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        parentPadding = DpPxSpToolKt.dip2px(context, 5);
        rectWidth = DpPxSpToolKt.dip2px(context, 40);
        pageMargin = (ScreenUtilKt.getScreenWidth(context) - parentPadding * 2 - rectWidth * 5) / 4;

        paint = new Paint();
        paint.setColor(Color.parseColor("#30c3a6"));
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

//        if (ev.getAction() == MotionEvent.ACTION_UP) {
//            int location = viewOfClickOnScreen(ev);
//            // 根据偏移位置的不同做不同的跳转，-1 表示是当前展示的位置或者其他未知的位置，不做特殊处理
//            if (location != -1) {
//                if (location == 1) {// 最左边的位置，ViewPager 应当向左偏移2个位置
//                    setCurrentItem(getCurrentItem() - 2);
//
//                } else if (location == 2) {// 左边第二个位置，ViewPager 应当向左偏移1个位置
//                    setCurrentItem(getCurrentItem() - 1);
//
//                } else if (location == 4) {// 右边第二个位置，ViewPager 应当向右偏移1个位置
//                    setCurrentItem(getCurrentItem() + 1);
//
//                } else if (location == 5) {// 最右边的位置，ViewPager 应当向右偏移2个位置
//                    setCurrentItem(getCurrentItem() + 2);
//                }
//            }
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        firstRect = new Rect(parentPadding, getTop(), parentPadding + rectWidth, getBottom());
////        Log.e("firstRect", parentPadding+"----------"+parentPadding + rectWidth+"");
//
//        SecondRect = new Rect(parentPadding + rectWidth + pageMargin, getTop(), parentPadding + rectWidth * 2 + pageMargin, getBottom());
////        Log.e("SecondRect",pageMargin + rectWidth + pageMargin+"----"+ pageMargin + rectWidth * 2 + pageMargin);
//
//        thirdRect = new Rect(parentPadding + rectWidth * 2 + pageMargin * 2, getTop(), parentPadding + rectWidth * 3 + pageMargin * 2, getBottom());
////        Log.e("thirdRect",pageMargin + rectWidth * 2 + pageMargin * 2+"----"+  pageMargin + rectWidth * 3 + pageMargin * 2);
//
//        fourthRect = new Rect(parentPadding + rectWidth * 3 + pageMargin * 3, getTop(), parentPadding + rectWidth * 4 + pageMargin * 3, getBottom());
////        Log.e("fourthRect",pageMargin + rectWidth * 3 + pageMargin * 3+"----"+  pageMargin + rectWidth * 4 + pageMargin * 3);
//
//        fifthRect = new Rect(parentPadding + rectWidth * 4 + pageMargin * 4, getTop(), parentPadding + rectWidth * 4 + pageMargin * 5, getBottom());
//
//        canvas.drawRect(firstRect, paint);
//        canvas.drawRect(SecondRect, paint);
//        canvas.drawRect(thirdRect, paint);
//        canvas.drawRect(fourthRect, paint);
//        canvas.drawRect(fifthRect, paint);
    }

    /**
     * 根据点击位置确定相对于当前pager 的偏移位置
     *
     * @param ev 点击对标
     * @return 相对于当前 pager 的偏移位置
     */
    private int viewOfClickOnScreen(MotionEvent ev) {

        firstRect = new Rect(parentPadding, getTop(), parentPadding + rectWidth, getBottom());
//        Log.e("firstRect", parentPadding+"----------"+parentPadding + rectWidth+"");

        SecondRect = new Rect(parentPadding + rectWidth + pageMargin, getTop(), parentPadding + rectWidth * 2 + pageMargin, getBottom());
//        Log.e("SecondRect",pageMargin + rectWidth + pageMargin+"----"+ pageMargin + rectWidth * 2 + pageMargin);

        thirdRect = new Rect(parentPadding + rectWidth * 2 + pageMargin * 2, getTop(), parentPadding + rectWidth * 3 + pageMargin * 2, getBottom());
//        Log.e("thirdRect",pageMargin + rectWidth * 2 + pageMargin * 2+"----"+  pageMargin + rectWidth * 3 + pageMargin * 2);

        fourthRect = new Rect(parentPadding + rectWidth * 3 + pageMargin * 3, getTop(), parentPadding + rectWidth * 4 + pageMargin * 3, getBottom());
//        Log.e("fourthRect",pageMargin + rectWidth * 3 + pageMargin * 3+"----"+  pageMargin + rectWidth * 4 + pageMargin * 3);

        fifthRect = new Rect(parentPadding + rectWidth * 4 + pageMargin * 4, getTop(), parentPadding + rectWidth * 4 + pageMargin * 5, getBottom());
//        Log.e("fifthRect",pageMargin + rectWidth * 4 + pageMargin * 4+"----"+   pageMargin + rectWidth * 4 + pageMargin * 5);

        float x = ev.getX();
        float y = ev.getY();

        if (firstRect.contains((int) x, (int) y)) {
//            Log.e("firstRect", "1111");
            return 1;
        } else if (SecondRect.contains((int) x, (int) y)) {
//            Log.e("SecondRect", "2222222");
            return 2;
        } else if (thirdRect.contains((int) x, (int) y)) {
//            Log.e("thirdRect", "3333333333");
            return -1;
        } else if (fourthRect.contains((int) x, (int) y)) {
//            Log.e("fourthRect", "4444444444444");
            return 4;
        } else if (fifthRect.contains((int) x, (int) y)) {
//            Log.e("fifthRect", "555555555555555555555");
            return 5;
        }
        //1 计算其他四个展示图片的位置
        return -1;
    }
}