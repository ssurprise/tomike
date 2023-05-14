package com.skx.tomike.main

/**
 * Created by shiguotao on 2017/2/27.
 *
 *
 * 首页标签数据信息类
 */
data class HomepageNavigationTabBo(
        /**
         * 标签名称
         */
        var tabName: String,
        /**
         * 标签icon
         */
        var tabIcon: Int,
        /**
         * 标签目标页面
         */
        var tabTargetPage: String)