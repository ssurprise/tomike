package com.skx.tomike.activity.effect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skx.tomike.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : Layout 添加/删除ziview的过渡效果
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2018/12/16 5:43 PM
 */
public class LayoutTransitionActivity extends AppCompatActivity {

    private LinearLayout mContainer;
    private List<View> mViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition);

        mContainer = findViewById(R.id.layoutTransition_container);
    }

    public void remove(View view) {
        mContainer.removeViewAt(0);
        mViewList.remove(0);

    }

    public void add(View view) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
        textView.setText(String.valueOf(mViewList.size()));
        textView.setLayoutParams(layoutParams);
        mContainer.addView(textView, 0);
        mViewList.add(0, textView);
    }


    /*
    This class enables automatic animations on layout changes in ViewGroup objects.
    To enable transitions for a layout container, create a LayoutTransition object and
    set it on any ViewGroup by calling setLayoutTransition(LayoutTransition).
    This will cause default animations to run whenever items are added to or removed from that container.
    To specify custom animations, use the setAnimator() method.

    目标：当ViewGroup添加或者删除View 时，可以自动执行过渡动画。

    使用：
    1.创建LayoutTransition对象
    2.调用setLayoutTransition(LayoutTransition)方法设置到ViewGroup上
    注意：这时的所有效果都是默认的。

    与setLayoutTransition对应的xml属性是 animateLayoutChanges =“true”，提供了默认的效果。

    */
}
