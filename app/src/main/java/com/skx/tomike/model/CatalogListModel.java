package com.skx.tomike.model;

import com.skx.tomike.bean.CatalogItem;
import com.skx.tomike.bomber.basics.Base64Activity;
import com.skx.tomike.bomber.basics.DoubleFormatActivity;
import com.skx.tomike.bomber.basics.GsonParseActivity;
import com.skx.tomike.bomber.basics.UrlEncodeActivity;
import com.skx.tomike.bomber.basics.UrlParseActivity;
import com.skx.tomike.bomber.basics.XmlParseActivity;
import com.skx.tomike.bomber.coroutine.CoroutineActivity;
import com.skx.tomike.bomber.generic.GenericTestActivity;
import com.skx.tomike.bomber.reflect.ReflectTestActivity;
import com.skx.tomike.bomber.thread.ReentrantLockActivity;
import com.skx.tomike.bomber.thread.ThreadStartActivity;
import com.skx.tomike.bomber.thread.ThreadCallbackActivity;
import com.skx.tomike.bomber.thread.ThreadCommunicationActivity;
import com.skx.tomike.bomber.thread.ThreadDaemonActivity;
import com.skx.tomike.bomber.thread.ThreadInterruptActivity;
import com.skx.tomike.bomber.thread.ThreadPoolActivity;
import com.skx.tomike.bomber.thread.ThreadPoolExecutorsActivity;
import com.skx.tomike.bomber.thread.ThreadSynchronizedActivity;
import com.skx.tomike.cannon.ui.activity.AopTestActivity;
import com.skx.tomike.cannon.ui.activity.AsyncTaskActivity;
import com.skx.tomike.cannon.ui.activity.CountDownTimerActivity;
import com.skx.tomike.cannon.ui.activity.DeviceInformationActivity;
import com.skx.tomike.cannon.ui.activity.GlideActivity;
import com.skx.tomike.cannon.ui.activity.HandlerActivity;
import com.skx.tomike.cannon.ui.activity.HotfixActivity;
import com.skx.tomike.cannon.ui.activity.ImageLoadActivity;
import com.skx.tomike.cannon.ui.activity.KeyboardActivity;
import com.skx.tomike.cannon.ui.activity.LifecycleActivity;
import com.skx.tomike.cannon.ui.activity.NfcGroupActivity;
import com.skx.tomike.cannon.ui.activity.NotificationActivity;
import com.skx.tomike.cannon.ui.activity.OkHttpActivity;
import com.skx.tomike.cannon.ui.activity.OpenFileActivity;
import com.skx.tomike.cannon.ui.activity.OutterStartActivity;
import com.skx.tomike.cannon.ui.activity.ParcelableActivity;
import com.skx.tomike.cannon.ui.activity.PermissionIntroActivity;
import com.skx.tomike.cannon.ui.activity.PhotoAlbumsActivity;
import com.skx.tomike.cannon.ui.activity.RebootAppActivity;
import com.skx.tomike.cannon.ui.activity.RetrofitActivity;
import com.skx.tomike.cannon.ui.activity.RoomTestActivity;
import com.skx.tomike.cannon.ui.activity.RxJavaActivity;
import com.skx.tomike.cannon.ui.activity.ServiceDemoActivity;
import com.skx.tomike.cannon.ui.activity.TransparentThemeActivity;
import com.skx.tomike.cannon.ui.activity.ViewModelActivity;
import com.skx.tomike.cannon.ui.activity.WithCallbackActivity;
import com.skx.tomike.cannon.ui.activity.ZXingActivity;
import com.skx.tomike.cannon.ui.activity.ZoomImageActivity;
import com.skx.tomike.missile.activity.ArrayDemoActivity;
import com.skx.tomike.missile.activity.DueueDemoActivity;
import com.skx.tomike.missile.activity.LinkedDemoActivity;
import com.skx.tomike.missile.activity.QueueDemoActivity;
import com.skx.tomike.missile.activity.SortDemoActivity;
import com.skx.tomike.missile.activity.StackDemoActivity;
import com.skx.tomike.missile.activity.TreeDemoActivity;
import com.skx.tomike.missile.pattern.chainofresponsibility.ChainOfResponsibilityPatternActivity;
import com.skx.tomike.missile.pattern.memento.MementoPatternActivity;
import com.skx.tomike.missile.pattern.observer.ObserverPatternActivity;
import com.skx.tomike.missile.pattern.prototype.PrototypePatternActivity;
import com.skx.tomike.missile.pattern.proxy.ProxyPatternActivity;
import com.skx.tomike.missile.pattern.strategy.StrategyPatternActivity;
import com.skx.tomike.tank.animation.activity.BezierAnimatorActivity;
import com.skx.tomike.tank.animation.activity.CircularRevealActivity;
import com.skx.tomike.tank.animation.activity.LayoutTransitionActivity;
import com.skx.tomike.tank.animation.activity.PropertyAnimatorActivity;
import com.skx.tomike.tank.animation.activity.ScrollChangeTitleActivity;
import com.skx.tomike.tank.animation.activity.ScrollViewAnchorActivity;
import com.skx.tomike.tank.animation.activity.ScrollZoomImage2Activity;
import com.skx.tomike.tank.animation.activity.ScrollZoomImage3Activity;
import com.skx.tomike.tank.animation.activity.ScrollerPracticeActivity;
import com.skx.tomike.tank.animation.activity.ShakeAnimatorActivity;
import com.skx.tomike.tank.animation.activity.ShareElementActivity;
import com.skx.tomike.tank.animation.activity.TweenAnimationActivity;
import com.skx.tomike.tank.widget.activity.CardViewActivity;
import com.skx.tomike.tank.widget.activity.CheckBoxActivity;
import com.skx.tomike.tank.widget.activity.ColorMatrix2Activity;
import com.skx.tomike.tank.widget.activity.ColorMatrixActivity;
import com.skx.tomike.tank.widget.activity.ConstraintLayoutActivity;
import com.skx.tomike.tank.widget.activity.CoordinatorLayoutActivity;
import com.skx.tomike.tank.widget.activity.CoordinatorLayoutCaseActivity;
import com.skx.tomike.tank.widget.activity.DrawableTint2Activity;
import com.skx.tomike.tank.widget.activity.DrawableTintActivity;
import com.skx.tomike.tank.widget.activity.DrawerLayoutActivity;
import com.skx.tomike.tank.widget.activity.EditTextCursorActivity;
import com.skx.tomike.tank.widget.activity.EmojiFilterActivity;
import com.skx.tomike.tank.widget.activity.FloatingActionButtonActivity;
import com.skx.tomike.tank.widget.activity.FlowLayoutActivity;
import com.skx.tomike.tank.widget.activity.LightDarkTextActivity;
import com.skx.tomike.tank.widget.activity.Lowercase2UppercaseActivity;
import com.skx.tomike.tank.widget.activity.MatrixImageActivity;
import com.skx.tomike.tank.widget.activity.NavigationBarActivity;
import com.skx.tomike.tank.widget.activity.NestedScrollViewViewPagerActivity;
import com.skx.tomike.tank.widget.activity.PageIndicatorActivity;
import com.skx.tomike.tank.widget.activity.PopupWindowActivity;
import com.skx.tomike.tank.widget.activity.RadioGroupActivity;
import com.skx.tomike.tank.widget.activity.RecyclerAsViewPagerActivity;
import com.skx.tomike.tank.widget.activity.RecyclerStaggeredGridActivity;
import com.skx.tomike.tank.widget.activity.RecyclerViewChildCountActivity;
import com.skx.tomike.tank.widget.activity.RecyclerViewCountDownTimerActivity;
import com.skx.tomike.tank.widget.activity.RecyclerViewItemUpdateActivity;
import com.skx.tomike.tank.widget.activity.RecyclerViewScrollToPositionActivity;
import com.skx.tomike.tank.widget.activity.ScrollViewAndRecyclerViewActivity;
import com.skx.tomike.tank.widget.activity.SetTextIsSelectableTestActivity;
import com.skx.tomike.tank.widget.activity.ShadowActivity;
import com.skx.tomike.tank.widget.activity.ShapeDrawableHelperActivity;
import com.skx.tomike.tank.widget.activity.ShapeViewActivity;
import com.skx.tomike.tank.widget.activity.SnackBarActivity;
import com.skx.tomike.tank.widget.activity.SpannableStringBuilderActivity;
import com.skx.tomike.tank.widget.activity.StatusBarNavigationBarActivity;
import com.skx.tomike.tank.widget.activity.SwipeRefreshLayoutActivity;
import com.skx.tomike.tank.widget.activity.TabLayoutHelperActivity;
import com.skx.tomike.tank.widget.activity.TabLayoutIndicatorActivity;
import com.skx.tomike.tank.widget.activity.TextSwitcherActivity;
import com.skx.tomike.tank.widget.activity.TextWordSpaceActivity;
import com.skx.tomike.tank.widget.activity.VectorDrawableActivity;
import com.skx.tomike.tank.widget.activity.ViewFocusActivity;
import com.skx.tomike.tank.widget.activity.ViewPager2Activity;
import com.skx.tomike.tank.widget.activity.ViewPagerInfiniteLoopActivity;
import com.skx.tomike.tank.widget.activity.ViewPagerMultiplePageActivity;
import com.skx.tomike.tank.widget.activity.ViewPagerWrapContentActivity;
import com.skx.tomike.tank.widget.activity.WatermarkActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_BASE64;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_COROUTINE;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_GENERIC;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_GSON;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_REFLECT;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_CALL_BACK;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_COMMUNICATION;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_DAEMON;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_EXECUTORS;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_INTERRUPT;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_POOL;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_REENTRANT_LOCK;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_START;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_SYNCHRONIZED;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_URL_ENCODE;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_URL_PARSE;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_XML_PARSE;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_ACTIVITY4RESULT;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_ASYNC_TASK;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_BIG_IMAGE_LOAD;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_COUNT_DOWN_TIMER;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_DEVICE_INFO;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_GLIDE;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_IMAGE_zoom;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_KEYBOARD;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_NFC_GROUP;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_OUTER_START;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_PARCELABLE;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_PERMISSION;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_SAF;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_aop;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_handler;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_hotfix;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_lifecycle;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_notification;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_okhttp;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_photo_album;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_reboot;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_retrofit;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_room;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_rxjava;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_service;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_transparent_theme;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_zxing;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_ARRAY;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_DUEUE;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_LINKED;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_QUEUE;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_SORT;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_STACK;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_TREE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_BEZIER;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CARDVIEW;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CHECKBOX;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CIRCULAR_REVEAL;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CONSTRAINT_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_COORDINATOR;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_COORDINATOR2;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_DRAWER_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_EDITTEXT_CURSOR;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_EDITTEXT_LIGHT2DARK;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_EDITTEXT_LOWER2UPPER;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_FLOATINGACTIONBUTTON;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_FLOW_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_LAYOUT_TRANSITION;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_MATRIXIMAGE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_NSCROLLVIEW_VP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_PAGE_INDICATOR_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_PROPERTY;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_RADIOGROUP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_RECYCLER_AS_VP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_RECYCLER_CHILD_COUNT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_RECYCLER_COUNT_DOWN;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_RECYCLER_ITEM_UPDATE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_RECYCLER_SCROLL2POS;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_RECYCLER_STAGGERED_GRID;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SCROLLER;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SCROLLVIEW_RV;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SHADOW;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SHAKE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SHAPE_VIEW;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SNACKBAR;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SWIPEREFRESH_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TABLELAYOUT_HELPER;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TABLELAYOUT_INDICATOR;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TEXTSWITCH;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TEXTVIEW_SELECTABLE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TEXTVIEW_SPANNABLESTRING;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TEXTVIEW_WORD_SPACE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TINT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TWEEN;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VECTOR_DRAWABLE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER2;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER_LOOP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER_MULTIPLE_PAGE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER_WRAP_CONTENT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEW_FOCUS;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_scroll_anchor;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_scroll_change_title;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_scroll_zoom;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_scroll_zoom2;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_share_Element;

