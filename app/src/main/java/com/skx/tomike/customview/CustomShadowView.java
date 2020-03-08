package com.skx.tomike.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by shiguotao on 2017/8/17.
 */
public class CustomShadowView extends View {

    private Paint mPaint;

    public CustomShadowView(Context context) {
        this(context, null);
    }

    public CustomShadowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制阴影，param1：模糊半径；param2：x轴大小：param3：y轴大小；param4：阴影颜色
        mPaint.setShadowLayer(10F, 15F, 15F, Color.GRAY);
        RectF rect = new RectF(0, 0, 200, 200);
        canvas.drawRoundRect(rect, (float) 75, (float) 75, mPaint);

    }
}
