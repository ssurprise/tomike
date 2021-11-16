package com.skx.tomike.missile.ds.bean;

/**
 * 描述 : 链表节点
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/8 4:43 下午
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
