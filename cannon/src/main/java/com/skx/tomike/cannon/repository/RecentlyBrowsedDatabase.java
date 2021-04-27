package com.skx.tomike.cannon.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.skx.tomike.cannon.bean.RecentlyBrowsedBean;

/**
 * 描述 : 近期浏览数据库
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-08 22:37
 */
@Database(entities = {RecentlyBrowsedBean.class}, version = 1, exportSchema = false)
public abstract class RecentlyBrowsedDatabase extends RoomDatabase {

    public abstract RecentlyBrowsedDao recentlyBrowsedDao();


    private static RecentlyBrowsedDatabase instance = null;

    public static RecentlyBrowsedDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (RecentlyBrowsedDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, RecentlyBrowsedDatabase.class, "recently_browsed_db")
                            .build();
                }
            }
        }
        return instance;
    }
}
