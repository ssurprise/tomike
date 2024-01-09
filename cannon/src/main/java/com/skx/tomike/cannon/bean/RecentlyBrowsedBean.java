package com.skx.tomike.cannon.bean;

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

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ac_id")
    public String acId = "";

    @ColumnInfo(name = "path")
    public String path = "";

    @ColumnInfo(name = "name")
    public String name = "";

    @ColumnInfo(name = "group")
    public String group = "";

    @ColumnInfo(name = "timestamp")
    public String timestamp = "";
}
