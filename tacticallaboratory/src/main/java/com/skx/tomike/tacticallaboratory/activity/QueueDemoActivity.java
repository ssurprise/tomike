package com.skx.tomike.tacticallaboratory.activity;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.skx.tomike.tacticallaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;
import com.skx.tomikecommonlibrary.utils.SkxDrawableUtil;
import com.skx.tomikecommonlibrary.utils.ToastTool;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 描述 : 数据结构 - 队列 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
public class QueueDemoActivity extends SkxBaseActivity<BaseViewModel> {

    private LinearLayout mLlStackWrap;

    private final int MAX_SIZE = 5;

    private int mIndex = -1;
    private final ArrayDeque<Integer> mQueue = new ArrayDeque<>();

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 队列").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_structure_queue;
    }

    @Override
    protected void initView() {
        mLlStackWrap = findViewById(R.id.tv_queue_sourceWrap);
        findViewById(R.id.tv_queue_pushBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                push();
            }
        });

        findViewById(R.id.tv_queue_popBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
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
