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

import java.util.Stack;

import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_STACK;

/**
 * 描述 : 数据结构 - 栈 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
@Route(path = ROUTE_PATH_STACK)
public class StackDemoActivity extends SkxBaseActivity<BaseViewModel> {

    private static final int MAX_SIZE = 5;

    private int mIndex = -1;
    private final Stack<Integer> mStack = new Stack<>();

    private LinearLayout mLlStackWrap;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 栈").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_data_structure_stack;
    }

    @Override
    protected void initView() {
        mLlStackWrap = findViewById(R.id.tv_stack_sourceWrap);
        findViewById(R.id.tv_stack_pushBtn).setOnClickListener(v -> push());

        findViewById(R.id.tv_stack_popBtn).setOnClickListener(v -> pop());
    }

    /**
     * 入栈
     */
    private void push() {
        if (mIndex + 1 >= MAX_SIZE) {
            ToastTool.showToast(this, "最大支持添加" + MAX_SIZE + "个");
            return;
        }
        mStack.push(++mIndex);
        addView();
    }

    /**
     * 出栈
     */
    private void pop() {
        if (mStack.size() == 0) {
            ToastTool.showToast(this, "当前栈内没有元素哦~");
            return;
        }
        mIndex--;
        Integer pop = mStack.pop();
        ToastTool.showToast(this, "出栈的元素为：" + pop);
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
        mLlStackWrap.addView(textView, 0, lp);
    }

    /*
    派生自 java.util.Vector 类，其内部实现是 Object[]

    1.入栈 push(E item)
    方法内部调用的是 addElement(E obj) 方法
    java.util.Vector.addElement 和 java.util.Vector.add(E) 没区别，方法体是一样的，不过后者有返回值。

    2.出栈 pop()
    其内部同样是调用父类的 java.util.Vector#removeElementAt 方法完成删除操作

    3.peek()
    查看该堆栈顶部的对象，但是不移除该元素

     */

}
