package com.skx.tomike.tanklaboratory.animation.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.view.ScrollChangedScrollView;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.utils.DpPxSpToolKt;
import com.skx.common.utils.ToastTool;

/**
 * 描述 : TabLayout + NestedScrollView 联动效果
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/20 6:15 PM
 */
public class ScrollViewAnchorActivity extends SkxBaseActivity<BaseViewModel> {

    // 头部导航标签
    private final String[] mNavigationTag = {"照片", "概览", "描述", "点评", "位置", "日期", "设施", "规则", "退订", "日记", "推荐"};
    private RelativeLayout mRlNavigationTagContainer;
    private ImageButton mBtnBack;
    private RelativeLayout rl_favoriteContainer;
    private ImageButton btn_favorite;
    private ImageButton btn_favoriteHover;
    private ImageButton btn_share;
    private View view_splitLine;
    private TabLayout tb_navigationTag;
    private View view_bottomSplitLine;
    private ImageView iv_image;
    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;
    private TextView tv_5;
    private TextView tv_6;
    private TextView tv_7;
    private TextView tv_8;
    private TextView tv_9;
    private TextView tv_10;
    private ScrollChangedScrollView mSvContentBody;
    private int translationDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshView();
        initListener();
    }

    @Override
    protected void initParams() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_view_anchor;
    }

    @Override
    protected void subscribeEvent() {
    }

    @Override
    protected void initView() {
        mRlNavigationTagContainer = findViewById(R.id.luDetail_navigationTag_container);
        mBtnBack = findViewById(R.id.btn_back);
        rl_favoriteContainer = findViewById(R.id.rl_favoriteContainer);
        btn_favorite = findViewById(R.id.btn_collect);
        btn_favoriteHover = findViewById(R.id.btn_favoriteHover);
        btn_share = findViewById(R.id.btn_share);
        view_splitLine = findViewById(R.id.view_splitLine);
        tb_navigationTag = findViewById(R.id.luDetail_navigationTag_tabLayout);
        view_bottomSplitLine = findViewById(R.id.view_bottomSplitLine);
        iv_image = findViewById(R.id.iv_image);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_4 = findViewById(R.id.tv_4);
        tv_5 = findViewById(R.id.tv_5);
        tv_6 = findViewById(R.id.tv_6);
        tv_7 = findViewById(R.id.tv_7);
        tv_8 = findViewById(R.id.tv_8);
        tv_9 = findViewById(R.id.tv_9);
        tv_10 = findViewById(R.id.tv_10);
        mSvContentBody = findViewById(R.id.anchor_bodyContainer);
    }

    private void refreshView() {
        // 初始化导航栏透明度
        initNavigationBar();

        tv_1.setText(mNavigationTag[1]);
        tv_2.setText(mNavigationTag[2]);
        tv_3.setText(mNavigationTag[3]);
        tv_4.setText(mNavigationTag[4]);
        tv_5.setText(mNavigationTag[5]);
        tv_6.setText(mNavigationTag[6]);
        tv_7.setText(mNavigationTag[7]);
        tv_8.setText(mNavigationTag[8]);
        tv_9.setText(mNavigationTag[9]);
        tv_10.setText(mNavigationTag[10]);

        for (String item : mNavigationTag) {
            TabLayout.Tab tab = tb_navigationTag.newTab();
            SpannableString spannableString = new SpannableString(item);
            spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, item.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tb_navigationTag.addTab(tab.setText(spannableString));
        }
        tb_navigationTag.getTabAt(0).select();
    }

    private boolean isTagItemClick = false;
    private boolean isFavorite = false;

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.showToast(mActivity, "收藏");
                isFavorite = !isFavorite;
                if (isFavorite) {
                    AnimatorSet animatorSet = new AnimatorSet();
                    ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(btn_favoriteHover, "scaleX", 1.0f, 0f);
                    ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(btn_favoriteHover, "scaleY", 1.0f, 0f);

                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            btn_favoriteHover.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            btn_favoriteHover.setVisibility(View.INVISIBLE);
                        }
                    });
                    animatorSet.setDuration(500);
                    animatorSet.play(scaleXAnimation);
                    animatorSet.play(scaleYAnimation);
                    animatorSet.start();

                } else {
                    AnimatorSet animatorSet = new AnimatorSet();
                    ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(btn_favoriteHover, "scaleX", 0.0f, 1.0f);
                    ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(btn_favoriteHover, "scaleY", 0.0f, 1.0f);
                    animatorSet.setDuration(500);
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            btn_favoriteHover.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            btn_favoriteHover.setVisibility(View.INVISIBLE);
                        }
                    });
                    animatorSet.play(scaleXAnimation);
                    animatorSet.play(scaleYAnimation);
                    animatorSet.start();
                }
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.showToast(mActivity, "分享");
            }
        });
        mSvContentBody.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isTagItemClick = true;
                }
                return false;
            }
        });
        tb_navigationTag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isTagItemClick = false;
                }
                return false;
            }
        });
        mSvContentBody.setScrollViewListener(new ScrollChangedScrollView.ScrollViewListener() {

            @Override
            public void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy) {
                navigationTagAnimation(scrollView);
                scrollRefreshNavigationTag(scrollView);
            }

            @Override
            public void onScrollStop(boolean isStop) {
                if (isStop) {
                    int currentTag;
                    int scrollY = mSvContentBody.getScrollY();
                    // 导航栏的高度
                    int navigationHeight = mRlNavigationTagContainer.getHeight();
                    if (scrollY > tv_10.getTop() - navigationHeight) {
                        currentTag = 10;

                    } else if (scrollY > tv_9.getTop() - navigationHeight) {
                        currentTag = 9;

                    } else if (scrollY > tv_8.getTop() - navigationHeight) {
                        currentTag = 8;

                    } else if (scrollY > tv_7.getTop() - navigationHeight) {
                        currentTag = 7;

                    } else if (scrollY > tv_6.getTop() - navigationHeight) {
                        currentTag = 6;

                    } else if (scrollY > tv_5.getTop() - navigationHeight) {
                        currentTag = 5;

                    } else if (scrollY > tv_4.getTop() - navigationHeight) {
                        currentTag = 4;

                    } else if (scrollY > tv_3.getTop() - navigationHeight) {
                        currentTag = 3;

                    } else if (scrollY > tv_2.getTop() - navigationHeight) {
                        currentTag = 2;

                    } else if (scrollY > tv_1.getTop() - navigationHeight) {
                        currentTag = 1;

                    } else if (scrollY > iv_image.getTop() - navigationHeight) {// 房源图
                        currentTag = 0;

                    } else {
                        currentTag = 0;

                    }
                    tb_navigationTag.setOnTabSelectedListener(null);
                    tb_navigationTag.getTabAt(currentTag).select();
                    tb_navigationTag.setOnTabSelectedListener(onTabSelectedListener);

                }
              /*  // 1.当前滑动的距离
                int currentY = mSvContentBody.getScrollY();
                // 2.图片区的高度
                int picHeight = iv_image.getHeight();
                // 3. 三个条件：已经滑动停止；当前滑动距离< 图片的高度；当前滑动距离 > 最低粘性高度
                if (isStop && currentY < picHeight && currentY > picHeight - DpPxSpTool.dip2px(mContext, 100)) {
                    mSvContentBody.smoothScrollTo(0, tv_1.getTop() - DpPxSpTool.dip2px(mContext, 50));
                }
              */
            }
        });

        tb_navigationTag.setOnTabSelectedListener(onTabSelectedListener);
    }

    TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Log.e("onTabSelected", "onTabSelected");
