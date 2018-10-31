package com.skx.tomike.util;

/**
 * Created by shiguotao on 2016/11/1.
 * <p/>
 * 订单参数配置类。
 * 请求码编号规则：
 * 计算方法：d(订)在在字母表中是4位，d(单)是4位。4*1+4*10 = 44。
 * 后两位为自定义编号。
 * {@link #requestCode_selectTenant2addTenant}：从预定 - 选择入住人页 到 添加/编辑入住人页
 * {@link #requestCode_tenantList2addTenant}：从个人中心 - 入住人列表 到 添加/编辑入住人页
 */
public class OrderConfig {
    public static final int requestCode_selectTenant2addTenant = 4401;
    public static final int requestCode_tenantList2addTenant = 4402;
}
