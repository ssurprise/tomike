package com.skx.tomike.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.Button;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.utils.SkxDrawableUtil;

/**
 * 着色测试2
 * <p>
 * 1.xml 着色
 *      android:background="@drawable/ drawable选择器"
 *      app:backgroundTint="@color/ 颜色选择器"
 * <p>
 *      如果是AppCompatButton AppCompatTextView 只需要设置 app:backgroundTint="@color/ 颜色选择器"
 * <p>
 * 2.code 着色
 * setBackgroundTintList（colorStateListSelector）
 * setSupportBackgroundTintList（colorStateListSelector）
 *
 */
public class Tint_BackgroundTintActivity extends AppCompatActivity {

    private ImageView mTint_ImageView2;
    private Button mTint_Btn2;
    private AppCompatButton mCompatBtn4;

    private ImageView mTintSelector_mImageView2;
    private Button mTintSelector_mBtn2;
    private AppCompatButton mTintSelector_mCompatBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_compat_tint2);

        mTint_ImageView2 = (ImageView) findViewById(R.id.tintTest2_tint_ImageView2);
        mTint_Btn2 = (Button) findViewById(R.id.tintTest2_tint_btn2);
        mCompatBtn4 = (AppCompatButton) findViewById(R.id.tintTest2_tint_btn4);

        mTintSelector_mImageView2 = (ImageView) findViewById(R.id.tintTest2_ImageView2);
        mTintSelector_mBtn2 = (Button) findViewById(R.id.tintTest2_btn2);
        mTintSelector_mCompatBtn4 = (AppCompatButton) findViewById(R.id.tintTest2_btn4);


        Drawable imgBgDrawable2 = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        mTint_ImageView2.setBackground(imgBgDrawable2);
        ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#3396a8"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTint_ImageView2.setBackgroundTintList(colorStateList);
        } else {
            mTint_ImageView2.setBackground(SkxDrawableUtil.tintListDrawable(imgBgDrawable2, colorStateList));
        }

        Drawable bgDrawable2 = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        mTint_Btn2.setBackground(bgDrawable2);
        ColorStateList colorStateList2 = ColorStateList.valueOf(Color.parseColor("#866393"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTint_Btn2.setBackgroundTintList(colorStateList2);
        } else {
            mTint_Btn2.setBackground(SkxDrawableUtil.tintListDrawable(bgDrawable2, colorStateList2));
        }

        Drawable bgDrawable4 = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        mCompatBtn4.setBackground(bgDrawable4);
        ColorStateList colorStateList4 = ColorStateList.valueOf(Color.parseColor("#984568"));
        mCompatBtn4.setSupportBackgroundTintList(colorStateList4);


//----------------------------------------------------------------------------分割线--------------------------------------------------------------------------------------------------------------------------------------------------

        Drawable selectorDrawable = ContextCompat.getDrawable(this, R.drawable.selector_drawable_button);
        ColorStateList colorStateListSelector = ContextCompat.getColorStateList(this, R.color.selector_color_button);

        mTintSelector_mImageView2.setBackground(selectorDrawable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTintSelector_mImageView2.setBackgroundTintList(colorStateListSelector);

        } else {
            mTintSelector_mImageView2.setBackground(SkxDrawableUtil.tintListDrawable(
                    selectorDrawable, colorStateListSelector
                    )
            );
        }
        mTintSelector_mImageView2.setClickable(true);

        Drawable selectorDrawable2 = ContextCompat.getDrawable(this, R.drawable.selector_drawable_button);
        mTintSelector_mBtn2.setBackground(selectorDrawable2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTintSelector_mBtn2.setBackgroundTintList(colorStateListSelector);
        } else {
            mTintSelector_mBtn2.setBackground(SkxDrawableUtil.tintListDrawable(selectorDrawable2, colorStateListSelector));
        }

        Drawable bgDrawable = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        mTintSelector_mCompatBtn4.setBackground(bgDrawable);
        mTintSelector_mCompatBtn4.setSupportBackgroundTintList(colorStateListSelector);
    }
}
