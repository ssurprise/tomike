package com.skx.tomike.catalog;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skx.common.base.BaseRepository;
import com.skx.common.base.BaseViewModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_BASE64;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_COROUTINE;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_EMOJI_FILTER;
import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_FLOAT_CALCULATE;
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
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_AOP;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_APP_USAGE_STATS;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_ASYNC_TASK;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_BIG_IMAGE_LOAD;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_BROADCAST;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_COUNT_DOWN_TIMER;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_DEVICE_INFO;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_DIALOG;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_FILE_TREE_VISITOR;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_FLOW;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_FRAGMENT;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_FULL_SCREEN;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_GLIDE;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_H5;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_HANDLER;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_HOTFIX;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_IMAGE_zoom;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_KEYBOARD;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_KEYBOARD_2;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_LIFECYCLE;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_LIVEDATA;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_MUSIC_PLAYER;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_NFC_GROUP;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_NOTIFICATION;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_OKHTTP;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_OUTER_START;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_PACKAGE_LIST;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_PARCELABLE;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_PERMISSION;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_PHOTO_ALBUM;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_POPWINDOW;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_REBOOT;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_RETROFIT;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_ROOM;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_RXJAVA;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_SAF;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_SERVICE;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_TRANSLUCENT_THEME;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_TRANSPARENT_THEME;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_VIEW_MODEL;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_ZXING;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_ARRAY;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_CHAINOFRESPONSIBILITY;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_DUEUE;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_LINKED;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_MEMENTO;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_OBSERVER;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_PROTOTYPE;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_PROXY;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_QUEUE;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_SORT;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_STACK;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_STRATEGY;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_TREE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_BEZIER;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CARDVIEW;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CHECKBOX;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CIRCULAR_REVEAL;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_COLORMATRIX;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CONSTRAINT_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_COORDINATOR;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_COORDINATOR2;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_DRAWER_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_DYNAMIC_MSG_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_EDITTEXT_CURSOR;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_EDITTEXT_LIGHT2DARK;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_EDITTEXT_LOWER2UPPER;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_EDITTEXT_SINGLE_MULTI_LINES;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_FLOATINGACTIONBUTTON;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_FLOW_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_GRADIENT_DRAWABLE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_HSL;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_LAYOUT_TRANSITION;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_LOTTIE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_MATRIXIMAGE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_NSCROLLVIEW_VP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_PAGE_INDICATOR_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_PAINT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_PROGRESS_BAR;
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
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SCROLL_ANCHOR;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SCROLL_CHANGE_TITLE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SCROLL_ZOOM;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SCROLL_ZOOM2;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SHADOW;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SHAKE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SHAPE_VIEW;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SNACKBAR;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_STATUS_BAR_HEIGHT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SWIPEREFRESH_LAYOUT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TABLELAYOUT_HELPER;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TABLELAYOUT_INDICATOR;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TABLELAYOUT_VIEWPAGER2;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TEXTSWITCH;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TEXTVIEW_MARQUEE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TEXTVIEW_SELECTABLE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TEXTVIEW_SPANNABLESTRING;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TEXTVIEW_WORD_SPACE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TINT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TWEEN;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VECTOR_DRAWABLE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER2;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER_LOOP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER_MULTIPLE_PAGE;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER_PAGE_TRANSFORMER;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER_WRAP_CONTENT;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEW_FOCUS;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_WATER_MARK;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_share_Element;

/**
 * 描述 : 目录列表model
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2017/12/29 6:08 PM
 */
public class CatalogViewModel extends BaseViewModel<BaseRepository<?>> {

    private static final String TAG = "CatalogListModel";

