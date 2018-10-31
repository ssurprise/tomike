package com.skx.tomike.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录单元model
 *
 * @author shiguotao
 *         Created on 2017/12/29.
 */
public class CatalogCellModel {

    public String title;
    public String target;
    public CatalogCellModel parent;
    public List<CatalogCellModel> childs;


    public CatalogCellModel(String title, String target, CatalogCellModel parent, List<CatalogCellModel> child) {
        this.title = title;
        this.target = target;
        this.parent = parent;
        this.childs = child;
    }


    public boolean isParent() {
        return parent == null;
    }

    public boolean isLeafChild() {
        return childs == null || childs.isEmpty();
    }

    public void addChild(CatalogCellModel catalogCellModel) {
        if (childs == null) {
            childs = new ArrayList<>();
        }
        if (catalogCellModel != null) {
            childs.add(catalogCellModel);
        }
    }
}
