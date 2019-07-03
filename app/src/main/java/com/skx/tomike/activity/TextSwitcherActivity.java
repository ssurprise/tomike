package com.skx.tomike.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

import java.util.Random;

public class TextSwitcherActivity extends Activity {

    TextSwitcher textSwitcher;
    private HorizontalScrollView pageIndicator_page;
    private LinearLayout pageIndicator_page_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher);

        textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
//        textSwitcher.setCurrentText("adv");
//        textSwitcher.setText("123");


        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);

//        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
//            @Override
//            public View makeView() {
//                TextView textView = new TextView(TextSwitcherActivity.this);
//                textView.setTextSize(36);
//                return textView;
//            }
//        });

        findViewById(R.id.textSwitcher_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSwitcher.setText(String.valueOf(new Random().nextInt()));
            }
        });

//        textSwitcher.getChildAt()


        pageIndicator_page = (HorizontalScrollView) findViewById(R.id.pageIndicator_page);
        pageIndicator_page_container = (LinearLayout) findViewById(R.id.pageIndicator_page_container);

        addPageView(1, 5);
    }

    private void addPageView(int offset, int length) {
        for (int i = offset; i < length; i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DpPxSpTool.INSTANCE.dip2px(this,25), ViewGroup.LayoutParams.MATCH_PARENT);
            TextView tv = new TextView(this);
            tv.setTextSize(24);
            tv.setGravity(Gravity.CENTER);
            tv.setText(i + "");
            tv.setLayoutParams(lp);
            pageIndicator_page_container.addView(tv);
        }
    }
}
