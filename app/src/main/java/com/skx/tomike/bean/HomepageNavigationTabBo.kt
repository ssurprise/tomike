package com.skx.tomike.bean;

/**
 * Created by shiguotao on 2017/2/27.
 * <p>
 * 首页标签数据信息类
 */
public class HomepageNavigationTabBo {

    /**
     * 标签名称
     */
    private String tabName;
    /**
     * 标签icon
     */
    private int tabIcon;
    /**
     * 标签目标页面
     */
    private String tabTargetPage;

    public HomepageNavigationTabBo(String tabName, int tabIcon, String tabTargetPage) {
        super();
        this.tabName = tabName;
        this.tabIcon = tabIcon;
        this.tabTargetPage = tabTargetPage;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getTabIcon() {
        return tabIcon;
    }

    public void setTabIcon(int tabIcon) {
        this.tabIcon = tabIcon;
    }

    public String getTabTargetPage() {
        return tabTargetPage;
    }

    public void setTabTargetPage(String tabTargetPage) {
        this.tabTargetPage = tabTargetPage;
    }
}