//                isTagItemClick = false;
            int position = tab.getPosition();
            // 计算点击的导航标签所对应内容区域的高度
            int targetY = 0;
            // 导航栏的高度
            int navigationHeight = mRlNavigationTagContainer.getHeight();
            switch (position) {
                case 1:
                    targetY = tv_1.getTop() - navigationHeight;
                    break;
                case 2:
                    targetY = tv_2.getTop() - navigationHeight;
                    break;
                case 3:
                    targetY = tv_3.getTop() - navigationHeight;
                    break;
                case 4:
                    targetY = tv_4.getTop() - navigationHeight;
                    break;
                case 5:
                    targetY = tv_5.getTop() - navigationHeight;
                    break;
                case 6:
                    targetY = tv_6.getTop() - navigationHeight;
                    break;
                case 7:
                    targetY = tv_7.getTop() - navigationHeight;
                    break;
                case 8:
                    targetY = tv_8.getTop() - navigationHeight;
                    break;
                case 9:
                    targetY = tv_9.getTop() - navigationHeight;
                    break;
                case 10:
                    targetY = tv_10.getTop() - navigationHeight;
                    break;
                default:
                    break;
            }
            // 移动到对应的内容区域
            mSvContentBody.smoothScrollTo(0, targetY + 5);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    /**
     * 用于切换内容模块，相应的改变导航标签
     */
    private int lastTagIndex = 0;
    /**
     * 用于在同一个内容模块内滑动，锁定导航标签，不改变
     * true: 锁定
     * false ; 没有锁定
     */
    private boolean content2NavigateFlagInnerLock = false;

    /**
     * 内容区域滑动 刷新 导航标签
     *
     * @param scrollView 内容模块容器
     */
    private void scrollRefreshNavigationTag(ScrollView scrollView) {
        if (scrollView == null) {
            return;
        }
        int scrollY = scrollView.getScrollY();

        // 导航栏的高度
        int navigationHeight = mRlNavigationTagContainer.getHeight();
        if (scrollY > tv_10.getTop() - navigationHeight) {
            refreshContent2NavigationFlag(10);

        } else if (scrollY > tv_9.getTop() - navigationHeight) {
            refreshContent2NavigationFlag(9);

        } else if (scrollY > tv_8.getTop() - navigationHeight) {
            refreshContent2NavigationFlag(8);

        } else if (scrollY > tv_7.getTop() - navigationHeight) {
            refreshContent2NavigationFlag(7);

        } else if (scrollY > tv_6.getTop() - navigationHeight) {
            refreshContent2NavigationFlag(6);

        } else if (scrollY > tv_5.getTop() - navigationHeight) {
            refreshContent2NavigationFlag(5);

        } else if (scrollY > tv_4.getTop() - navigationHeight) {
            refreshContent2NavigationFlag(4);

        } else if (scrollY > tv_3.getTop() - navigationHeight) {
            refreshContent2NavigationFlag(3);

        } else if (scrollY > tv_2.getTop() - navigationHeight) {
            refreshContent2NavigationFlag(2);

        } else if (scrollY > tv_1.getTop() - navigationHeight) {
            refreshContent2NavigationFlag(1);

        } else if (scrollY > iv_image.getTop() - navigationHeight) {// 房源图
            refreshContent2NavigationFlag(0);

        } else {
            refreshContent2NavigationFlag(0);

        }
    }

    /**
     * 刷新标签
     *
     * @param currentTagIndex 当前模块位置
     */
    private void refreshContent2NavigationFlag(int currentTagIndex) {
        if (lastTagIndex != currentTagIndex) {
            content2NavigateFlagInnerLock = false;
        }
        if (!content2NavigateFlagInnerLock) {
            content2NavigateFlagInnerLock = true;
            if (isTagItemClick) {
                tb_navigationTag.setScrollPosition(currentTagIndex, 0, true);
            }
        }
        lastTagIndex = currentTagIndex;
    }

    private boolean onceOpen = true;
    private boolean onceClose = true;

    /**
     * 导航标签的动画开关
     */
    private void navigationTagAnimation(ScrollView scrollView) {
        if (scrollView == null) {
            return;
        }

        int scrollY = scrollView.getScrollY();
        int mainImageHeight = iv_image.getHeight();
        int navigationBarHeight = mRlNavigationTagContainer.getHeight();
        // 滑动到图片一般的时候开始执行动画
        if (onceOpen && scrollY > mainImageHeight - navigationBarHeight) {
            openNavigationTagAnimator();
        } else if (!onceOpen && onceClose && scrollY <= mainImageHeight - navigationBarHeight) {
            closeNavigationTagAnimator();
        }
    }

    AnimatorSet openAnimSet;
    AnimatorSet closeAnimSet;

    /**
     * 导航标签的打开动画
     */
    private void openNavigationTagAnimator() {
        onceOpen = false;
        onceClose = true;

        ValueAnimator tagContainerAnimator = ValueAnimator.ofInt(0, 242).setDuration(200);
        tagContainerAnimator.setTarget(mRlNavigationTagContainer);
        tagContainerAnimator.setInterpolator(new LinearInterpolator());
        tagContainerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer animatedValue = (Integer) animation.getAnimatedValue();
                setViewBackgroundAlpha(mRlNavigationTagContainer, animatedValue);
                PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
                mBtnBack.setColorFilter(colorFilter);
                btn_favorite.setColorFilter(colorFilter);
                btn_share.setColorFilter(colorFilter);
            }
        });

        ObjectAnimator shareObjectAnimator = ObjectAnimator.ofFloat(btn_share, "translationX", btn_share.getTranslationX(), 0);
        shareObjectAnimator.setDuration(300);

        ObjectAnimator collectObjectAnimator = ObjectAnimator.ofFloat(rl_favoriteContainer, "translationX", rl_favoriteContainer.getTranslationX(), 0);
        collectObjectAnimator.setDuration(300);

        ObjectAnimator splitLineObjectAnimator = ObjectAnimator.ofFloat(view_splitLine, "translationX", view_splitLine.getTranslationX(), 0);
        splitLineObjectAnimator.setDuration(300);

        ObjectAnimator tagObjectAnimator = ObjectAnimator.ofFloat(tb_navigationTag, "translationX", tb_navigationTag.getTranslationX(), 0);
        tagObjectAnimator.setDuration(300);

        openAnimSet = new AnimatorSet();
        openAnimSet.play(shareObjectAnimator);
        openAnimSet.play(collectObjectAnimator).after(100);
        openAnimSet.play(splitLineObjectAnimator).after(200);
        openAnimSet.play(tagObjectAnimator).after(300);
        openAnimSet.play(tagContainerAnimator);
        openAnimSet.setInterpolator(new AccelerateDecelerateInterpolator());
        openAnimSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (closeAnimSet != null && closeAnimSet.isRunning()) {
                    closeAnimSet.cancel();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        openAnimSet.start();
    }

    /**
     * 导航标签的关闭动画
     */
    private void closeNavigationTagAnimator() {
        onceClose = false;
        onceOpen = true;
        ValueAnimator tagContainerAnimator = ValueAnimator.ofInt(242, 0).setDuration(200);
        tagContainerAnimator.setTarget(mRlNavigationTagContainer);
        tagContainerAnimator.setInterpolator(new LinearInterpolator());
        tagContainerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer animatedValue = (Integer) animation.getAnimatedValue();
                setViewBackgroundAlpha(mRlNavigationTagContainer, animatedValue);
                PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
                mBtnBack.setColorFilter(colorFilter);
                btn_favorite.setColorFilter(colorFilter);
                btn_share.setColorFilter(colorFilter);
            }
        });

        ObjectAnimator shareObjectAnimator = ObjectAnimator.ofFloat(btn_share, "translationX", 0, translationDistance * 1.0f);
        shareObjectAnimator.setDuration(300);

        ObjectAnimator collectObjectAnimator = ObjectAnimator.ofFloat(rl_favoriteContainer, "translationX", 0, translationDistance * 1.0f);
        collectObjectAnimator.setDuration(300);

        ObjectAnimator splitLineObjectAnimator = ObjectAnimator.ofFloat(view_splitLine, "translationX", 0, translationDistance * 1.0f);
        splitLineObjectAnimator.setDuration(300);