/**
 * 描述 : 目录列表model
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2017/12/29 6:08 PM
 */
public class CatalogListModel {

    private static final String GROUP_ANIMATOR = "动效类";
    private static final String GROUP_VIEW = "控件";
    private static final String GROUP_UTIL = "工具";
    private static final String GROUP_FUNCTION = "功能";
    private static final String GROUP_DESIGN_PATTERNS = "设计模式";
    private static final String GROUP_JAVA = "JAVA";
    private static final String GROUP_OTHER = "其他";
    private static final String GROUP_DATA_STRUCTURE_AND_ALGORITHM = "数据结构和算法";

    private static final LinkedHashMap<String, List<CatalogItem>> mCatalogGroupMap = new LinkedHashMap<>();

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
        // 坦克实验室 - view 篇
        mViewCatalogs.add(new CatalogItem("View 焦点", ROUTE_PATH_VIEW_FOCUS, ViewFocusActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TextView 行间距/字间距", ROUTE_PATH_TEXTVIEW_WORD_SPACE, TextWordSpaceActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TextView 富文本SpannableString", ROUTE_PATH_TEXTVIEW_SPANNABLESTRING, SpannableStringBuilderActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TextView 复制测试", ROUTE_PATH_TEXTVIEW_SELECTABLE, SetTextIsSelectableTestActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TextSwitcher 测试", ROUTE_PATH_TEXTSWITCH, TextSwitcherActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("EditText 光标", ROUTE_PATH_EDITTEXT_CURSOR, EditTextCursorActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("EditText 明暗文切换", ROUTE_PATH_EDITTEXT_LIGHT2DARK, LightDarkTextActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("EditText 小写转大写", ROUTE_PATH_EDITTEXT_LOWER2UPPER, Lowercase2UppercaseActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("自定义显示方向ImageView", ROUTE_PATH_MATRIXIMAGE, MatrixImageActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RadioGroup 单选", ROUTE_PATH_RADIOGROUP, RadioGroupActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("CheckBox 更换自定义icon", ROUTE_PATH_CHECKBOX, CheckBoxActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("自定义ShapeView", ROUTE_PATH_SHAPE_VIEW, ShapeViewActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("FlowLayout 流式布局", ROUTE_PATH_FLOW_LAYOUT, FlowLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ConstraintLayout约束布局", ROUTE_PATH_CONSTRAINT_LAYOUT, ConstraintLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("CoordinatorLayout", ROUTE_PATH_COORDINATOR, CoordinatorLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("CoordinatorLayout效果", ROUTE_PATH_COORDINATOR2, CoordinatorLayoutCaseActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("SwipeRefreshLayout 下拉刷新", ROUTE_PATH_SWIPEREFRESH_LAYOUT, SwipeRefreshLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("卡片CardView", ROUTE_PATH_CARDVIEW, CardViewActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TabLayout 指示器", ROUTE_PATH_TABLELAYOUT_INDICATOR, TabLayoutIndicatorActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TabLayout使用扩展", ROUTE_PATH_TABLELAYOUT_HELPER, NavigationBarActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("抽屉效果", ROUTE_PATH_DRAWER_LAYOUT, DrawerLayoutActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("投影", ROUTE_PATH_SHADOW, ShadowActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("FloatingActionButton", ROUTE_PATH_FLOATINGACTIONBUTTON, FloatingActionButtonActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("SnackBar", ROUTE_PATH_SNACKBAR, SnackBarActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("PopupWindow", "", PopupWindowActivity.class.getName()));

        mViewCatalogs.add(new CatalogItem("ViewPager 一屏多展示", ROUTE_PATH_VIEWPAGER_MULTIPLE_PAGE, ViewPagerMultiplePageActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ViewPager 自适应高度", ROUTE_PATH_VIEWPAGER_WRAP_CONTENT, ViewPagerWrapContentActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ViewPager 无限循环+自动轮播", ROUTE_PATH_VIEWPAGER_LOOP, ViewPagerInfiniteLoopActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ViewPager N种页签指示器", ROUTE_PATH_PAGE_INDICATOR_LAYOUT, PageIndicatorActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ViewPager2 androidx", ROUTE_PATH_VIEWPAGER2, ViewPager2Activity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RecyclerView 添加/删除item", ROUTE_PATH_RECYCLER_ITEM_UPDATE, RecyclerViewItemUpdateActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RecyclerView 滑动到指定position", ROUTE_PATH_RECYCLER_SCROLL2POS, RecyclerViewScrollToPositionActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RecyclerView 瀑布流", ROUTE_PATH_RECYCLER_STAGGERED_GRID, RecyclerStaggeredGridActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RecyclerView 仿ViewPager", ROUTE_PATH_RECYCLER_AS_VP, RecyclerAsViewPagerActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RecyclerView child count测试", ROUTE_PATH_RECYCLER_CHILD_COUNT, RecyclerViewChildCountActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("RecyclerView 倒计时功能", ROUTE_PATH_RECYCLER_COUNT_DOWN, RecyclerViewCountDownTimerActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("ScrollView + RecyclerView", ROUTE_PATH_SCROLLVIEW_RV, ScrollViewAndRecyclerViewActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("NestedScrollView+ViewPager", ROUTE_PATH_NSCROLLVIEW_VP, NestedScrollViewViewPagerActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("NestedScrollView滑动改变状态栏", ROUTE_PATH_scroll_change_title, ScrollChangeTitleActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("回弹效果-滑动缩放头图", ROUTE_PATH_scroll_zoom, ScrollZoomImage2Activity.class.getName()));
        mViewCatalogs.add(new CatalogItem("滑动缩放头图-demo2", ROUTE_PATH_scroll_zoom2, ScrollZoomImage3Activity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TabLayout+ScrollView", ROUTE_PATH_scroll_anchor, ScrollViewAnchorActivity.class.getName()));
        mViewCatalogs.add(new CatalogItem("TabLayout 工具类封装检测", ROUTE_PATH_TABLELAYOUT_HELPER, TabLayoutHelperActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_VIEW, mViewCatalogs);


        // 坦克实验室 - 动效 篇
        mAnimatorCatalogs.add(new CatalogItem("补间动画", ROUTE_PATH_TWEEN, TweenAnimationActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("属性动画", ROUTE_PATH_PROPERTY, PropertyAnimatorActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("shake动画", ROUTE_PATH_SHAKE, ShakeAnimatorActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("圆形揭示效果", ROUTE_PATH_CIRCULAR_REVEAL, CircularRevealActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("共享元素转场动画", ROUTE_PATH_share_Element, ShareElementActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("view内容移动 - scroller", ROUTE_PATH_SCROLLER, ScrollerPracticeActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("Layout添加/删除子view过渡效果", ROUTE_PATH_LAYOUT_TRANSITION, LayoutTransitionActivity.class.getName()));
        mAnimatorCatalogs.add(new CatalogItem("贝塞尔曲线", ROUTE_PATH_BEZIER, BezierAnimatorActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_ANIMATOR, mAnimatorCatalogs);


        // 加农炮实验室
        mFunctionCatalogs.add(new CatalogItem("服务(Service)", ROUTE_PATH_service, ServiceDemoActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("lifecycle", ROUTE_PATH_lifecycle, LifecycleActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("Handler", ROUTE_PATH_handler, HandlerActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("AsyncTask", ROUTE_PATH_ASYNC_TASK, AsyncTaskActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("权限管理", ROUTE_PATH_PERMISSION, PermissionIntroActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("通知", ROUTE_PATH_notification, NotificationActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("透明Activity", ROUTE_PATH_transparent_theme, TransparentThemeActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("替代StartActivityForResult", ROUTE_PATH_ACTIVITY4RESULT, WithCallbackActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("Room - 最近浏览案例", ROUTE_PATH_room, RoomTestActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("AOP 测试", ROUTE_PATH_aop, AopTestActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("okhttp", ROUTE_PATH_okhttp, OkHttpActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("retrofit", ROUTE_PATH_retrofit, RetrofitActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("RxJava", ROUTE_PATH_rxjava, RxJavaActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("Glide图片加载 - 二次封装", ROUTE_PATH_GLIDE, GlideActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("二维码", ROUTE_PATH_zxing, ZXingActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("相册", ROUTE_PATH_photo_album, PhotoAlbumsActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("图片缩放", ROUTE_PATH_IMAGE_zoom, ZoomImageActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("大图片加载", ROUTE_PATH_BIG_IMAGE_LOAD, ImageLoadActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("打开文件管理器(SAF)", ROUTE_PATH_SAF, OpenFileActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("Parcelable序列化", ROUTE_PATH_PARCELABLE, ParcelableActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("倒计时", ROUTE_PATH_COUNT_DOWN_TIMER, CountDownTimerActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("设备、APP 基础信息", ROUTE_PATH_DEVICE_INFO, DeviceInformationActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("键盘、输入法", ROUTE_PATH_KEYBOARD, KeyboardActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("热修复-微信Tinker", ROUTE_PATH_hotfix, HotfixActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("重启APP", ROUTE_PATH_reboot, RebootAppActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("打开其他App", ROUTE_PATH_OUTER_START, OutterStartActivity.class.getName()));
        mFunctionCatalogs.add(new CatalogItem("NFC功能测试", ROUTE_PATH_NFC_GROUP, NfcGroupActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_FUNCTION, mFunctionCatalogs);


        mUtilCatalogs.add(new CatalogItem("ShapeDrawable工具类", ShapeDrawableHelperActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("水印图", WatermarkActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("Double数据转换", DoubleFormatActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("URL 编码", ROUTE_PATH_URL_ENCODE, UrlEncodeActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("Url 解析", ROUTE_PATH_URL_PARSE, UrlParseActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("json 解析", ROUTE_PATH_GSON, GsonParseActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("Xml 解析", ROUTE_PATH_XML_PARSE, XmlParseActivity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("Base64 加密/解密", ROUTE_PATH_BASE64, Base64Activity.class.getName()));
        mUtilCatalogs.add(new CatalogItem("状态栏-底部导航栏高度", StatusBarNavigationBarActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_UTIL, mUtilCatalogs);


        mOtherCatalogs.add(new CatalogItem("Tint 着色1 -DrawableCompat", ROUTE_PATH_TINT, DrawableTintActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("Tint 着色2 -xml、选择器", ROUTE_PATH_TINT, DrawableTint2Activity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("VectorDrawable", ROUTE_PATH_VECTOR_DRAWABLE, VectorDrawableActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("emoji过滤", EmojiFilterActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("图像颜色处理", "", ColorMatrixActivity.class.getName()));
        mOtherCatalogs.add(new CatalogItem("图像颜色处理2", "", ColorMatrix2Activity.class.getName()));
        mCatalogGroupMap.put(GROUP_OTHER, mOtherCatalogs);


        // 作战实验室
        mDesignPatternCatalogs.add(new CatalogItem("MVVM-ViewModel", "", ViewModelActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("观察者模式", "", ObserverPatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("备忘录模式", "", MementoPatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("原型模式", "", PrototypePatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("策略模式", "", StrategyPatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("责任链模式", "", ChainOfResponsibilityPatternActivity.class.getName()));
        mDesignPatternCatalogs.add(new CatalogItem("代理模式", "", ProxyPatternActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_DESIGN_PATTERNS, mDesignPatternCatalogs);


        // 战略轰炸机实验室
        mJavaCatalogs.add(new CatalogItem("多线程", ROUTE_PATH_THREAD_START, ThreadStartActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程守护", ROUTE_PATH_THREAD_DAEMON, ThreadDaemonActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程中断", ROUTE_PATH_THREAD_INTERRUPT, ThreadInterruptActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程同步", ROUTE_PATH_THREAD_SYNCHRONIZED, ThreadSynchronizedActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程间通信 - wait/notify", ROUTE_PATH_THREAD_COMMUNICATION, ThreadCommunicationActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程返回值", ROUTE_PATH_THREAD_CALL_BACK, ThreadCallbackActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程池 - ThreadPool", ROUTE_PATH_THREAD_POOL, ThreadPoolActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("线程池 - Executors", ROUTE_PATH_THREAD_EXECUTORS, ThreadPoolExecutorsActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("ReentrantLock", ROUTE_PATH_THREAD_REENTRANT_LOCK, ReentrantLockActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("协程", ROUTE_PATH_COROUTINE, CoroutineActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("泛型理解", ROUTE_PATH_GENERIC, GenericTestActivity.class.getName()));
        mJavaCatalogs.add(new CatalogItem("反射理解", ROUTE_PATH_REFLECT, ReflectTestActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_JAVA, mJavaCatalogs);


        // 数据结构和算法
        mDataStructureCatalogs.add(new CatalogItem("数组", ROUTE_PATH_ARRAY, ArrayDemoActivity.class.getName()));
        mDataStructureCatalogs.add(new CatalogItem("栈", ROUTE_PATH_STACK, StackDemoActivity.class.getName()));
        mDataStructureCatalogs.add(new CatalogItem("队列", ROUTE_PATH_QUEUE, QueueDemoActivity.class.getName()));
        mDataStructureCatalogs.add(new CatalogItem("双端队列", ROUTE_PATH_DUEUE, DueueDemoActivity.class.getName()));
        mDataStructureCatalogs.add(new CatalogItem("链表", ROUTE_PATH_LINKED, LinkedDemoActivity.class.getName()));
        mDataStructureCatalogs.add(new CatalogItem("树", ROUTE_PATH_TREE, TreeDemoActivity.class.getName()));
        mDataStructureCatalogs.add(new CatalogItem("排序算法", ROUTE_PATH_SORT, SortDemoActivity.class.getName()));
        mCatalogGroupMap.put(GROUP_DATA_STRUCTURE_AND_ALGORITHM, mDataStructureCatalogs);
    }

    /**
     * 创建目录分组
     *
     * @return 目录分组结构
     */
    public static List<CatalogCellModel> createCatalogGroup() {
        List<CatalogCellModel> allCatalogs = new ArrayList<>();

        for (Map.Entry<String, List<CatalogItem>> entry : mCatalogGroupMap.entrySet()) {
            List<CatalogItem> tempGroup = entry.getValue();

            CatalogCellModel parentModel = new CatalogCellModel(entry.getKey(), "", "", null, null);
            allCatalogs.add(parentModel);
            for (CatalogItem item : tempGroup) {
                CatalogCellModel cellModel = new CatalogCellModel(item.getName(), item.getPath(), item.getValue(), parentModel, null);
                parentModel.addChild(cellModel);
                allCatalogs.add(cellModel);
            }
        }
        return allCatalogs;
    }

    public static LinkedHashMap<String, List<CatalogItem>> getCatalogGroupMp() {
        return mCatalogGroupMap;
    }
}
