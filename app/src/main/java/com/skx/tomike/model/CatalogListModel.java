package com.skx.tomike.model;

import com.skx.tomike.activity.ColorMatrix2Activity;
import com.skx.tomike.activity.ColorMatrixActivity;
import com.skx.tomike.activity.DoubleFormatActivity;
import com.skx.tomike.activity.EmojiFilterActivity;
import com.skx.tomike.activity.KeyboardActivity;
import com.skx.tomike.activity.SpannableStringBuilderActivity;
import com.skx.tomike.bomberlaboratory.basics.UrlEncodeActivity;
import com.skx.tomike.activity.WatermarkActivity;
import com.skx.tomike.activity.xzdz.EffectGroupActivity;
import com.skx.tomike.bomberlaboratory.basics.UrlParseActivity;
import com.skx.tomike.bomberlaboratory.generic.GenericTestActivity;
import com.skx.tomike.bomberlaboratory.reflect.ReflectTestActivity;
import com.skx.tomike.bomberlaboratory.thread.ThreadActivity;
import com.skx.tomike.bomberlaboratory.thread.ThreadCallbackActivity;
import com.skx.tomike.bomberlaboratory.thread.ThreadCommunicationActivity;
import com.skx.tomike.bomberlaboratory.thread.ThreadDaemonActivity;
import com.skx.tomike.bomberlaboratory.thread.ThreadInterruptActivity;
import com.skx.tomike.bomberlaboratory.thread.ThreadSynchronizedActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.AopTestActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.AsyncTaskActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.GlideActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.HandlerActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.ImageLoadActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.LifecycleActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.NfcGroupActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.NotificationActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.OkHttpActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.OpenFileActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.OutterStartActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.PermissionIntroActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.PhotoAlbumsActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.RetrofitActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.RoomTestActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.RxJavaActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.TransparentThemeActivity;
import com.skx.tomike.cannonlaboratory.ui.activity.ZoomImageActivity;
import com.skx.tomike.javabean.CatalogItem;
import com.skx.tomike.tacticallaboratory.activity.LinkedDemoActivity;
import com.skx.tomike.tacticallaboratory.activity.QueueDemoActivity;
import com.skx.tomike.tacticallaboratory.activity.StackDemoActivity;
import com.skx.tomike.tacticallaboratory.activity.ViewModelActivity;
import com.skx.tomike.tacticallaboratory.pattern.chainofresponsibility.ChainOfResponsibilityPatternActivity;
import com.skx.tomike.tacticallaboratory.pattern.memento.MementoPatternActivity;
import com.skx.tomike.tacticallaboratory.pattern.observer.ObserverPatternActivity;
import com.skx.tomike.tacticallaboratory.pattern.prototype.PrototypePatternActivity;
import com.skx.tomike.tacticallaboratory.pattern.proxy.ProxyPatternActivity;
import com.skx.tomike.tacticallaboratory.pattern.strategy.StrategyPatternActivity;
import com.skx.tomike.tanklaboratory.animation.activity.BezierAnimatorActivity;
import com.skx.tomike.tanklaboratory.animation.activity.CircularRevealActivity;
import com.skx.tomike.tanklaboratory.animation.activity.LayoutTransitionActivity;
import com.skx.tomike.tanklaboratory.animation.activity.PropertyAnimatorActivity;
import com.skx.tomike.tanklaboratory.animation.activity.ScrollViewAnchorActivity;
import com.skx.tomike.tanklaboratory.animation.activity.ScrollerPracticeActivity;
import com.skx.tomike.tanklaboratory.animation.activity.ShakeAnimatorActivity;
import com.skx.tomike.tanklaboratory.animation.activity.ShareElementActivity;
import com.skx.tomike.tanklaboratory.animation.activity.TweenAnimationActivity;
import com.skx.tomike.tanklaboratory.widget.activity.CardViewActivity;
import com.skx.tomike.tanklaboratory.widget.activity.CheckBoxActivity;
import com.skx.tomike.tanklaboratory.widget.activity.ConstraintLayoutActivity;
import com.skx.tomike.tanklaboratory.widget.activity.CoordinatorLayoutActivity;
import com.skx.tomike.tanklaboratory.widget.activity.CoordinatorLayoutCaseActivity;
import com.skx.tomike.tanklaboratory.widget.activity.DrawableTint2Activity;
import com.skx.tomike.tanklaboratory.widget.activity.DrawableTintActivity;
import com.skx.tomike.tanklaboratory.widget.activity.DrawerLayoutActivity;
import com.skx.tomike.tanklaboratory.widget.activity.EditTextCursorActivity;
import com.skx.tomike.tanklaboratory.widget.activity.FloatingActionButtonActivity;
import com.skx.tomike.tanklaboratory.widget.activity.FlowLayoutActivity;
import com.skx.tomike.tanklaboratory.widget.activity.LightDarkTextActivity;
import com.skx.tomike.tanklaboratory.widget.activity.Lowercase2UppercaseActivity;
import com.skx.tomike.tanklaboratory.widget.activity.MatrixImageActivity;
import com.skx.tomike.tanklaboratory.widget.activity.NavigationBarActivity;
import com.skx.tomike.tanklaboratory.widget.activity.NestedScrollViewViewPagerActivity;
import com.skx.tomike.tanklaboratory.widget.activity.PageIndicatorActivity;
import com.skx.tomike.tanklaboratory.widget.activity.PopupWindowActivity;
import com.skx.tomike.tanklaboratory.widget.activity.RadioGroupActivity;
import com.skx.tomike.tanklaboratory.widget.activity.RecyclerAsViewPagerActivity;
import com.skx.tomike.tanklaboratory.widget.activity.RecyclerViewChildCountActivity;
import com.skx.tomike.tanklaboratory.widget.activity.RecyclerViewItemUpdateActivity;
import com.skx.tomike.tanklaboratory.widget.activity.RecyclerViewScrollToPositionActivity;
import com.skx.tomike.tanklaboratory.widget.activity.SetTextIsSelectableTestActivity;
import com.skx.tomike.tanklaboratory.widget.activity.ShadowActivity;
import com.skx.tomike.tanklaboratory.widget.activity.ShapeDrawableHelperActivity;
import com.skx.tomike.tanklaboratory.widget.activity.ShapeViewActivity;
import com.skx.tomike.tanklaboratory.widget.activity.SnackBarActivity;
import com.skx.tomike.tanklaboratory.widget.activity.StatusBarNavigationBarActivity;
import com.skx.tomike.tanklaboratory.widget.activity.SwipeRefreshLayoutActivity;
import com.skx.tomike.tanklaboratory.widget.activity.TabLayoutHelperActivity;
import com.skx.tomike.tanklaboratory.widget.activity.TabLayoutIndicatorActivity;
import com.skx.tomike.tanklaboratory.widget.activity.TextSwitcherActivity;
import com.skx.tomike.tanklaboratory.widget.activity.TextWordSpacingActivity;
import com.skx.tomike.tanklaboratory.widget.activity.VectorDrawableActivity;
import com.skx.tomike.tanklaboratory.widget.activity.ViewFocusActivity;
import com.skx.tomike.tanklaboratory.widget.activity.ViewPager2Activity;
import com.skx.tomike.tanklaboratory.widget.activity.ViewPagerInfiniteLoopActivity;
import com.skx.tomike.tanklaboratory.widget.activity.ViewPagerMultiplePageActivity;
import com.skx.tomike.tanklaboratory.widget.activity.ViewPagerWrapContentActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 : 目录列表model
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2017/12/29 6:08 PM
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
    private static final String GROUP_DATA_STRUCTURE_AND_ALGORITHM = "数据结构和算法";

    private static LinkedHashMap<String, List<CatalogItem>> mCatalogGroupMap = new LinkedHashMap<>();

    /**
     * 小猪分组
     */
    private static final List<CatalogItem> mXzCatalogs = new ArrayList<>();
    /**
     * view 类分组
     */
    private static final List<CatalogItem> mViewCatalogs = new ArrayList<>();
    /**
     * 动效类分组
     */
    private static final List<CatalogItem> mAnimatorCatalogs = new ArrayList<>();
    /**
     * 功能类分组
     */
    private static final List<CatalogItem> mFunctionCatalogs = new ArrayList<>();
    /**
     * 工具类分组
     */
    private static final List<CatalogItem> mUtilCatalogs = new ArrayList<>();
    /**
     * 设计模式类分组
     */
    private static final List<CatalogItem> mDesignPatternCatalogs = new ArrayList<>();
    /**
     * java
     */
    private static final List<CatalogItem> mJavaCatalogs = new ArrayList<>();
    /**
     * 其他
     */
    private static final List<CatalogItem> mOtherCatalogs = new ArrayList<>();
    /**
     * 数据结构和算法
     */
    private static final List<CatalogItem> mDataStructureCatalogs = new ArrayList<>();

    static {
        mXzCatalogs.add(new CatalogItem("订单V2动效", EffectGroupActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_XIAOZHU, mXzCatalogs);


        // 坦克实验室 - view 篇
        mViewCatalogs.add(new CatalogItem("View 焦点", ViewFocusActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TextView 字间距", TextWordSpacingActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TextView 复制测试", SetTextIsSelectableTestActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TextSwitcher 测试", TextSwitcherActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("EditText 光标", EditTextCursorActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("EditText 明暗文切换", LightDarkTextActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TextView/EditText 小写转大写", Lowercase2UppercaseActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("自定义显示方向ImageView", MatrixImageActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RadioGroup 单选", RadioGroupActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("CheckBox 更换自定义icon", CheckBoxActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("自定义ShapeView", ShapeViewActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("FlowLayout 流式布局", FlowLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ConstraintLayout约束布局", ConstraintLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("CoordinatorLayout", CoordinatorLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("CoordinatorLayout效果", CoordinatorLayoutCaseActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("SwipeRefreshLayout 下拉刷新", SwipeRefreshLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("卡片CardView", CardViewActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TabLayout 指示器", TabLayoutIndicatorActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TabLayout使用扩展", NavigationBarActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("抽屉效果", DrawerLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("投影", ShadowActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("FloatingActionButton", FloatingActionButtonActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("SnackBar", SnackBarActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("PopupWindow", PopupWindowActivity.class.getName()));


        mViewCatalogs.add(new CatalogItem("ViewPager 一屏多展示", ViewPagerMultiplePageActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ViewPager 自适应高度", ViewPagerWrapContentActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ViewPager 无限循环+自动轮播", ViewPagerInfiniteLoopActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ViewPager N种页签指示器", PageIndicatorActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ViewPager2 androidx", ViewPager2Activity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RecyclerView 添加/删除item", RecyclerViewItemUpdateActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RecyclerView 滑动到指定position", RecyclerViewScrollToPositionActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RecyclerView 仿ViewPager", RecyclerAsViewPagerActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RecyclerView child count测试", RecyclerViewChildCountActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("NestedScrollView+ViewPager", NestedScrollViewViewPagerActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TabLayout+ScrollView", ScrollViewAnchorActivity.class.getName()));

        mCatalogGroupMap.put(GROUP_VIEW, mViewCatalogs);


        // 坦克实验室 - 动效 篇
        mAnimatorCatalogs.add(new CatalogItem("Layout添加/删除子view过渡效果", LayoutTransitionActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("共享元素转场动画", ShareElementActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("圆形揭示效果", CircularRevealActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("补间动画", TweenAnimationActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("属性动画", PropertyAnimatorActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("shake动画", ShakeAnimatorActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("贝塞尔曲线", BezierAnimatorActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("view内容移动 - scroller", ScrollerPracticeActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_ANIMATOR, mAnimatorCatalogs);


        // 加农炮实验室
        mFunctionCatalogs.add(new CatalogItem("lifecycle", LifecycleActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("Handler", HandlerActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("AsyncTask", AsyncTaskActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("通知", NotificationActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("打开其他App", OutterStartActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("透明Activity", TransparentThemeActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("AOP 测试", AopTestActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("NFC功能测试", NfcGroupActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("okhttp", OkHttpActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("retrofit", RetrofitActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("RxJava", RxJavaActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("6.0权限", PermissionIntroActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("Room - 最近浏览案例", RoomTestActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("相册", PhotoAlbumsActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("图片缩放", ZoomImageActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("Glide图片加载 - 二次封装", GlideActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("大图片加载", ImageLoadActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("打开文件管理器", OpenFileActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_FUNCTION, mFunctionCatalogs);


        mUtilCatalogs.add(new CatalogItem("TabLayout助手", TabLayoutHelperActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("shapeDrawable工具类", ShapeDrawableHelperActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("键盘、输入法", KeyboardActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("水印图", WatermarkActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("Double数据转换", DoubleFormatActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("编码/解码", UrlEncodeActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("Url 解析", UrlParseActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("状态栏-底部导航栏高度", StatusBarNavigationBarActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_UTIL, mUtilCatalogs);


        mOtherCatalogs.add(new CatalogItem("Tint 着色1 -DrawableCompat", DrawableTintActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("Tint 着色2 -xml、选择器", DrawableTint2Activity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("VectorDrawable", VectorDrawableActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("emoji过滤", EmojiFilterActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("SpannableString多样化展示", SpannableStringBuilderActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("图像颜色处理", ColorMatrixActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("图像颜色处理2", ColorMatrix2Activity.class.getName()));
        mCatalogGroupMap.put(GROUP_OTHER, mOtherCatalogs);


        // 作战实验室
        mDesignPatternCatalogs.add(new CatalogItem("MVVM-ViewModel", ViewModelActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("观察者模式", ObserverPatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("备忘录模式", MementoPatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("原型模式", PrototypePatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("策略模式", StrategyPatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("责任链模式", ChainOfResponsibilityPatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("代理模式", ProxyPatternActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_DESIGN_PATTERNS, mDesignPatternCatalogs);


        // 战略轰炸机实验室
        mJavaCatalogs.add(new CatalogItem("多线程", ThreadActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程守护", ThreadDaemonActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程中断", ThreadInterruptActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程同步", ThreadSynchronizedActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程间通信 - wait/notify", ThreadCommunicationActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程返回值", ThreadCallbackActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("泛型理解", GenericTestActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("反射理解", ReflectTestActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_JAVA, mJavaCatalogs);


        // 数据结构和算法
        mDesignPatternCatalogs.add(new CatalogItem("栈", StackDemoActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("队列", QueueDemoActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("链表", LinkedDemoActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_DATA_STRUCTURE_AND_ALGORITHM, mDataStructureCatalogs);
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
