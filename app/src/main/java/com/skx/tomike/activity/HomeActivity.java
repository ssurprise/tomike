package com.skx.tomike.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.R;
import com.skx.tomike.bean.HomepageNavigationTabBo;
import com.skx.tomike.fragment.CatalogFragment;
import com.skx.tomike.fragment.HomepageFragment;
import com.skx.tomike.fragment.PersonalFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends SkxBaseActivity<BaseViewModel> implements OnClickListener {

    private FragmentManager mFragmentManager;

    private ImageView mIvHomeTabIcon;
    private TextView mTvHomeTabText;

    private ImageView mIvCatalogTabIcon;
    private TextView mTvCatalogTabText;

    private ImageView mIvPersonalTabIcon;
    private TextView mTvPersonalTabText;

    private static final String HOME_FRAGMENT = "home_fragment";
    private static final String CATALOG_FRAGMENT = "catalog_fragment";
    private static final String PERSONAL_FRAGMENT = "personal_fragment";

    private final List<HomepageNavigationTabBo> tabList = new ArrayList<>();
    private int currentIndex = 0;
    private int lastIndex = 0;
    private Fragment from;

    @Override
    protected void initParams() {
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mIvHomeTabIcon = findViewById(R.id.homepage_navigation_homepageTab_icon);
        mTvHomeTabText = findViewById(R.id.homepage_navigation_homepageTab_text);

        mIvCatalogTabIcon = findViewById(R.id.homepage_navigation_catalogTab_icon);
        mTvCatalogTabText = findViewById(R.id.homepage_navigation_catalogTab_text);

        mIvPersonalTabIcon = findViewById(R.id.homepage_navigation_myTab_icon);
        mTvPersonalTabText = findViewById(R.id.homepage_navigation_myTab_text);

        HomepageNavigationTabBo homepageTab = new HomepageNavigationTabBo("首页", android.R.drawable.ic_dialog_map, "");
        HomepageNavigationTabBo catalogTab = new HomepageNavigationTabBo("目录", android.R.drawable.ic_menu_save, "");
        HomepageNavigationTabBo myTab = new HomepageNavigationTabBo("我的", android.R.drawable.ic_menu_manage, "");

        tabList.add(homepageTab);
        tabList.add(catalogTab);
        tabList.add(myTab);

        findViewById(R.id.homepage_navigation_homepageTab).setOnClickListener(this);
        findViewById(R.id.homepage_navigation_catalogTab).setOnClickListener(this);
        findViewById(R.id.homepage_navigation_myTab).setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderView();
    }

    private void renderView() {
        // 开启双服务保活
//        startService(new Intent(this, LocalService.class));
//        startService(new Intent(this, RemoteService.class));

        updateFragment(HOME_FRAGMENT);
        setCurrentTabColor();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homepage_navigation_homepageTab:
                updateFragment(HOME_FRAGMENT);
                // 点击后重置currentIndex为当前点击的tab
                currentIndex = 0;
                if (currentIndex != lastIndex) {// 当点击的不是同一个tab的时候，刷新tab，切换到对应的内容页面
                    setCurrentTabColor();
                    lastIndex = currentIndex;
                }
                break;

            case R.id.homepage_navigation_catalogTab:
                updateFragment(CATALOG_FRAGMENT);
                currentIndex = 1;
                if (currentIndex != lastIndex) {
                    setCurrentTabColor();
                    lastIndex = currentIndex;
                }
                break;

            case R.id.homepage_navigation_myTab:
                updateFragment(PERSONAL_FRAGMENT);
                currentIndex = 2;
                if (currentIndex != lastIndex) {
                    setCurrentTabColor();
                    lastIndex = currentIndex;
                }
                break;
            default:
                break;
        }
    }

    private void updateFragment(String name) {
        // 1. 确定对应的fragment
        Fragment to = mFragmentManager.findFragmentByTag(name);
        switch (name) {
            case HOME_FRAGMENT:
                if (to == null) {
                    to = HomepageFragment.newInstance("", "");
                }
                break;
            case CATALOG_FRAGMENT:
                if (to == null) {
                    to = new CatalogFragment();
                }
                break;
            case PERSONAL_FRAGMENT:
                if (to == null) {
                    to = new PersonalFragment();
                }
                break;
        }
        if (to == null) {
            return;
        }
        if (from == to) {
            // 说明点击的是同一个tab，无需处理
            return;
        }
        // 3. 处理显示切换
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (from != null) {
            transaction.hide(from);
        }
        // 判断是否被add过
        if (!to.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.add(R.id.homepage_content_container, to, name).commit();
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.show(to).commit();
        }
        // 4. 更新from
        from = to;
    }

    /**
     * 设置页签的颜色
     */
    private void setCurrentTabColor() {
        for (int i = 0, j = tabList.size(); i < j; i++) {
            if (i == currentIndex) {
                if (i == 0) {
                    mTvHomeTabText.setTextColor(Color.parseColor("#ff4081"));
                } else if (i == 1) {
                    mTvCatalogTabText.setTextColor(Color.parseColor("#ff4081"));
                } else if (i == 2) {
                    mTvPersonalTabText.setTextColor(Color.parseColor("#ff4081"));
                }
            } else if (i == lastIndex) {
                if (i == 0) {
                    mTvHomeTabText.setTextColor(Color.parseColor("#323232"));
                } else if (i == 1) {
                    mTvCatalogTabText.setTextColor(Color.parseColor("#323232"));
                } else if (i == 2) {
                    mTvPersonalTabText.setTextColor(Color.parseColor("#323232"));
                }
            }
        }
    }

    /**
     * 设置Fragment专场动画
     * <p>
     * 自定义转场动画是通过setCustomAnimations()方法，因为Fragment添加时可以指定加入到Back Stack中，
     * 所以转场动画有添加、移除、从Back stack中pop出来，还有进入四种情况。
     * 注意setCustomAnimations()方法必须在add、remove、replace调用之前被设置，否则不起作用。
     *
     * @param fragmentTransaction fragment事务
     */
    public void setFragmentAnimation(FragmentTransaction fragmentTransaction) {
//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        if (lastIndex < currentIndex) {
//            fragmentTransaction.setCustomAnimations(R.anim.enter_right_left, R.anim.exit_right_left);
//        } else if (lastIndex > currentIndex) {
//            fragmentTransaction.setCustomAnimations(R.anim.enter_left_right, R.anim.exit_left_right);
//        }
    }
}
