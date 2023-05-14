package com.skx.tomike.catalog

/**
 * Created by shiguotao on 2016/8/1.
 *
 * 目录条目
 */
class CatalogItem constructor(
        // 条目名称
        val name: String,
        // route 路径
        val path: String = "",
        // 值，取目标activity 的class，没啥特殊作用，就是为了方便点击查看的，实际项目中不可。
        val value: String?
) {
    constructor (name: String, path: String) : this(name, path, "") {

    }
}