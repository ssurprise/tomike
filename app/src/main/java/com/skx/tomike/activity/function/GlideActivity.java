package com.skx.tomike.activity.function;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.imageloader.AbstractTarget;
import com.skx.tomikecommonlibrary.imageloader.ImageLoader;
import com.skx.tomikecommonlibrary.imageloader.Target;

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

                        final int index = i;
                        ImageLoader.with(GlideActivity.this).load(imageArray[i]).into(new AbstractTarget<Drawable>() {

                            @Override
                            public void onResourceReady(@NonNull Drawable resource) {
                                if (resource == null) return;
                                ((ImageView) gridLayout.getChildAt(index)).setImageDrawable(resource);
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0);
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
