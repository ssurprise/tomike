package com.skx.tomike.missile.ds.activity;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.SkxDrawableUtil;
import com.skx.common.utils.ToastTool;
import com.skx.tomike.missile.R;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_QUEUE;

/**
 * 描述 : 数据结构 - 队列 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
@Route(path = ROUTE_PATH_QUEUE)
public class QueueDemoActivity extends SkxBaseActivity<BaseViewModel<?>> {

    private LinearLayout mLlStackWrap;

    private static final int MAX_SIZE = 5;

    private int mIndex = -1;
    private final Queue<Integer> mQueue = new ArrayDeque<>();

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 队列").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_data_structure_queue;
    }

    @Override
    protected void initView() {
        mLlStackWrap = findViewById(R.id.tv_queue_sourceWrap);
        findViewById(R.id.tv_queue_pushBtn).setOnClickListener(v -> push());
        findViewById(R.id.tv_queue_popBtn).setOnClickListener(v -> pop());
    }

    private void push() {
        if (mIndex + 1 >= MAX_SIZE) {
            ToastTool.showToast(this, "最大支持添加" + MAX_SIZE + "个");
            return;
        }
        mQueue.offer(++mIndex);
        addView();
    }

    private void pop() {
        if (mQueue.size() == 0) {
            ToastTool.showToast(this, "当前栈内没有元素哦~");
            return;
        }
        mIndex--;
        mQueue.poll();
        mLlStackWrap.removeViewAt(0);
    }

    private void addView() {
        TextView textView = new TextView(this);
        textView.setText(String.valueOf(mIndex));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(14);
        ViewCompat.setBackground(textView, new SkxDrawableUtil().getBuilder(SkxDrawableUtil.Builder.RECTANGLE)
                .setColor(Color.parseColor("#2cb298"))
                .setCornerRadius(8)
                .create());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150);
        lp.topMargin = 15;
        lp.leftMargin = 15;
        lp.rightMargin = 15;
        mLlStackWrap.addView(textView, lp);
    }

}
