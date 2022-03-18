package com.skx.tomike.model

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
) {

    val isParent: Boolean
        get() = parent == null
}
