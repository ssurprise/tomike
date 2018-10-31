package com.skx.tomike.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.widget.TextView;

import com.skx.tomike.R;

/**
 * 图文混排test
 */
public class ImageSpanActivity extends SkxBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_span);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                tv_insuranceTip2.setText(Html.fromHtml(descString(insuranceTip2), FROM_HTML_MODE_LEGACY, getImageGetterInstance(), null));
//            } else {
//                tv_insuranceTip2.setText(Html.fromHtml(descString(insuranceTip2), getImageGetterInstance(), null));
//            }

        TextView imageSpan_test1 = (TextView) findViewById(R.id.imageSpan_test1);
        TextView imageSpan_test2 = (TextView) findViewById(R.id.imageSpan_test2);
        TextView imageSpan_test3 = (TextView) findViewById(R.id.imageSpan_test3);
        TextView imageSpan_test4 = (TextView) findViewById(R.id.imageSpan_test4);
        TextView imageSpan_test5 = (TextView) findViewById(R.id.imageSpan_test5);
        TextView imageSpan_test6 = (TextView) findViewById(R.id.imageSpan_test6);

        imageSpan_test1.setText(descString("你愁啥"));
        imageSpan_test2.setText(descString("你愁啥"));
    }


    /**
     * 字符串
     *
     * @return
     */
    private String descString(String insuranceTip2) {
        return insuranceTip2 + "  <img src='" + R.drawable.ic_action_search + "'/>";
    }

    /**
     * ImageGetter用于text图文混排
     *
     * @return
     */
    public Html.ImageGetter getImageGetterInstance() {
        Html.ImageGetter imgGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                int id = Integer.parseInt(source);
                Drawable d = ContextCompat.getDrawable(mContext, id);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                return d;
            }
        };

        return imgGetter;
    }
}
