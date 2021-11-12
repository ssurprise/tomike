package com.skx.tomike.tank.widget.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.tomike.tank.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.utils.SkxDrawableUtilKt;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTER_GROUP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CARDVIEW;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TINT;

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
@Route(path = ROUTE_PATH_TINT, group = ROUTER_GROUP)
public class DrawableTintActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawable_compat_tint;
    }

    @Override
    protected void initView() {
        ImageView mImageView1 = findViewById(R.id.tintTest_ImageView1);
        ImageView mImageView2 = findViewById(R.id.tintTest_ImageView2);
        ImageView mImageView3 = findViewById(R.id.tintTest_ImageView3);
        ImageView mImageView4 = findViewById(R.id.tintTest_ImageView4);

        Drawable originBitmapDrawable = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        ViewCompat.setBackground(mImageView1, SkxDrawableUtilKt.tintDrawable(originBitmapDrawable, Color.parseColor("#30c3a6")));
        mImageView2.setImageDrawable(SkxDrawableUtilKt.tintDrawable(originBitmapDrawable, Color.parseColor("#ff4081")));


        Drawable originBitmapDrawable2 = ContextCompat.getDrawable(this, R.drawable.icon_beijing);
        Drawable tintDrawable2 = SkxDrawableUtilKt.tintDrawable(originBitmapDrawable2, Color.parseColor("#30c3a6"));
        Bitmap bitmap2 = ((BitmapDrawable) tintDrawable2).getBitmap();
        ViewCompat.setBackground(mImageView3, new BitmapDrawable(getResources(), bitmap2));
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
