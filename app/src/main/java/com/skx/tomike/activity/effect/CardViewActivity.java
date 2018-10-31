package com.skx.tomike.activity.effect;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.skx.tomike.R;

/**
 * 卡片布局
 * <p/>
 * 如果要在您的布局中设置圆角半径，请使用 card_view:cardCornerRadius 属性。
 * 如果要在您的代码中设置圆角半径，请使用 CardView.setRadius 方法。
 */
public class CardViewActivity extends AppCompatActivity {
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
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
    }
}
