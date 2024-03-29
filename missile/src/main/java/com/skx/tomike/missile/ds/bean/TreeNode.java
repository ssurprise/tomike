package com.skx.tomike.missile.ds.bean;

/**
 * 描述 : 树节点
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/8 4:41 下午
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
