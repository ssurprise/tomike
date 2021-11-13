package com.skx.tomike.missile.activity;

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
import java.util.Deque;

import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_DUEUE;

/**
 * 描述 : 数据结构 - 双端队列 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
@Route(path = ROUTE_PATH_DUEUE)
public class DueueDemoActivity extends SkxBaseActivity<BaseViewModel> {

    private LinearLayout mLlStackWrap;

    private static final int MAX_SIZE = 8;

    private int mIndex = -1;
    private final Deque<Integer> mQueue = new ArrayDeque<>();

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 双端队列").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_structure_dueue;
    }

    @Override
    protected void initView() {
        mLlStackWrap = findViewById(R.id.tv_dueue_sourceWrap);

        findViewById(R.id.tv_dueue_addFirst).setOnClickListener(v -> push(true));
        findViewById(R.id.tv_dueue_addLast).setOnClickListener(v -> push(false));

        findViewById(R.id.tv_dueue_removeFirst).setOnClickListener(v -> pop(true));
        findViewById(R.id.tv_dueue_removeLast).setOnClickListener(v -> pop(false));
    }

    private void push(boolean isFirst) {
        if (mIndex + 1 >= MAX_SIZE) {
            ToastTool.showToast(this, "最大支持添加" + MAX_SIZE + "个");
            return;
        }
        mIndex++;
        if (isFirst) {
            mQueue.offerFirst(mIndex);
            mLlStackWrap.addView(createChildView(), 0);
        } else {
            mQueue.offerLast(mIndex);
            mLlStackWrap.addView(createChildView());
        }
    }

    private void pop(boolean isFirst) {
        if (mQueue.size() == 0) {
            ToastTool.showToast(this, "当前栈内没有元素哦~");
            return;
        }
        mIndex--;
        if (isFirst) {
            mQueue.pollFirst();
            mLlStackWrap.removeViewAt(0);
        } else {
            mQueue.pollLast();
            mLlStackWrap.removeViewAt(mLlStackWrap.getChildCount() - 1);
        }
    }

    private TextView createChildView() {
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
        textView.setLayoutParams(lp);
        return textView;
    }
}
