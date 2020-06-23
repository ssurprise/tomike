package com.skx.tomike.tanklaboratory.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.utils.DpPxSpToolKt;

/**
 * Implementation of App Widget functionality.
 */
public class LuCommentScoreWidget extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {

    private LinearLayout widget_totalScoreContainer;
    private TextView tv_totalScore;

    // 初始状态下 控件的宽高
    private int initWidth;
    private int initHeight;
    // 折叠状态下 控件的宽高
    private int collapseWidth;
    private int collapseHeight;

    // 初始状态下 分数控件的字号大小
    private final float startScoreTextSize = 40.0f;
    // 折叠状态下 分数控件的字号大小
    private final float endScoreTextSize = 14.0f;

    // 初始状态下 总分控件的坐标
    private float scoreStartPosX;
    private float scoreStartPosY;
    // 折叠状态下 总分控件的坐标
    private float scoreEndPosX;
    private float scoreEndPosY;


    public LuCommentScoreWidget(Context context) {
        this(context, null);
    }

    public LuCommentScoreWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LuCommentScoreWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_lu_comment_total_score, this, true);
        widget_totalScoreContainer = findViewById(R.id.luCommentTotalScore_totalScoreContainer);
        tv_totalScore = view.findViewById(R.id.luCommentTotalScore_totalScore);

        setOrientation(VERTICAL);

        // 初始状态的控件宽高
        initWidth = DpPxSpToolKt.dip2px(context, 114);
        initHeight = DpPxSpToolKt.dip2px(context, 114);

        // 折叠后的控件宽高
        collapseWidth = DpPxSpToolKt.dip2px(context, 134);
        collapseHeight = DpPxSpToolKt.dip2px(context, 25);

        // 初始化分数view 结束点的坐标
        scoreEndPosX = DpPxSpToolKt.dip2px(context, 15);
        scoreEndPosY = (collapseHeight - DpPxSpToolKt.sp2px(context, 14)) * 1.0f / 2;
    }


    /**
     * 改变view的形状
     * <p>
     * 1.此view的宽高改变
     * 2.子view 位移变化或者形变
     *
     * @param rate 变化率
     */
    public void changeViewShape(float rate) {
        if (rate < 0) {
            rate = 0;
        } else if (rate > 1) {
            rate = 1;
        }
        // 1.view 宽高变化
        updateWidthHeight(rate);

        // 2. 分数变化
        refreshSoreViewPos(rate);

    }

    /**
     * 更新view的宽高
     *
     * @param rate 变化率，上滑 逐渐变大；下滑 逐渐变小。
     */
    private void updateWidthHeight(float rate) {// 上滑，rate越大；
        ViewGroup.LayoutParams lp = widget_totalScoreContainer.getLayoutParams();
        lp.height = initHeight - (int) ((initHeight - collapseHeight) * (rate));// 高度逐渐变小
        lp.width = initWidth + (int) ((collapseWidth - initWidth) * (rate));// 宽度逐渐变大
        widget_totalScoreContainer.setLayoutParams(lp);
    }

    /**
     * 更新分数View的位置，字号大小
     *
     * @param rate 变化率
     */
    private void refreshSoreViewPos(float rate) {
        tv_totalScore.setTranslationX((scoreEndPosX - scoreStartPosX) * rate);

        // 因为textView 设置字号会有上下间距，所的以要减去一个大概距离
        tv_totalScore.setTranslationY((scoreEndPosY - scoreStartPosY - 12) * rate);
        float size = startScoreTextSize - ((startScoreTextSize - endScoreTextSize) * rate);
        size = size > startScoreTextSize ? startScoreTextSize : size < endScoreTextSize ? endScoreTextSize : size;
        tv_totalScore.setTextSize(size);
    }


    @Override
    public void onGlobalLayout() {
        scoreStartPosX = tv_totalScore.getLeft();
        scoreStartPosY = tv_totalScore.getTop();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }
}

