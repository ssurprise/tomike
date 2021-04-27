package com.skx.tomike.cannon.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.skx.tomike.cannon.bean.RecentlyBrowsedBean;

import java.util.List;

/**
 * 描述 : 最近浏览 数据访问对象
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-08 17:49
 */
@Dao
public interface RecentlyBrowsedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBrowsedRecord(RecentlyBrowsedBean browsedRecord);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBrowsedRecord(RecentlyBrowsedBean browsedRecord);

    @Delete
    void deleteBrowsedRecord(RecentlyBrowsedBean browsedRecord);

    @Query("SELECT * FROM recently_browsed")
    List<RecentlyBrowsedBean> getRecentlyBrowsed();

    @Query("SELECT * FROM recently_browsed WHERE lu_city_id = (:cityId)")
    List<RecentlyBrowsedBean> getRecentlyBrowsedByCityId(String cityId);
}
