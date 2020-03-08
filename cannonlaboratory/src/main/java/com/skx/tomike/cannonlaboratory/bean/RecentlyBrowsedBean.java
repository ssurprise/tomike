package com.skx.tomike.cannonlaboratory.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 描述 : 最近浏览 bean。room 以此类来构建最近浏览的数据库表。勿改！！！
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-08 19:49
 */
@Entity(tableName = "recently_browsed")
public class RecentlyBrowsedBean {

    /**
     * 用户id，这里做个预留，如果用户
     */
    @ColumnInfo(name = "use_id")
    public String useID = "";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "lu_id")
    public String luID = "";

    @ColumnInfo(name = "lu_city_id")
    public String luCityID = "";

    @ColumnInfo(name = "timestamp")
    public String timestamp = "";
}
