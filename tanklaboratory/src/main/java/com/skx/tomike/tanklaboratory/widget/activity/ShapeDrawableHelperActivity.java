package com.skx.tomike.tanklaboratory.widget.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;
import com.skx.tomikecommonlibrary.utils.SkxDrawableUtil;
import com.skx.tomikecommonlibrary.utils.SkxDrawableUtilKt;

public class ShapeDrawableHelperActivity extends SkxBaseActivity<BaseViewModel> {

    private LinearLayout mLlContainer;
    private ImageView imageView1;
    private TextView textView1;
    private Button btn1;
    private View view1;
    private View view2;
    private View view3;
    private View view4;

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("Drawable 工具类demo").create();
    }

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shape_drawable_helper;
    }

    @Override
    protected void initView() {
        mLlContainer = findViewById(R.id.ll_drawableUtils_container);
        imageView1 = findViewById(R.id.imageView1);
        textView1 = findViewById(R.id.textView1);
        btn1 = findViewById(R.id.button1);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshView();
    }

    private void refreshView() {
        int[][] strokeState = {{-android.R.attr.state_pressed}, {android.R.attr.state_pressed}};

        SkxDrawableUtil skxDrawableUtil = new SkxDrawableUtil();
        ViewCompat.setBackground(imageView1,
                skxDrawableUtil.getBuilder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(Color.parseColor("#ff4081"))
                        .setCornerRadius(20.f)
                        .create());
        ViewCompat.setBackground(textView1,
                skxDrawableUtil.getBuilder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(Color.parseColor("#ff4081"))
                        .setStroke(3, Color.parseColor("#3a39a6"))
                        .create());


        int[] imageSolidColors = {R.drawable.image_01, R.drawable.image_02};
        ViewCompat.setBackground(btn1,
                skxDrawableUtil.getBuilder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(new ColorStateList(strokeState, imageSolidColors))
                        .setCornerRadius(20)
                        .setStroke(3, Color.parseColor("#3a39a6"))
                        .create());

        int[] strokeColors = {Color.parseColor("#3a39a6"), Color.parseColor("#ff4081")};
        ColorStateList strokeColorStateList = new ColorStateList(strokeState, strokeColors);

        int[] solidColors = {Color.parseColor("#ff4081"), Color.parseColor("#3a39a6")};
        ColorStateList solidColorStateList = new ColorStateList(strokeState, solidColors);
        ViewCompat.setBackground(view1,
                skxDrawableUtil.getBuilder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(solidColorStateList)
                        .setStroke(5, strokeColorStateList)
                        .setCornerRadii(new float[]{0.0f, 0.0f, 40.0f, 100.0f, 0.0f, 0.0f, 40.0f, 20.0f})
                        .create());
        ViewCompat.setBackground(view2,
                skxDrawableUtil.getBuilder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(Color.parseColor("#ff4081"))
                        .setCornerRadius(20)
                        .setStroke(5, Color.parseColor("#3a39a6"), 15, 5)
                        .create());

        ViewCompat.setBackground(view4,
                SkxDrawableUtilKt.getSelectorDrawable(Color.parseColor("#3a39a6"), Color.parseColor("#ff4081"), 20));

        View view7 = mLlContainer.getChildAt(7);
        ViewCompat.setBackground(view7,
                skxDrawableUtil.getBuilder(SkxDrawableUtil.Builder.OVAL)
                        .setCornerRadius(5)
                        .setStroke(5, Color.parseColor("#3a39a6"), 15, 5)
                        .create());

        View view8 = mLlContainer.getChildAt(8);
        ViewCompat.setBackground(view8,
                skxDrawableUtil.getBuilder(SkxDrawableUtil.Builder.OVAL)
                        .setColor(Color.parseColor("#ff4081"))
                        .setCornerRadius(5)
                        .setStroke(15, Color.parseColor("#3a39a6"), 15, 5)
                        .create());

        View view9 = mLlContainer.getChildAt(9);
        ViewCompat.setBackground(view9,
                skxDrawableUtil.getBuilder(SkxDrawableUtil.Builder.LINE)
                        .setStroke(15, Color.parseColor("#3a39a6"), 15, 5)
                        .create());

        View view10 = mLlContainer.getChildAt(10);
        ViewCompat.setBackground(view10,
                skxDrawableUtil.getBuilder(SkxDrawableUtil.Builder.LINE)
                        .setStroke(5, Color.parseColor("#3a39a6"), 15, 5)
                        .create());
    }
}
