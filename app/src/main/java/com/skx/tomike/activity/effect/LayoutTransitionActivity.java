package com.skx.tomike.activity.effect;

import android.animation.PropertyValuesHolder;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.skx.tomike.R;

/**
 * Layout 添加/删除ziview的过渡效果
 *
 * @author shiguotao
 */
public class LayoutTransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition);
        final CardView cardView = (CardView) findViewById(R.id.card_view);
        if (cardView != null) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    }
                }
            });
        }
//        ViewGroup
//        PropertyValuesHolder
    }


    /*
    This class enables automatic animations on layout changes in ViewGroup objects. To enable transitions for a layout container, create a LayoutTransition object and set it on any ViewGroup by calling setLayoutTransition(LayoutTransition). This will cause default animations to run whenever items are added to or removed from that container. To specify custom animations, use the setAnimator() method.


    目标：当ViewGroup添加或者删除View 时，可以自动执行过渡动画。

    使用：
    1.创建LayoutTransition对象
    2.调用setLayoutTransition(LayoutTransition)方法设置到ViewGroup上
    注意：这时的所有效果都是默认的。



    与setLayoutTransition对应的xml属性是 animateLayoutChanges =“true”，提供了默认的效果。


     */
}
