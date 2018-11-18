package com.skx.tomike.activity.function;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.imageloader.target.AbstractTarget;
import com.skx.tomikecommonlibrary.imageloader.ImageLoader;
import com.skx.tomikecommonlibrary.imageloader.transform.TransformStrategy;
import com.skx.tomikecommonlibrary.imageloader.transform.CenterCrop;
import com.skx.tomikecommonlibrary.imageloader.transform.CenterInside;
import com.skx.tomikecommonlibrary.imageloader.transform.CircleCrop;
import com.skx.tomikecommonlibrary.imageloader.transform.OverLapTransform;
import com.skx.tomikecommonlibrary.imageloader.transform.RoundedCorners;


public class GlideActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private String[] imageArray = {
            // 第1行
            "http://img.ivsky.com/img/tupian/pre/201805/06/shandian-009.jpg",
            "http://img.mp.itc.cn/upload/20170516/93663f2fe5f8491394b4cef08f4a9bdb_th.jpg",

            // 第2行
            "http://cdn.duitang.com/uploads/item/201304/15/20130415014759_u3UUV.jpeg",
            "http://365jia.cn/uploads/11/1121/4ec9f0089de6f.jpg",

            // 第3行
            "https://b-ssl.duitang.com/uploads/item/201208/03/20120803190720_WVxWS.thumb.700_0.jpeg",

            // 第4行
            "http://img.anzow.com/Software/files_images/2014109/2014100975059721.jpg",
            "http://img1.imgtn.bdimg.com/it/u=3522970723,1359610582&fm=26&gp=0.jpg",

            // 第5行
            "http://img.cheshi-img.com/meinv/0_720/2009/0505/49fff32d846be.jpg",

            // 第6行
            "http://img2.ph.126.net/gUzX-t8Px6z7Pd9x4urozw==/622622648501911322.jpg",
            "https://b-ssl.duitang.com/uploads/item/201208/03/20120803190720_WVxWS.thumb.700_0.jpeg",

            // 第7行
            "http://www.pujia8.com/static/pics/20171014110026_41.jpg",
            "http://image.haha.mx/2014/02/02/middle/1115779_c221d1fc47b97bb1605cddc9c8aec0a7_1391347675.gif",

            // 第8行
            "http://www.pujia8.com/static/pics/20171014110026_41.jpg",
            "http://www.pujia8.com/static/pics/20171014110026_41.jpg",

            // 第9行
            "http://www.pujia8.com/static/pics/20171014110026_41.jpg",
            "http://www.pujia8.com/static/pics/20171014110026_41.jpg",
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
                        final ImageView targetImgv = (ImageView) gridLayout.getChildAt(i);

                        final int index = i;
                        switch (index) {
                            // 第1行
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
                            // 第2行
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

                            // 第3行
                            case 4:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transforms(new RoundedCorners(90),
                                                new OverLapTransform(GlideActivity.this, R.drawable.icon_used))
                                        .into(targetImgv);
                                break;

                            // 第4行
                            case 5:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .noTransitionAnim()
                                        .transform(new RoundedCorners(90))
                                        .into(targetImgv);
                                break;
                            case 6:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transform(new CircleCrop())
                                        .into(targetImgv);
                                break;

                            // 第5行
                            case 7:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
//                                        .transform(new CenterCrop())
                                        .into(targetImgv);
                                break;
                            case 8:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transforms(new CenterCrop(), new RoundedCorners(90))
                                        .into(targetImgv);
                                break;

                            // 第6行
                            case 9:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transforms(new RoundedCorners(90),
                                                new OverLapTransform(GlideActivity.this, R.drawable.icon_overdue))
                                        .into(targetImgv);

                                break;

                            // 第7行
                            case 10:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .noTransitionAnim()
                                        .transforms(new CenterInside(), new RoundedCorners(90))
                                        .into(targetImgv);
                                break;
                            case 11:
                                ImageLoader.asGif(GlideActivity.this)
                                        .load(imageArray[i])
                                        .into(targetImgv);
                                break;

                            // 第8行
                            case 12:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
                                        .transforms(new RoundedCorners(90),
                                                new OverLapTransform(GlideActivity.this, R.drawable.icon_overdue))
                                        .into(new AbstractTarget<Drawable>() {
                                            @Override
                                            public void onLoadStarted(@Nullable Drawable placeholder) {
                                                super.onLoadStarted(placeholder);
                                                targetImgv.setImageDrawable(placeholder);
                                            }

                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource) {
                                                targetImgv.setImageDrawable(resource);
                                            }
                                        });
                                break;
                            case 13:
                                ImageLoader.with(GlideActivity.this)
                                        .load(imageArray[i])
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

    /**
     * asBitmap()   以Bitmap方式加载图片。即使加载的图片可能是gif
     * crossFade()， crossFade(int duration) 设置过渡动画，默认是300ms
     * dontAnimate()  取消过渡动画
     * fitCenter() 设置图片填充方式：图片会被完整显示，可能不能完全填充整个ImageView。
     * centerCrop() 设置图片填充方式： 图片不缩放，图片可能会显示不全
     * asGif() 如果gifUrl是一个gif,没有异常发生。但如果gifUrl不是一个Gif，即使是一个完好的图片（非Gif），Glide也会加载失败。.error()回调方法会被调用，并加载错误占位图。
     */

}
