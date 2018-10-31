package com.skx.tomike.sqlitedb;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SkxSqliteHelper extends SQLiteOpenHelper {

	private final static String DB_NAME = "skx.db";
	public final static String IMAGE_TABLE_NAME = "skx_image";
	private final static String CREATE_TBL = "create table person(_id integer primary key autoincrement, imagepath text, _display_name text, title text, width text,height text)";

	public SkxSqliteHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	public SkxSqliteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TBL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void insert(String tableName, ContentValues values) {
		// 获取SQLiteDatabase实例
		SQLiteDatabase db = getWritableDatabase();
		// 插入数据库中
		db.insert(tableName, null, values);
		db.close();
	}

	// 查询方法
	public Cursor query(String tableName) {
		SQLiteDatabase db = getReadableDatabase();
		// 获取Cursor
		return db.query(tableName, null, null, null, null, null, null, null);
	}

	// 根据唯一标识_id 来删除数据
	public void delete(String tableName, int id) {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(tableName, "_id=?", new String[] { String.valueOf(id) });
		db.close();
	}

	// 更新数据库的内容
	public void update(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = getWritableDatabase();
		db.update(tableName, values, whereClause, whereArgs);
		db.close();
	}
}