    private static final String GROUP_HISTORY = "最近使用";
    private static final String GROUP_VIEW = "控件";
    private static final String GROUP_GRAPHICS = "图形";
    private static final String GROUP_ANIMATOR = "动效类";
    private static final String GROUP_UTIL = "工具";
    private static final String GROUP_FUNCTION = "功能";
    private static final String GROUP_DESIGN_PATTERNS = "设计模式";
    private static final String GROUP_JAVA = "JAVA";
    private static final String GROUP_DATA_STRUCTURE_AND_ALGORITHM = "数据结构和算法";

    private static final LinkedHashMap<String, List<CatalogItem>> mCatalogGroupMap = new LinkedHashMap<>();

    /**
     * 最近访问记录
     */
    private static final List<CatalogItem> mRecentHistory = new LinkedList<>();
    /**
     * view 类分组
     */
    private static final List<CatalogItem> mViewCatalogs = new ArrayList<>();
    /**
     * 图形类分组
     */
    private static final List<CatalogItem> mGraphicsCatalogs = new ArrayList<>();
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
     * 数据结构和算法
     */
    private static final List<CatalogItem> mDataStructureCatalogs = new ArrayList<>();

    static {
        // 最近访问记录
        mCatalogGroupMap.put(GROUP_HISTORY, mRecentHistory);

        // 坦克实验室 - view 篇
        mViewCatalogs.add(new CatalogItem("View 焦点", ROUTE_PATH_VIEW_FOCUS));
        mViewCatalogs.add(new CatalogItem("TextView 行间距/字间距", ROUTE_PATH_TEXTVIEW_WORD_SPACE));
        mViewCatalogs.add(new CatalogItem("TextView 跑马灯效果", ROUTE_PATH_TEXTVIEW_MARQUEE));
        mViewCatalogs.add(new CatalogItem("TextView 富文本SpannableString", ROUTE_PATH_TEXTVIEW_SPANNABLESTRING));
        mViewCatalogs.add(new CatalogItem("TextView 复制测试", ROUTE_PATH_TEXTVIEW_SELECTABLE));
        mViewCatalogs.add(new CatalogItem("TextSwitcher 测试", ROUTE_PATH_TEXTSWITCH));
        mViewCatalogs.add(new CatalogItem("EditText 光标", ROUTE_PATH_EDITTEXT_CURSOR));
        mViewCatalogs.add(new CatalogItem("EditText 明暗文切换", ROUTE_PATH_EDITTEXT_LIGHT2DARK));
        mViewCatalogs.add(new CatalogItem("EditText 小写转大写", ROUTE_PATH_EDITTEXT_LOWER2UPPER));
        mViewCatalogs.add(new CatalogItem("EditText 单行/多行显示", ROUTE_PATH_EDITTEXT_SINGLE_MULTI_LINES));
        mViewCatalogs.add(new CatalogItem("自定义显示方向ImageView", ROUTE_PATH_MATRIXIMAGE));
        mViewCatalogs.add(new CatalogItem("RadioGroup 单选", ROUTE_PATH_RADIOGROUP));
        mViewCatalogs.add(new CatalogItem("CheckBox 更换自定义icon", ROUTE_PATH_CHECKBOX));
        mViewCatalogs.add(new CatalogItem("ProgressBar", ROUTE_PATH_PROGRESS_BAR));
        mViewCatalogs.add(new CatalogItem("自定义ShapeView", ROUTE_PATH_SHAPE_VIEW));
        mViewCatalogs.add(new CatalogItem("FlowLayout 流式布局", ROUTE_PATH_FLOW_LAYOUT));
        mViewCatalogs.add(new CatalogItem("ConstraintLayout约束布局", ROUTE_PATH_CONSTRAINT_LAYOUT));
        mViewCatalogs.add(new CatalogItem("CoordinatorLayout", ROUTE_PATH_COORDINATOR));
        mViewCatalogs.add(new CatalogItem("CoordinatorLayout效果", ROUTE_PATH_COORDINATOR2));
        mViewCatalogs.add(new CatalogItem("SwipeRefreshLayout 下拉刷新", ROUTE_PATH_SWIPEREFRESH_LAYOUT));
        mViewCatalogs.add(new CatalogItem("卡片CardView", ROUTE_PATH_CARDVIEW));
        mViewCatalogs.add(new CatalogItem("抽屉效果", ROUTE_PATH_DRAWER_LAYOUT));
        mViewCatalogs.add(new CatalogItem("投影", ROUTE_PATH_SHADOW));
        mViewCatalogs.add(new CatalogItem("FloatingActionButton", ROUTE_PATH_FLOATINGACTIONBUTTON));
        mViewCatalogs.add(new CatalogItem("SnackBar", ROUTE_PATH_SNACKBAR));

        mViewCatalogs.add(new CatalogItem("ViewPager 页面切换效果", ROUTE_PATH_VIEWPAGER_PAGE_TRANSFORMER));
        mViewCatalogs.add(new CatalogItem("ViewPager 一屏多展示", ROUTE_PATH_VIEWPAGER_MULTIPLE_PAGE));
        mViewCatalogs.add(new CatalogItem("ViewPager 自适应高度", ROUTE_PATH_VIEWPAGER_WRAP_CONTENT));
        mViewCatalogs.add(new CatalogItem("ViewPager 无限循环+自动轮播", ROUTE_PATH_VIEWPAGER_LOOP));
        mViewCatalogs.add(new CatalogItem("ViewPager N种页签指示器", ROUTE_PATH_PAGE_INDICATOR_LAYOUT));
        mViewCatalogs.add(new CatalogItem("ViewPager2 androidx", ROUTE_PATH_VIEWPAGER2));
        mViewCatalogs.add(new CatalogItem("RecyclerView 添加/删除item", ROUTE_PATH_RECYCLER_ITEM_UPDATE));
        mViewCatalogs.add(new CatalogItem("RecyclerView 滑动到指定position", ROUTE_PATH_RECYCLER_SCROLL2POS));
        mViewCatalogs.add(new CatalogItem("RecyclerView 瀑布流", ROUTE_PATH_RECYCLER_STAGGERED_GRID));
        mViewCatalogs.add(new CatalogItem("RecyclerView 仿ViewPager", ROUTE_PATH_RECYCLER_AS_VP));
        mViewCatalogs.add(new CatalogItem("RecyclerView child count", ROUTE_PATH_RECYCLER_CHILD_COUNT));
        mViewCatalogs.add(new CatalogItem("RecyclerView 倒计时功能", ROUTE_PATH_RECYCLER_COUNT_DOWN));
        mViewCatalogs.add(new CatalogItem("ScrollView+RecyclerView", ROUTE_PATH_SCROLLVIEW_RV));
        mViewCatalogs.add(new CatalogItem("NestedScrollView+ViewPager", ROUTE_PATH_NSCROLLVIEW_VP));
        mViewCatalogs.add(new CatalogItem("NestedScrollView 滑动改变状态栏", ROUTE_PATH_SCROLL_CHANGE_TITLE));
        mViewCatalogs.add(new CatalogItem("带回弹效果的ScrollView", ROUTE_PATH_SCROLL_ZOOM));
        mViewCatalogs.add(new CatalogItem("滑动缩放头图-demo2", ROUTE_PATH_SCROLL_ZOOM2));
        mViewCatalogs.add(new CatalogItem("TabLayout 指示器", ROUTE_PATH_TABLELAYOUT_INDICATOR));
        mViewCatalogs.add(new CatalogItem("TabLayout 使用扩展", ROUTE_PATH_TABLELAYOUT_HELPER));
        mViewCatalogs.add(new CatalogItem("TabLayout+ScrollView", ROUTE_PATH_SCROLL_ANCHOR));
        mViewCatalogs.add(new CatalogItem("TabLayout+ViewPager2", ROUTE_PATH_TABLELAYOUT_VIEWPAGER2));
        mViewCatalogs.add(new CatalogItem("TabLayout 工具类封装检测", ROUTE_PATH_TABLELAYOUT_HELPER));
        mViewCatalogs.add(new CatalogItem("获取状态栏/导航栏高度", ROUTE_PATH_STATUS_BAR_HEIGHT));
        mViewCatalogs.add(new CatalogItem("动态消息展示-自定义Layout", ROUTE_PATH_DYNAMIC_MSG_LAYOUT));
        mCatalogGroupMap.put(GROUP_VIEW, mViewCatalogs);


        // 坦克实验室 - 动效 篇
        mAnimatorCatalogs.add(new CatalogItem("补间动画", ROUTE_PATH_TWEEN));
        mAnimatorCatalogs.add(new CatalogItem("属性动画", ROUTE_PATH_PROPERTY));
        mAnimatorCatalogs.add(new CatalogItem("shake动画", ROUTE_PATH_SHAKE));
        mAnimatorCatalogs.add(new CatalogItem("圆形揭示效果", ROUTE_PATH_CIRCULAR_REVEAL));
        mAnimatorCatalogs.add(new CatalogItem("Scroller 动效", ROUTE_PATH_SCROLLER));
        mAnimatorCatalogs.add(new CatalogItem("Layout添加/删除子view过渡效果", ROUTE_PATH_LAYOUT_TRANSITION));
        mAnimatorCatalogs.add(new CatalogItem("贝塞尔曲线", ROUTE_PATH_BEZIER));
        mAnimatorCatalogs.add(new CatalogItem("共享元素转场动画", ROUTE_PATH_share_Element));
        mAnimatorCatalogs.add(new CatalogItem("Airbnb Lottie库", ROUTE_PATH_LOTTIE));
        mCatalogGroupMap.put(GROUP_ANIMATOR, mAnimatorCatalogs);


        // 图形类
        mGraphicsCatalogs.add(new CatalogItem("paint", ROUTE_PATH_PAINT));
        mGraphicsCatalogs.add(new CatalogItem("Tint 着色", ROUTE_PATH_TINT));
        mGraphicsCatalogs.add(new CatalogItem("矢量图 VectorDrawable", ROUTE_PATH_VECTOR_DRAWABLE));
        mGraphicsCatalogs.add(new CatalogItem("渐变 GradientDrawable", ROUTE_PATH_GRADIENT_DRAWABLE));
        mGraphicsCatalogs.add(new CatalogItem("图像处理-HSL", ROUTE_PATH_HSL));
        mGraphicsCatalogs.add(new CatalogItem("ColorMatrix-HSL", ROUTE_PATH_COLORMATRIX));
        mGraphicsCatalogs.add(new CatalogItem("给图片添加水印", ROUTE_PATH_WATER_MARK));
        mCatalogGroupMap.put(GROUP_GRAPHICS, mGraphicsCatalogs);


        // 加农炮实验室
        mFunctionCatalogs.add(new CatalogItem("Dialog", ROUTE_PATH_DIALOG));
        mFunctionCatalogs.add(new CatalogItem("PopupWindow", ROUTE_PATH_POPWINDOW));
        mFunctionCatalogs.add(new CatalogItem("全屏页", ROUTE_PATH_FULL_SCREEN));
        mFunctionCatalogs.add(new CatalogItem("透明Activity", ROUTE_PATH_TRANSPARENT_THEME));
        mFunctionCatalogs.add(new CatalogItem("半透明Activity - 新手引导", ROUTE_PATH_TRANSLUCENT_THEME));
        mFunctionCatalogs.add(new CatalogItem("服务(Service)", ROUTE_PATH_SERVICE));
        mFunctionCatalogs.add(new CatalogItem("广播(broadcast)", ROUTE_PATH_BROADCAST));
        mFunctionCatalogs.add(new CatalogItem("fragment 练习", ROUTE_PATH_FRAGMENT));
        mFunctionCatalogs.add(new CatalogItem("lifecycle", ROUTE_PATH_LIFECYCLE));
        mFunctionCatalogs.add(new CatalogItem("livedata", ROUTE_PATH_LIVEDATA));
        mFunctionCatalogs.add(new CatalogItem("ViewModel", ROUTE_PATH_VIEW_MODEL));
        mFunctionCatalogs.add(new CatalogItem("Handler", ROUTE_PATH_HANDLER));
        mFunctionCatalogs.add(new CatalogItem("AsyncTask", ROUTE_PATH_ASYNC_TASK));
        mFunctionCatalogs.add(new CatalogItem("权限管理", ROUTE_PATH_PERMISSION));
        mFunctionCatalogs.add(new CatalogItem("通知", ROUTE_PATH_NOTIFICATION));
        mFunctionCatalogs.add(new CatalogItem("替代StartActivityForResult", ROUTE_PATH_ACTIVITY4RESULT));
        mFunctionCatalogs.add(new CatalogItem("Room - 最近浏览案例", ROUTE_PATH_ROOM));
        mFunctionCatalogs.add(new CatalogItem("AOP 测试", ROUTE_PATH_AOP));
        mFunctionCatalogs.add(new CatalogItem("okhttp", ROUTE_PATH_OKHTTP));
        mFunctionCatalogs.add(new CatalogItem("retrofit", ROUTE_PATH_RETROFIT));
        mFunctionCatalogs.add(new CatalogItem("RxJava", ROUTE_PATH_RXJAVA));
        mFunctionCatalogs.add(new CatalogItem("Kotlin-flow", ROUTE_PATH_FLOW));
        mFunctionCatalogs.add(new CatalogItem("Glide图片加载 - 二次封装", ROUTE_PATH_GLIDE));
        mFunctionCatalogs.add(new CatalogItem("二维码", ROUTE_PATH_ZXING));
        mFunctionCatalogs.add(new CatalogItem("相册", ROUTE_PATH_PHOTO_ALBUM));
        mFunctionCatalogs.add(new CatalogItem("图片缩放", ROUTE_PATH_IMAGE_zoom));
        mFunctionCatalogs.add(new CatalogItem("大图片加载", ROUTE_PATH_BIG_IMAGE_LOAD));
        mFunctionCatalogs.add(new CatalogItem("图片添加水印", ROUTE_PATH_WATER_MARK));
        mFunctionCatalogs.add(new CatalogItem("打开文件管理器(SAF)", ROUTE_PATH_SAF));
        mFunctionCatalogs.add(new CatalogItem("Parcelable序列化", ROUTE_PATH_PARCELABLE));
        mFunctionCatalogs.add(new CatalogItem("倒计时", ROUTE_PATH_COUNT_DOWN_TIMER));
        mFunctionCatalogs.add(new CatalogItem("设备、APP 基础信息", ROUTE_PATH_DEVICE_INFO));
        mFunctionCatalogs.add(new CatalogItem("键盘应用-1", ROUTE_PATH_KEYBOARD));
        mFunctionCatalogs.add(new CatalogItem("键盘应用-2", ROUTE_PATH_KEYBOARD_2));
        mFunctionCatalogs.add(new CatalogItem("热修复-微信Tinker", ROUTE_PATH_HOTFIX));
        mFunctionCatalogs.add(new CatalogItem("重启APP", ROUTE_PATH_REBOOT));
        mFunctionCatalogs.add(new CatalogItem("打开其他App", ROUTE_PATH_OUTER_START));
        mFunctionCatalogs.add(new CatalogItem("NFC功能测试", ROUTE_PATH_NFC_GROUP));
        mFunctionCatalogs.add(new CatalogItem("音乐播放器", ROUTE_PATH_MUSIC_PLAYER));
        mFunctionCatalogs.add(new CatalogItem("H5", ROUTE_PATH_H5));
        mFunctionCatalogs.add(new CatalogItem("文件扫描", ROUTE_PATH_FILE_TREE_VISITOR));
        mFunctionCatalogs.add(new CatalogItem("APP安装列表", ROUTE_PATH_PACKAGE_LIST));
        mFunctionCatalogs.add(new CatalogItem("APP使用信息统计", ROUTE_PATH_APP_USAGE_STATS));
        mCatalogGroupMap.put(GROUP_FUNCTION, mFunctionCatalogs);


        mUtilCatalogs.add(new CatalogItem("精度计算-浮点数", ROUTE_PATH_FLOAT_CALCULATE));
        mUtilCatalogs.add(new CatalogItem("Base64 加密/解密", ROUTE_PATH_BASE64));
        mUtilCatalogs.add(new CatalogItem("Url 编码", ROUTE_PATH_URL_ENCODE));
        mUtilCatalogs.add(new CatalogItem("Url 解析", ROUTE_PATH_URL_PARSE));
        mUtilCatalogs.add(new CatalogItem("json 解析", ROUTE_PATH_GSON));
        mUtilCatalogs.add(new CatalogItem("Xml 解析", ROUTE_PATH_XML_PARSE));
        mUtilCatalogs.add(new CatalogItem("emoji过滤", ROUTE_PATH_EMOJI_FILTER));
        mCatalogGroupMap.put(GROUP_UTIL, mUtilCatalogs);


        // 作战实验室
        mDesignPatternCatalogs.add(new CatalogItem("观察者模式", ROUTE_PATH_OBSERVER));
        mDesignPatternCatalogs.add(new CatalogItem("备忘录模式", ROUTE_PATH_MEMENTO));
        mDesignPatternCatalogs.add(new CatalogItem("原型模式", ROUTE_PATH_PROTOTYPE));
        mDesignPatternCatalogs.add(new CatalogItem("策略模式", ROUTE_PATH_STRATEGY));
        mDesignPatternCatalogs.add(new CatalogItem("责任链模式", ROUTE_PATH_CHAINOFRESPONSIBILITY));
        mDesignPatternCatalogs.add(new CatalogItem("代理模式", ROUTE_PATH_PROXY));
        mCatalogGroupMap.put(GROUP_DESIGN_PATTERNS, mDesignPatternCatalogs);


        // 战略轰炸机实验室
        mJavaCatalogs.add(new CatalogItem("多线程", ROUTE_PATH_THREAD_START));
        mJavaCatalogs.add(new CatalogItem("线程守护", ROUTE_PATH_THREAD_DAEMON));
        mJavaCatalogs.add(new CatalogItem("线程中断", ROUTE_PATH_THREAD_INTERRUPT));
        mJavaCatalogs.add(new CatalogItem("线程同步", ROUTE_PATH_THREAD_SYNCHRONIZED));
        mJavaCatalogs.add(new CatalogItem("线程间通信 - wait/notify", ROUTE_PATH_THREAD_COMMUNICATION));
        mJavaCatalogs.add(new CatalogItem("线程返回值", ROUTE_PATH_THREAD_CALL_BACK));
        mJavaCatalogs.add(new CatalogItem("线程池 - ThreadPool", ROUTE_PATH_THREAD_POOL));
        mJavaCatalogs.add(new CatalogItem("线程池 - Executors", ROUTE_PATH_THREAD_EXECUTORS));
        mJavaCatalogs.add(new CatalogItem("ReentrantLock", ROUTE_PATH_THREAD_REENTRANT_LOCK));
        mJavaCatalogs.add(new CatalogItem("协程", ROUTE_PATH_COROUTINE));
        mJavaCatalogs.add(new CatalogItem("泛型理解", ROUTE_PATH_GENERIC));
        mJavaCatalogs.add(new CatalogItem("反射理解", ROUTE_PATH_REFLECT));
        mCatalogGroupMap.put(GROUP_JAVA, mJavaCatalogs);


        // 数据结构和算法
        mDataStructureCatalogs.add(new CatalogItem("数组", ROUTE_PATH_ARRAY));
        mDataStructureCatalogs.add(new CatalogItem("栈", ROUTE_PATH_STACK));
        mDataStructureCatalogs.add(new CatalogItem("队列", ROUTE_PATH_QUEUE));
        mDataStructureCatalogs.add(new CatalogItem("双端队列", ROUTE_PATH_DUEUE));
        mDataStructureCatalogs.add(new CatalogItem("链表", ROUTE_PATH_LINKED));
        mDataStructureCatalogs.add(new CatalogItem("树", ROUTE_PATH_TREE));
        mDataStructureCatalogs.add(new CatalogItem("排序算法", ROUTE_PATH_SORT));
        mCatalogGroupMap.put(GROUP_DATA_STRUCTURE_AND_ALGORITHM, mDataStructureCatalogs);
    }