//        ObjectAnimator tagObjectAnimator = ObjectAnimator.ofFloat(rv_navigationTag, "translationX", 0, translationDistance * 1.0f);
        ObjectAnimator tagObjectAnimator = ObjectAnimator.ofFloat(tb_navigationTag, "translationX", 0, translationDistance * 1.0f);
        tagObjectAnimator.setDuration(300);

        closeAnimSet = new AnimatorSet();
        closeAnimSet.play(tagObjectAnimator);
        closeAnimSet.play(splitLineObjectAnimator).after(100);
        closeAnimSet.play(collectObjectAnimator).after(200);
        closeAnimSet.play(shareObjectAnimator).after(300);
        closeAnimSet.play(tagContainerAnimator).after(300);
        closeAnimSet.setInterpolator(new AccelerateDecelerateInterpolator());
        closeAnimSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (openAnimSet != null && openAnimSet.isRunning()) {
                    openAnimSet.cancel();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        closeAnimSet.start();
    }

    /**
     * 初始化导航栏
     * 包括：
     * 初始化导航栏背景透明度，
     * 初始化底部分界线背景透明度，
     * 初始化返回、分享、收藏按钮颜色。
     * 初始化返回、分享、收藏按钮、导航标签偏移距离
     */
    private void initNavigationBar() {
        setViewBackgroundAlpha(mRlNavigationTagContainer, 0);
        setViewBackgroundAlpha(view_bottomSplitLine, 0);

        PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
        mBtnBack.setColorFilter(colorFilter);
        btn_favorite.setColorFilter(colorFilter);
        btn_share.setColorFilter(colorFilter);

        calculateNavigationTagTranslateDistance();
        btn_share.setTranslationX(translationDistance * 1.0f);
        rl_favoriteContainer.setTranslationX(translationDistance * 1.0f);
        view_splitLine.setTranslationX(translationDistance * 1.0f);
//        rv_navigationTag.setTranslationX(translationDistance * 1.0f);
        tb_navigationTag.setTranslationX(translationDistance * 1.0f);
    }

    /**
     * 设置View的背景透明度
     *
     * @param view
     * @param alpha
     */
    public void setViewBackgroundAlpha(View view, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.getBackground().mutate().setAlpha(alpha);
        } else {
            Drawable drawable = view.getBackground();
            if (drawable != null) {
                drawable.setAlpha(alpha);
            }
        }
    }

    /**
     * 计算导航标签偏移距离
     */
    public void calculateNavigationTagTranslateDistance() {
        translationDistance = DpPxSpToolKt.dip2px(mActivity, 268 - 24 - 9);
    }
}
