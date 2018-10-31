package com.skx.tomike.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.utils.SkxDrawableUtil;

/**
 * 着色测试1
 * <p>
 * 通过给Drawable 设置 tint 进行着色
 * blog :http://blog.csdn.net/jm_beizi/article/details/54916965
 * <p>
 * 参考资料：
 * https://developer.android.google.cn/reference/android/support/v4/graphics/drawable/DrawableCompat.html
 * http://www.cnblogs.com/helloandroid/p/4779061.html
 * http://blog.csdn.net/u010687392/article/details/47399719
 * http://www.jianshu.com/p/8c479ed24ca8
 * <p>
 * 涉及到的知识点：
 * 1.DrawableCompat.warp  对drawable进行包装，使drawable 可以跨越任何不同版本进行着色
 * 2.mutate  使drawable 可变，可变的drawable 不与其他使用相同资源的drawable 共享
 * 3.ConstantState 享元模式
 * 4.setTint()  setTintList() 着色方法
 */
public class Tint_DrawableTintActivity extends AppCompatActivity {

    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_compat_tint);

        mImageView1 = (ImageView) findViewById(R.id.tintTest_ImageView1);
        mImageView2 = (ImageView) findViewById(R.id.tintTest_ImageView2);
        mImageView3 = (ImageView) findViewById(R.id.tintTest_ImageView3);
        mImageView4 = (ImageView) findViewById(R.id.tintTest_ImageView4);

//        PorterDuffXfermode
//        AppCompatTextView
//        AppCompatEditText
//        AppCompatTextView


//        BitmapDrawable
//        ColorDrawable

        Drawable originBitmapDrawable = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        mImageView1.setBackground(SkxDrawableUtil.tintDrawable(originBitmapDrawable, Color.parseColor("#30c3a6")));

        Drawable originBitmapDrawable2 = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        mImageView2.setImageDrawable(SkxDrawableUtil.tintDrawable(originBitmapDrawable2, Color.parseColor("#ff4081")));


        Bitmap bitmap = ((BitmapDrawable) originBitmapDrawable).getBitmap();
        mImageView3.setBackground(new BitmapDrawable(getResources(), bitmap));

        Bitmap bitmap2 = ((BitmapDrawable) originBitmapDrawable2).getBitmap();
        mImageView4.setImageBitmap(bitmap2);


    }
    /*
      mMutated 是个标签，已保证mutate 只会设置一次，也就解释了在Drawable中对mutate（）方法的一个解释，
     Calling this method on a mutable Drawable will have no effect.
     （在已经可变的drawable上调用此方法无效，因为返回的还是自己）
     */


    /**
     * Make this drawable mutable. This operation cannot be reversed. A mutable
     * drawable is guaranteed to not share its state with any other drawable.
     * This is especially useful when you need to modify properties of drawables
     * loaded from resources. By default, all drawables instances loaded from
     * the same resource share a common state; if you modify the state of one
     * instance, all the other instances will receive the same modification.
     *
     * 使这个drawable可变。 此操作无法撤消。一个可变的Drawable不会与其他Drawable共享它的状态。
     * 当你需要修改从资源加载的drawables的属性时，这是特别有用的。
     * 默认情况下，从同一资源加载的所有drawables实例都共享一个公共状态; 如果修改一个实例的状态，所有其他实例将接收相同的修改。
     *
     *
     * Calling this method on a mutable Drawable will have no effect.
     * 在可变的Drawable上调用此方法将没有效果。
     *
     * @return This drawable.
     * @see ConstantState
     * @see #getConstantState()
     */

}
