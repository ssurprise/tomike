package com.skx.tomike.javabean;

/**
 * Created by shiguotao on 2016/8/1.
 * <p>
 * 目录条目
 */
public class CatalogItem {
    private String name;
    private String value;

    public CatalogItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        if (value == null) {
            value = "";
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