    private final MutableLiveData<List<CatalogCellModel>> mCatalogGroupLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<CatalogItem>> mCatalogItemLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<CatalogItem>> mRecentItemLiveData = new MutableLiveData<>();

    public CatalogViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<CatalogCellModel>> getCatalogGroupLiveData() {
        return mCatalogGroupLiveData;
    }


    public LiveData<List<CatalogItem>> getCatalogItemLiveData() {
        return mCatalogItemLiveData;
    }


    public LiveData<List<CatalogItem>> getRecentItemLiveData() {
        return mRecentItemLiveData;
    }

    public boolean isRecentHistoryGroup(String key) {
        return GROUP_HISTORY.equals(key);
    }


    /**
     * 创建目录分组
     *
     * @return 目录分组结构
     */
    public void createCatalogGroup() {
        List<CatalogCellModel> allCatalogs = new ArrayList<>();
        for (Map.Entry<String, List<CatalogItem>> entry : mCatalogGroupMap.entrySet()) {
            CatalogCellModel parentModel = new CatalogCellModel(entry.getKey(),
                    "", "", null);
            allCatalogs.add(parentModel);
        }
        mCatalogGroupLiveData.postValue(allCatalogs);
    }

    /**
     * 获取目录
     *
     * @param key
     * @return 目录分组结构
     */
    public void fetchCatalogByKey(String key) {
        List<CatalogItem> catalogItems = mCatalogGroupMap.get(key);
        Log.d(TAG, "fetchCatalogByKey, key=" + key + " size=" + (catalogItems == null ? 0 : catalogItems.size()));
        mCatalogItemLiveData.postValue(catalogItems);
    }

