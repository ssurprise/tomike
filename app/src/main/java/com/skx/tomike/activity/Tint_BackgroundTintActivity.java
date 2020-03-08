package com.skx.tomike.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.utils.SkxDrawableUtil;

/**
 * 着色测试2
 * <p>
 * 1.xml 着色
 * android:background="@drawable/ drawable选择器"
 * app:backgroundTint="@color/ 颜色选择器"
 * <p>
 * 如果是AppCompatButton AppCompatTextView 只需要设置 app:backgroundTint="@color/ 颜色选择器"
 * <p>
 * 2.code 着色
 * setBackgroundTintList（colorStateListSelector）
 * setSupportBackgroundTintList（colorStateListSelector）
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

        mTint_ImageView2 = findViewById(R.id.tintTest2_tint_ImageView2);
        mTint_Btn2 = findViewById(R.id.tintTest2_tint_btn2);
        mCompatBtn4 = findViewById(R.id.tintTest2_tint_btn4);

        mTintSelector_mImageView2 = findViewById(R.id.tintTest2_ImageView2);
        mTintSelector_mBtn2 = findViewById(R.id.tintTest2_btn2);
        mTintSelector_mCompatBtn4 = findViewById(R.id.tintTest2_btn4);

        SkxDrawableUtil skxDrawableUtil = new SkxDrawableUtil();

        Drawable imgBgDrawable2 = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#3396a8"));
        ViewCompat.setBackground(mTint_ImageView2, skxDrawableUtil.tintListDrawable(imgBgDrawable2, colorStateList));

        Drawable bgDrawable2 = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        ColorStateList colorStateList2 = ColorStateList.valueOf(Color.parseColor("#866393"));
        ViewCompat.setBackground(mTint_Btn2, skxDrawableUtil.tintListDrawable(bgDrawable2, colorStateList2));


        Drawable bgDrawable4 = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        ViewCompat.setBackground(mCompatBtn4, bgDrawable4);

//----------------------------------------------------------------------------分割线--------------------------------------------------------------------------------------------------------------------------------------------------

        Drawable selectorDrawable = ContextCompat.getDrawable(this, R.drawable.selector_drawable_button);
        ColorStateList colorStateListSelector = ContextCompat.getColorStateList(this, R.color.selector_color_button);
        ViewCompat.setBackground(mTintSelector_mImageView2, skxDrawableUtil.tintListDrawable(
                selectorDrawable, colorStateListSelector));
        mTintSelector_mImageView2.setClickable(true);

        Drawable selectorDrawable2 = ContextCompat.getDrawable(this, R.drawable.selector_drawable_button);
        ViewCompat.setBackground(mTintSelector_mBtn2, skxDrawableUtil.tintListDrawable(selectorDrawable2, colorStateListSelector));

        Drawable bgDrawable = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
//        mTintSelector_mCompatBtn4.setBackground(bgDrawable);
//        mTintSelector_mCompatBtn4.setSupportBackgroundTintList(colorStateListSelector);
        ViewCompat.setBackground(mTintSelector_mCompatBtn4, bgDrawable);
    }
}
