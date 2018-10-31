package com.skx.tomike.interf;

public interface IDataRefresh {
	/**
	 * 初始化数据
	 */
	void initializeData();
	/**
	 * 初始化数据
	 */
	void initializeView();

	/**
	 * 刷新view
	 */
	void refreshView();

	/**
	 * 安装监听
	 */
	void installListener();
}
