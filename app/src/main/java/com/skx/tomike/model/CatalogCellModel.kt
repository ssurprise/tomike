package com.skx.tomike.model

import java.util.*

/**
 * 目录单元model
 *
 * @author shiguotao
 * Created on 2017/12/29.
 */
class CatalogCellModel(
    val title: String,
    val path: String? = "",
    val target: String,
    var parent: CatalogCellModel?,
    var childs: MutableList<CatalogCellModel>?
) {


    val isParent: Boolean
        get() = parent == null

    val isLeafChild: Boolean
        get() = childs == null || childs!!.isEmpty()

    fun addChild(catalogCellModel: CatalogCellModel?) {
        catalogCellModel?.run {
            if (childs == null) {
                childs = ArrayList()
            }
            childs?.add(catalogCellModel)
        }
    }
}
