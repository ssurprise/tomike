package com.skx.tomike.cannonlaboratory.ui.activity;

import android.graphics.drawable.Drawable;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;
import com.skx.tomikecommonlibrary.imageloader.ImageLoader;
import com.skx.tomikecommonlibrary.imageloader.target.AbstractTarget;
import com.skx.tomikecommonlibrary.imageloader.transform.CenterCrop;
import com.skx.tomikecommonlibrary.imageloader.transform.CenterInside;
import com.skx.tomikecommonlibrary.imageloader.transform.CircleCrop;
import com.skx.tomikecommonlibrary.imageloader.transform.OverLapTransform;
import com.skx.tomikecommonlibrary.imageloader.transform.RoundedCorners;
import com.skx.tomikecommonlibrary.imageloader.transform.TransformStrategy;

/**
 * 描述 : 对Glide库的二次封装测试demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/23 4:16 PM
 */
public class GlideActivity extends SkxBaseActivity<BaseViewModel> {

    private final String[] imageArray = {
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
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1579611350893&di=3ed61673fc6472a10c6cafe68179f321&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20161119%2F3a5d4a7dd6624f81a8d5ccbd57e2fad3_th.jpg",

            // 第6行
            "http://img2.ph.126.net/gUzX-t8Px6z7Pd9x4urozw==/622622648501911322.jpg",
            "https://b-ssl.duitang.com/uploads/item/201208/03/20120803190720_WVxWS.thumb.700_0.jpeg",

            // 第7行
            "http://www.pujia8.com/static/pics/20171014110026_41.jpg",
            "http://image.haha.mx/2014/02/02/middle/1115779_c221d1fc47b97bb1605cddc9c8aec0a7_1391347675.gif",

            // 第8行
            "http://www.pujia8.com/static/pics/20171014110026_41.jpg",
            "http://www.pujia8.com/static/pics/20171014110026_41.jpg",
    };

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("Glide 加载图片 - 二次封装").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide_demo;
    }

    @Override
    protected void initView() {
        GridLayout mGlPictures = findViewById(R.id.glide_gridLayout);
        ImageView targetIv;
        for (int i = 0, j = imageArray.length; i < j; i++) {
            if (i >= mGlPictures.getChildCount()) {
                break;
            }
            targetIv = (ImageView) mGlPictures.getChildAt(i);

            switch (i) {
                // 第1行
                case 0:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .placeholder(R.color.skx_ff4081)
                            .transformStrategy(TransformStrategy.CENTER_CROP)
                            .into(targetIv);
                    break;
                case 1:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .placeholder(R.color.skx_1f000000)
                            .transformStrategy(TransformStrategy.CENTER_INSIDE)
                            .into(targetIv);
                    break;
                // 第2行
                case 2:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .placeholder(R.color.skx_1f000000)
                            .transformStrategy(TransformStrategy.FIT_CENTER)
                            .into(targetIv);
                    break;
                case 3:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .transformStrategy(TransformStrategy.CIRCLE_CROP)
                            .into(targetIv);
                    break;

                // 第3行
                case 4:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .transforms(new RoundedCorners(90),
                                    new OverLapTransform(GlideActivity.this, R.drawable.icon_used))
                            .into(targetIv);
                    break;

                // 第4行
                case 5:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .noTransitionAnim()
                            .transform(new RoundedCorners(90))
                            .into(targetIv);
                    break;
                case 6:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .transform(new CircleCrop())
                            .into(targetIv);
                    break;

                // 第5行
                case 7:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .transform(new CenterCrop())
                            .into(targetIv);
                    break;
                case 8:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .transforms(new CenterCrop(), new RoundedCorners(90))
                            .into(targetIv);
                    break;

                // 第6行
                case 9:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .transforms(new RoundedCorners(90),
                                    new OverLapTransform(GlideActivity.this, R.drawable.icon_overdue))
                            .into(targetIv);
                    break;

                // 第7行
                case 10:
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .noTransitionAnim()
                            .transforms(new CenterInside(), new RoundedCorners(90))
                            .into(targetIv);
                    break;
                case 11:
                    ImageLoader.with(GlideActivity.this)
                            .asGif()
                            .load(imageArray[i])
                            .noTransitionAnim()
                            .transforms(new CenterInside(), new RoundedCorners(90))
                            .into(targetIv);
                    break;

                // 第8行
                case 12:
                    final ImageView finalTargetIv = targetIv;
                    ImageLoader.with(GlideActivity.this)
                            .load(imageArray[i])
                            .transforms(new RoundedCorners(90),
                                    new OverLapTransform(GlideActivity.this, R.drawable.icon_overdue))
                            .into(new AbstractTarget<Drawable>() {
                                @Override
                                public void onLoadStarted(@Nullable Drawable placeholder) {
                                    super.onLoadStarted(placeholder);
                                    finalTargetIv.setImageDrawable(placeholder);
                                }

                                @Override
                                public void onResourceReady(@NonNull Drawable resource) {
                                    finalTargetIv.setImageDrawable(resource);
                                }
                            });
                    break;
                case 13:
                    try {
                        // todo 这里有问题，还未处理
                        ImageLoader.with(GlideActivity.this)
                                .download()
                                .load(imageArray[i])
                                .into(targetIv);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    /*
     * asBitmap()   以Bitmap方式加载图片。即使加载的图片可能是gif
     * crossFade()， crossFade(int duration) 设置过渡动画，默认是300ms
     * dontAnimate()  取消过渡动画
     * fitCenter() 设置图片填充方式：图片会被完整显示，可能不能完全填充整个ImageView。
     * centerCrop() 设置图片填充方式： 图片不缩放，图片可能会显示不全
     * asGif() 如果gifUrl是一个gif,没有异常发生。但如果gifUrl不是一个Gif，即使是一个完好的图片（非Gif），Glide也会加载失败。.error()回调方法会被调用，并加载错误占位图。
     */

}
