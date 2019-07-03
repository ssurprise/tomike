package com.skx.tomike.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

/**
 * Implementation of App Widget functionality.
 */
public class LuCommentScoreWidget extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {

    private LinearLayout widget_totalScoreContainer;
    private TextView tv_totalScore;
    private TextView tv_scoreNum;
    private LinearLayout widget_scoreBehavior;

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

    // 初始状态下星星控件的坐标
    private float starStartPosX;
    private float starStartPosY;
    // 折叠状态下星星控件的坐标
    private float starEndPosX;
    private float starEndPosY;

    private Context mContext;

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
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_lu_comment_total_score, this, true);
        widget_totalScoreContainer = (LinearLayout) findViewById(R.id.luCommentTotalScore_totalScoreContainer);
        tv_totalScore = (TextView) view.findViewById(R.id.luCommentTotalScore_totalScore);
        tv_scoreNum = (TextView) view.findViewById(R.id.luCommentTotalScore_scoreNum);
        widget_scoreBehavior = (LinearLayout) view.findViewById(R.id.luCommentTotalScore_behavior);

        setOrientation(VERTICAL);
//        setGravity(Gravity.CENTER);

        tv_scoreNum.setVisibility(INVISIBLE);

        // 初始状态的控件宽高
        initWidth = DpPxSpTool.INSTANCE.dip2px(context, 114);
        initHeight = DpPxSpTool.INSTANCE.dip2px(context, 114);

        // 折叠后的控件宽高
        collapseWidth = DpPxSpTool.INSTANCE.dip2px(context, 134);
        collapseHeight = DpPxSpTool.INSTANCE.dip2px(context, 25);


        // 初始化分数view 结束点的坐标
        scoreEndPosX = DpPxSpTool.INSTANCE.dip2px(mContext, 15);
        scoreEndPosY = (collapseHeight - DpPxSpTool.INSTANCE.sp2px(mContext, 14)) / 2;

        // 初始化星星view 结束点的坐标
        starEndPosX = collapseWidth - DpPxSpTool.INSTANCE.dip2px(mContext, 15);
        starEndPosY = (collapseHeight - DpPxSpTool.INSTANCE.sp2px(mContext, 14)) / 2;

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

        // 3. 星星位移变化
        refreshStarView(rate);
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

    /**
     * 更新分数表现器的位置
     *
     * @param rate 变化率
     */
    private void refreshStarView(float rate) {
        widget_scoreBehavior.setTranslationX((starEndPosX - starStartPosX) * rate);
        // 此处的 5 乃是误差范围。根据具体情况酌情进行调整
        widget_scoreBehavior.setTranslationY((starEndPosY - starStartPosY + 5) * rate);
    }

    @Override
    public void onGlobalLayout() {
        scoreStartPosX = tv_totalScore.getLeft();
        scoreStartPosY = tv_totalScore.getTop();

        starStartPosX = widget_scoreBehavior.getRight();
        starStartPosY = widget_scoreBehavior.getTop();
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

    public void setScoreInfo(String totalScore, String scoreNum) {
        tv_totalScore.setText(totalScore);
        int scoreNumInt = 0;
        try {
            scoreNumInt = Integer.parseInt(scoreNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (scoreNumInt > 3) {
            tv_scoreNum.setVisibility(VISIBLE);
            tv_scoreNum.setText(scoreNum);
        } else {
            tv_scoreNum.setVisibility(INVISIBLE);
        }
    }

}

