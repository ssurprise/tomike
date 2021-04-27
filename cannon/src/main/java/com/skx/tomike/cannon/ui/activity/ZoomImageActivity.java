package com.skx.tomike.cannon.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.skx.tomike.cannon.R;
import com.skx.tomike.cannon.ui.view.ZoomImageView;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

import org.jetbrains.annotations.NotNull;

/**
 * 描述 : 图片缩放
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-20 23:14
 */
public class ZoomImageActivity extends SkxBaseActivity<BaseViewModel> {

    private int[] mImageArray;

    @Override
    protected void initParams() {
        mImageArray = new int[]{R.drawable.image_02, R.drawable.kuantu, R.drawable.changtu};
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zoom_image;
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("图片缩放").create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        ViewPager mViewPager = findViewById(R.id.id_viewpager);
        mViewPager.setAdapter(new PagerAdapter() {

            @NotNull
            @Override
            public Object instantiateItem(@NotNull ViewGroup container, int position) {
                ZoomImageView imageView = new ZoomImageView(getApplicationContext());

                // 图片重叠
                Bitmap newBitmap = overlapBitmap(position);
                imageView.setImageBitmap(newBitmap);
                container.addView(imageView);
                imageView.setSingleClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("xianshi", "xianshi");
                    }
                });
                return imageView;
            }

            @Override
            public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
                View view = (View) object;
                container.removeView(view);
            }

            @Override
            public boolean isViewFromObject(@NotNull View arg0, @NotNull Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return mImageArray.length;
            }
        });
    }

    @NonNull
    private Bitmap overlapBitmap(int position) {
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), mImageArray[position]).copy(Bitmap.Config.ARGB_8888, true);
//        Bitmap bitmap2 = ((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.icon_beijing)).getBitmap();
//        Bitmap newBitmap;
//        newBitmap = Bitmap.createBitmap(bitmap1);
//        Canvas canvas = new Canvas(newBitmap);
//        Paint paint = new Paint();
//        int w = bitmap1.getWidth();
//        int h = bitmap1.getHeight();
//        int w_2 = bitmap2.getWidth();
//        int h_2 = bitmap2.getHeight();
//        paint.setColor(Color.WHITE);
//        paint.setAlpha(0);
//        canvas.drawRect(0, 0, bitmap1.getWidth(), bitmap1.getHeight(), paint);
//        paint = new Paint();
//        canvas.drawBitmap(bitmap2, Math.abs(w - w_2) / 2, Math.abs(h - h_2) / 2, paint);
//        canvas.save();
//        // 存储新合成的图片
//        canvas.restore();
        return bitmap1;
    }
}
