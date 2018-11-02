package com.skx.tomike.model;

import com.skx.tomike.activity.GenericTestActivity;
import com.skx.tomike.activity.PrecomputedTextActivity;
import com.skx.tomike.activity.designpattern.ChainOfResponsibilityPatternActivity;
import com.skx.tomike.activity.designpattern.StrategyPatternActivity;
import com.skx.tomike.activity.designpattern.ViewModelActivity;
import com.skx.tomike.activity.effect.CardViewActivity;
import com.skx.tomike.activity.effect.CircularRevealActivity;
import com.skx.tomike.activity.designpattern.ClonePatternActivity;
import com.skx.tomike.activity.function.AopTestActivity;
import com.skx.tomike.activity.function.OutterStartActivity;
import com.skx.tomike.activity.function.FloatingWindowActivity;
import com.skx.tomike.activity.widget.CollapsingToolbarLayoutActivity;
import com.skx.tomike.activity.ColorMatrix2Activity;
import com.skx.tomike.activity.ColorMatrixActivity;
import com.skx.tomike.activity.widget.ConstraintLayoutActivity;
import com.skx.tomike.activity.widget.CoordinatorLayoutActivity;
import com.skx.tomike.activity.CoordinatorLayoutCaseActivity;
import com.skx.tomike.activity.widget.CustomViewActivity;
import com.skx.tomike.activity.DoubleFormatActivity;
import com.skx.tomike.activity.DrawerLayoutActivity;
import com.skx.tomike.activity.effect.LayoutTransitionActivity;
import com.skx.tomike.activity.xzdz.EffectGroupActivity;
import com.skx.tomike.activity.EmojiFilterActivity;
import com.skx.tomike.activity.widget.FloatingActionButtonActivity;
import com.skx.tomike.activity.widget.FlowLayoutActivity;
import com.skx.tomike.activity.GetTopTestActivity;
import com.skx.tomike.activity.function.GlideActivity;
import com.skx.tomike.activity.HeaderFooterRecyclerViewActivity;
import com.skx.tomike.activity.function.ImageLoadActivity;
import com.skx.tomike.activity.ImageSpanActivity;
import com.skx.tomike.activity.KeyboardActivity;
import com.skx.tomike.activity.MatrixImageActivity;
import com.skx.tomike.activity.designpattern.MementoPatternActivity;
import com.skx.tomike.activity.NavigationBarActivity;
import com.skx.tomike.activity.function.NfcGroupActivity;
import com.skx.tomike.activity.function.OkHttpActivity;
import com.skx.tomike.activity.PageIndicatorActivity;
import com.skx.tomike.activity.function.PermissionIntroActivity;
import com.skx.tomike.activity.function.PhotoAlbumsActivity;
import com.skx.tomike.activity.PopupWindowActivity;
import com.skx.tomike.activity.PropertyAnimatorActivity;
import com.skx.tomike.activity.function.RetrofitActivity;
import com.skx.tomike.activity.function.RxJavaActivity;
import com.skx.tomike.activity.ScrollViewAnchorActivity;
import com.skx.tomike.activity.ScrollerPracticeActivity;
import com.skx.tomike.activity.SetTextIsSelectableTestActivity;
import com.skx.tomike.activity.ShadowActivity;
import com.skx.tomike.activity.ShapeDrawableHelperActivity;
import com.skx.tomike.activity.effect.ShareElementActivity;
import com.skx.tomike.activity.widget.SnackBarActivity;
import com.skx.tomike.activity.SpannableStringBuilderActivity;
import com.skx.tomike.activity.StatusBarNavigationBarActivity;
import com.skx.tomike.activity.effect.SwipeRefreshLayoutActivity;
import com.skx.tomike.activity.TabLayoutActivity;
import com.skx.tomike.activity.TabLayoutHelperActivity;
import com.skx.tomike.activity.TextSwitcherActivity;
import com.skx.tomike.activity.TextWordSpacingActivity;
import com.skx.tomike.activity.Tint_BackgroundTintActivity;
import com.skx.tomike.activity.Tint_DrawableTintActivity;
import com.skx.tomike.activity.UrlEncodeActivity;
import com.skx.tomike.activity.VectorDrawableActivity;
import com.skx.tomike.activity.ViewPagerInfiniteLoopActivity;
import com.skx.tomike.activity.VpMultiplePageActivity;
import com.skx.tomike.activity.WatermarkActivity;
import com.skx.tomike.activity.XZHomepageNewActivity;
import com.skx.tomike.activity.ZoomHeaderListViewActivity;
import com.skx.tomike.activity.ZoomImageActivity;
import com.skx.tomike.javabean.CatalogItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 目录列表model
 *
 * @author shiguotao
 * Created on 2017/12/29.
 */
public class CatalogListModel {

    private static final String GROUP_XIAOZHU = "小猪";
    private static final String GROUP_ANIMATOR = "动效类";
    private static final String GROUP_VIEW = "控件";
    private static final String GROUP_UTIL = "工具";
    private static final String GROUP_FUNCTION = "功能";
    private static final String GROUP_DESIGN_PATTERNS = "设计模式";
    private static final String GROUP_JAVA = "JAVA";
    private static final String GROUP_OTHER = "其他";

    private static LinkedHashMap<String, List<CatalogItem>> mCatalogGroupMap = new LinkedHashMap<>();

