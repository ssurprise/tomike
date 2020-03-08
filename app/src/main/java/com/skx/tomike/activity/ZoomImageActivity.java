package com.skx.tomike.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.skx.tomike.R;
import com.skx.tomike.customview.ZoomImageView;
import com.skx.tomikecommonlibrary.utils.WidthHeightTool;

public class ZoomImageActivity extends SkxBaseActivity {
    ViewPager mViewPager;
    private int[] mImageArray;
    private ImageView[] mImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        initializeData();
        initializeView();
        refreshView();

//        ZoomImageView zoomImg = (ZoomImageView) findViewById(R.id.zoomImage_image);
//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.image_02);
//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.guide_bg_4);
//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.changtu);
//        zoomImg.setImageBitmap(bitmap);
    }

    @Override
    public void initializeData() {
        super.initializeData();
        mImageArray = new int[]{R.drawable.image_02, R.drawable.changtu, R.drawable.guide_bg_4};
        mImageViews = new ImageView[mImageArray.length];
    }

    @Override
    public void initializeView() {
        super.initializeView();
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        WidthHeightTool.getScreenWidth(this);
    }

    @Override
    public void refreshView() {
        super.refreshView();
        mViewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
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
                mImageViews[position] = imageView;
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageViews[position]);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
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
        Bitmap bitmap2 = ((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.icon_beijing)).getBitmap();
        Bitmap newBitmap;
        newBitmap = Bitmap.createBitmap(bitmap1);
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        int w = bitmap1.getWidth();
        int h = bitmap1.getHeight();
        int w_2 = bitmap2.getWidth();
        int h_2 = bitmap2.getHeight();
        paint.setColor(Color.WHITE);
        paint.setAlpha(0);
        canvas.drawRect(0, 0, bitmap1.getWidth(), bitmap1.getHeight(), paint);
        paint = new Paint();
        canvas.drawBitmap(bitmap2, Math.abs(w - w_2) / 2, Math.abs(h - h_2) / 2, paint);
        canvas.save();
        // 存储新合成的图片
        canvas.restore();
        return newBitmap;
    }
}
