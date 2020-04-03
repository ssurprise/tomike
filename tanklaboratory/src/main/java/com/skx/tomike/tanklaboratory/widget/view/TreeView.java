package com.skx.tomike.tanklaboratory.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TreeView extends View {

    private SnapShot snapShot;
    private Paint paint;
//    private LinkedList<Branch>

    public TreeView(Context context) {
        this(context, null);
    }

    public TreeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TreeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        snapShot = new SnapShot(Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // 保存上一次的效果
        canvas.drawBitmap(snapShot.bitmap, 0, 0, paint);

    }


    class SnapShot {
        Canvas canvas;
        Bitmap bitmap;

        public SnapShot(Bitmap bitmap) {
            this.bitmap = bitmap;
            this.canvas = new Canvas(bitmap);
        }
    }

    class Branch {
        // 树干 需要用到贝塞尔曲线

    }
}
