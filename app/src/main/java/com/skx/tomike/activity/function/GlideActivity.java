package com.skx.tomike.activity.function;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.imageloader.ImageLoader;
import com.skx.tomikecommonlibrary.imageloader.TransformStrategy;
import com.skx.tomikecommonlibrary.imageloader.transform.CenterCrop;
import com.skx.tomikecommonlibrary.imageloader.transform.CenterInside;
import com.skx.tomikecommonlibrary.imageloader.transform.CircleCrop;
import com.skx.tomikecommonlibrary.imageloader.transform.OverLapTransform;
import com.skx.tomikecommonlibrary.imageloader.transform.RoundedCorners;

public class GlideActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private String[] imageArray = {
            "http://a.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=16d72513b0003af34defd464001aea6a/8601a18b87d6277fbe2bf7222e381f30e924fceb.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539145466732&di=2181114d0261abcaba42a4ea115da777&imgtype=0&src=http%3A%2F%2F02imgmini.eastday.com%2Fmobile%2F20180910%2F20180910024525_12bf1f909d6a22fe426b06806d6cba38_3_mwpm_03200403.jpg",
            "http://cdn.duitang.com/uploads/item/201304/15/20130415014759_u3UUV.jpeg",
            "http://365jia.cn/uploads/11/1121/4ec9f0089de6f.jpg",
            null,
            "http://img.cheshi-img.com/meinv/0_720/2009/0505/49fff32d846be.jpg",
            "http://img1.imgtn.bdimg.com/it/u=3522970723,1359610582&fm=26&gp=0.jpg",
            "http://img.anzow.com/Software/files_images/2014109/2014100975059721.jpg",
            "http://img2.ph.126.net/gUzX-t8Px6z7Pd9x4urozw==/622622648501911322.jpg",
            "http://g.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=7f7e2d22de54564ee530ec3d86eeb0b4/d439b6003af33a87df7a112bc55c10385343b5ef.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541670921791&di=0105ca00fb13ffae5bfa5048b7886934&imgtype=0&src=http%3A%2F%2F04imgmini.eastday.com%2Fmobile%2F20180922%2F20180922214531_d63b3a92a3bca14a7fd0900d3b5d48c3_6.jpeg",
    };

    private String nativeImagePath;

    int resourceId = R.mipmap.ic_launcher;
    String gifUrl = "http://image.haha.mx/2014/02/02/middle/1115779_c221d1fc47b97bb1605cddc9c8aec0a7_1391347675.gif";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        gridLayout = findViewById(R.id.glide_gridLayout);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    for (int i = 0, j = imageArray.length; i < j; i++) {
                        if (i >= gridLayout.getChildCount()) {
                            break;
                        }
                        ImageView targetImgv = (ImageView) gridLayout.getChildAt(i);

                        final int index = i;
                        switch (index) {
                            case 0:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .placeholder(R.color.skx_ff4081)
                                        .transformStrategy(TransformStrategy.CENTER_CROP)
                                        .into(targetImgv);
                                break;
                            case 1:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .placeholder(R.color.skx_1f000000)
                                        .transformStrategy(TransformStrategy.CENTER_INSIDE)
                                        .into(targetImgv);
                                break;
                            case 2:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .placeholder(R.color.skx_1f000000)
                                        .transformStrategy(TransformStrategy.FIT_CENTER)
                                        .into(targetImgv);
                                break;
                            case 3:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transformStrategy(TransformStrategy.CIRCLE_CROP)
                                        .into(targetImgv);
                                break;
                            case 4:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transform(new CenterInside())
                                        .into(targetImgv);
                                break;
                            case 5:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transform(new CenterCrop())
                                        .into(targetImgv);
                                break;
                            case 6:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transform(new CircleCrop())
                                        .into(targetImgv);
                                break;
                            case 7:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transform(new RoundedCorners(90))
                                        .into(targetImgv);
                                break;
                            case 8:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transforms(new CenterCrop(), new RoundedCorners(90))
                                        .into(targetImgv);
                                break;
                            case 9:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transforms(new CenterInside(), new RoundedCorners(90))
                                        .into(targetImgv);

                                break;
                            case 10:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .noTransitionAnim()
                                        .transforms(
                                                new CenterInside(),
                                                new RoundedCorners(90),
                                                new OverLapTransform(GlideActivity.this, getBitmap(R.drawable.icon_overdue))
                                        )
                                        .into(targetImgv);

                                break;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0);
    }

    private Bitmap getBitmap(int id) {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.image_01);
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * asBitmap()   以Bitmap方式加载图片。即使加载的图片可能是gif
     * crossFade()， crossFade(int duration) 设置过渡动画，默认是300ms
     * dontAnimate()  取消过渡动画
     * fitCenter() 设置图片填充方式：图片会被完整显示，可能不能完全填充整个ImageView。
     * centerCrop() 设置图片填充方式： 图片不缩放，图片可能会显示不全
     * asGif() 如果gifUrl是一个gif,没有异常发生。但如果gifUrl不是一个Gif，即使是一个完好的图片（非Gif），Glide也会加载失败。.error()回调方法会被调用，并加载错误占位图。
     */

}
