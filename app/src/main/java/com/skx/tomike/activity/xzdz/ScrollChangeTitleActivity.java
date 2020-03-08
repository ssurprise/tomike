package com.skx.tomike.activity.xzdz;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.skx.tomike.R;

/**
 * 滑动改变标题栏透明度、颜色
 */
public class ScrollChangeTitleActivity extends AppCompatActivity {

    private Context mContext;
    private RelativeLayout mHeaderView;
    private TextView mTitle;
    private TextView mTvDes;
    private ImageView iv_mainImage;
    private ImageView mImgvBack;
    private NestedScrollView mScrollView;

    private RelativeLayout mExpandOrderState;
    private CardView mOriginalOrderState;
    private LinearLayout mLlOrderOther;

    private Drawable mBackDrawable;
    private int mAlpha = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_scroll_change_title);
        initializeView();
        installListener();
    }

    public void initializeView() {
        mHeaderView = findViewById(R.id.scrollChange_header);
        mTitle = findViewById(R.id.scrollChange_title);
        mTvDes = findViewById(R.id.scrollChange_des);
        mImgvBack = findViewById(R.id.scrollChange_backImg);
        mScrollView = findViewById(R.id.scrollChange_scrollView);
        iv_mainImage = findViewById(R.id.scrollChange_mainImage);

        mBackDrawable = ContextCompat.getDrawable(this, R.drawable.icon_back);
        mImgvBack.setImageDrawable(mBackDrawable);

        mHeaderView.setBackgroundColor(Color.argb(0, 255, 255, 255));
        mTitle.setAlpha(0);
        iconColorFilter(Color.parseColor("#ffffff"));

        LinearLayout bodyContainer = findViewById(R.id.orderDetail_bodyContainer);

        mExpandOrderState = findViewById(R.id.expandOrder_state);
        mOriginalOrderState = findViewById(R.id.orderDetail_state);
        mLlOrderOther = findViewById(R.id.order_other);

        LayoutTransition transition = new LayoutTransition();
        transition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 100);
        bodyContainer.setLayoutTransition(transition);
    }

    public void installListener() {
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // 滑动的最小距离（自行定义，you happy jiu ok）
                int minHeight = 50;
                // 滑动的最大距离（自行定义，you happy jiu ok）
                int maxHeight = iv_mainImage.getHeight() - dip2px(mContext, minHeight);

                // 滑动距离小于定义得最小距离
                if (v.getScrollY() <= minHeight) {
                    mAlpha = 0;
                }
                // 滑动距离大于定义得最大距离
                else if (v.getScrollY() > maxHeight) {
                    mAlpha = 255;
                }
                // 滑动距离出于最小和最大距离之间
                else {
                    mAlpha = (v.getScrollY() - minHeight) * 255 / (maxHeight - minHeight);
                }

                // 初始状态 标题栏/导航栏透明等
                if (mAlpha <= 0) {
                    mHeaderView.setBackgroundColor(Color.argb(0, 255, 255, 255));
                    mTitle.setTextColor(Color.argb(255, 255, 255, 255));
                    mTitle.setAlpha(0);
                    mTvDes.setTextColor(Color.argb(255, 255, 255, 255));
                    iconColorFilter(Color.parseColor("#ffffff"));

                }
                //  终止状态：标题栏/导航栏 不在进行变化
                else if (mAlpha >= 255) {
                    mHeaderView.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    mTitle.setTextColor(Color.argb(255, 0, 0, 0));
                    mTitle.setAlpha(1.0f);
                    mTvDes.setTextColor(Color.argb(255, 0, 0, 0));

                    iconColorFilter(Color.parseColor("#000000"));

                }
                // 变化中状态：标题栏/导航栏随ScrollView 的滑动而产生相应变化
                else {
                    mHeaderView.setBackgroundColor(Color.argb(mAlpha, 255, 255, 255));
                    mTitle.setTextColor(Color.argb(255, 255 - mAlpha, 255 - mAlpha, 255 - mAlpha));
                    mTitle.setAlpha(mAlpha * 1.0f / 255.0f);
                    mTvDes.setTextColor(Color.argb(255, 255 - mAlpha, 255 - mAlpha, 255 - mAlpha));
                    iconColorFilter(Color.argb(255, 255 - mAlpha, 255 - mAlpha, 255 - mAlpha));
                }
            }
        });
    }

    /**
     * 标题栏/导航栏icon 颜色改变
     *
     * @param color 颜色值
     */
    private void iconColorFilter(int color) {
        PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        mBackDrawable.setColorFilter(colorFilter);
    }

    public void closeExtendOrderState(View view) {
        mExpandOrderState.setVisibility(View.GONE);
    }

    public void onBackPre(View view) {
        onBackPressed();
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