    public void add2RecentHistory(CatalogItem catalogItem) {
        if (catalogItem == null) return;

        int index = -1;
        for (int i = 0, j = mRecentHistory.size(); i < j; i++) {
            CatalogItem item = mRecentHistory.get(i);
            if (catalogItem.getPath().equals(item.getPath())) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            // 当前已经排在首位，无需处理
            Log.d(TAG, "add2RecentHistory, 当前已经排在首位，无需处理");
            return;
        }
        if (index != -1) {
            Log.d(TAG, "add2RecentHistory, 之前有浏览记录，name=" + catalogItem.getName() + " index=" + index);
            // 说明之前有此记录，但是没有排在首位，需要重新插入到队首
            mRecentHistory.remove(index);
            mRecentHistory.add(0, catalogItem);

            mRecentItemLiveData.postValue(mRecentHistory);
            return;
        }

        Log.d(TAG, "add2RecentHistory, 新插入浏览记录 name=" + catalogItem.getName());
        mRecentHistory.add(0, catalogItem);
        if (mRecentHistory.size() > 10) {
            Log.d(TAG, "add2RecentHistory, 浏览记录超过10条，移除多余的记录");
            mRecentHistory.remove(10);
        }
        mRecentItemLiveData.postValue(mRecentHistory);
    }
}