    /**
     * 小猪分组
     */
    private static List<CatalogItem> mXzCatalogs = new ArrayList<>();
    /**
     * 动效类分组
     */
    private static List<CatalogItem> mAnimatorCatalogs = new ArrayList<>();
    /**
     * view 类分组
     */
    private static List<CatalogItem> mViewCatalogs = new ArrayList<>();
    /**
     * 工具类分组
     */
    private static List<CatalogItem> mUtilCatalogs = new ArrayList<>();
    /**
     * 功能类分组
     */
    private static List<CatalogItem> mFunctionCatalogs = new ArrayList<>();
    /**
     * 设计模式类分组
     */
    private static List<CatalogItem> mDesignPatternCatalogs = new ArrayList<>();
    /**
     * java
     */
    private static List<CatalogItem> mJavaCatalogs = new ArrayList<>();
    /**
     * 其他
     */
    private static List<CatalogItem> mOtherCatalogs = new ArrayList<>();

    static {
        mXzCatalogs.add(new CatalogItem("首页V2", XZHomepageNewActivity.class.getName()));
        mXzCatalogs.add(new CatalogItem("订单V2动效", EffectGroupActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_XIAOZHU, mXzCatalogs);


        mAnimatorCatalogs.add(new CatalogItem("Layout添加/删除子view过渡效果", LayoutTransitionActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("共享元素转场动画", ShareElementActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("TabLayout联动", TabLayoutActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("抽屉效果", DrawerLayoutActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("下拉放大头图", ZoomHeaderListViewActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("下拉刷新", SwipeRefreshLayoutActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("N种页签指示器", PageIndicatorActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("CoordinatorLayout效果", CoordinatorLayoutCaseActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("圆形揭示效果", CircularRevealActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("属性动画", PropertyAnimatorActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("ViewPager无限循环+自动轮播", ViewPagerInfiniteLoopActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("ViewPager一屏多展示", VpMultiplePageActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("view内容移动", ScrollerPracticeActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_ANIMATOR, mAnimatorCatalogs);


        mFunctionCatalogs.add(new CatalogItem("打开其他App", OutterStartActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("全局浮层", FloatingWindowActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("AOP 测试", AopTestActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("NFC功能测试", NfcGroupActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("okhttp", OkHttpActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("RxJava", RxJavaActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("retrofit", RetrofitActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("Glide图片加载", GlideActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("6.0权限", PermissionIntroActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("大图片加载", ImageLoadActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("相册", PhotoAlbumsActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_FUNCTION, mFunctionCatalogs);


        mUtilCatalogs.add(new CatalogItem("TabLayout助手", TabLayoutHelperActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("shapeDrawable工具类", ShapeDrawableHelperActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("keyboard", KeyboardActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("水印图", WatermarkActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("图片缩放", ZoomImageActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("Double数据转换", DoubleFormatActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("url编码", UrlEncodeActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("状态栏-底部导航栏高度", StatusBarNavigationBarActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_UTIL, mUtilCatalogs);


        mViewCatalogs.add(new CatalogItem("PrecomputedText", PrecomputedTextActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ConstraintLayout约束布局", ConstraintLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("CoordinatorLayout", CoordinatorLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("CollapsingToolbarLayout", CollapsingToolbarLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("自定义View", CustomViewActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("导航栏封装", NavigationBarActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("Tint 着色1 -DrawableCompat", Tint_DrawableTintActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("Tint 着色2 -xml、选择器", Tint_BackgroundTintActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("VectorDrawable", VectorDrawableActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TextView复制测试", SetTextIsSelectableTestActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("卡片", CardViewActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("Snackbar", SnackBarActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("PopupWindow", PopupWindowActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("FlowLayout", FlowLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("FloatingActionButton", FloatingActionButtonActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_VIEW, mViewCatalogs);


        mDesignPatternCatalogs.add(new CatalogItem("MVVM-ViewModel", ViewModelActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("备忘录模式", MementoPatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("原型模式", ClonePatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("策略模式", StrategyPatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("责任链模式", ChainOfResponsibilityPatternActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_DESIGN_PATTERNS, mDesignPatternCatalogs);


        mOtherCatalogs.add(new CatalogItem("getTop()测试", GetTopTestActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("投影", ShadowActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("TextView字间距", TextWordSpacingActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("TextSwitcher测试", TextSwitcherActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("TextView图文混排", ImageSpanActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("emoji过滤", EmojiFilterActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("RecyclerView添加头布局", HeaderFooterRecyclerViewActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("ScrollView锚点", ScrollViewAnchorActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("SpannableString多样化展示", SpannableStringBuilderActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("Matrix自定义版", MatrixImageActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("图像颜色处理", ColorMatrixActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("图像颜色处理2", ColorMatrix2Activity.class.getName()));
        mCatalogGroupMap.put(GROUP_OTHER, mOtherCatalogs);


        mJavaCatalogs.add(new CatalogItem("泛型理解", GenericTestActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_JAVA, mJavaCatalogs);
    }

    /**
     * 创建目录分组
     *
     * @return 目录分组结构
     */
    public static List<CatalogCellModel> createCatalogGroup() {
        if (mCatalogGroupMap != null) {
            List<CatalogCellModel> allCatalogs = new ArrayList<>();

            for (Map.Entry<String, List<CatalogItem>> entry : mCatalogGroupMap.entrySet()) {
                List<CatalogItem> tempGroup = entry.getValue();

                CatalogCellModel parentModel = new CatalogCellModel(entry.getKey(), "", null, null);
                allCatalogs.add(parentModel);
                for (CatalogItem item : tempGroup) {
                    CatalogCellModel cellModel = new CatalogCellModel(item.getName(), item.getValue(), parentModel, null);
                    parentModel.addChild(cellModel);
                    allCatalogs.add(cellModel);
                }
            }
            return allCatalogs;
        }
        return null;
    }

    public static LinkedHashMap<String, List<CatalogItem>> getCatalogGroupMp() {
        return mCatalogGroupMap;
    }
}