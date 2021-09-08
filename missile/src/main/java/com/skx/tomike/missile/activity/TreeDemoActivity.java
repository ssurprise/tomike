package com.skx.tomike.missile.activity;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.missile.R;
import com.skx.tomike.missile.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : 数据结构 - 树 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
public class TreeDemoActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 树").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_structure_array;
    }

    @Override
    protected void initView() {

    }

    /**
     * 前序遍历二叉树
     *
     * @param root 输入节点
     * @return 排序结果list
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(result, root);
        return result;
    }

    /**
     * 前序遍历二叉树 - 递归实现
     * 复杂度分析：
     * 时间复杂度：O(n)，其中 nn 是二叉树的节点数。每一个节点恰好被遍历一次。
     * 空间复杂度：O(n)，为递归过程中栈的开销，平均情况下为O(logn)，最坏情况下树呈现链状，为O(n)。
     *
     * @param result 排序结果list
     * @param root   目标节点
     */
    private void traverse(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        traverse(result, root.left);
        traverse(result, root.right);
    }

    /**
     * 中序遍历二叉树
     *
     * @param root 目标节点
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(result, root);
        return result;
    }

    /**
     * 中序遍历二叉树 - 递归实现
     * 复杂度分析：
     * 时间复杂度：O(n)，其中 nn 是二叉树的节点数。每一个节点恰好被遍历一次。
     * 空间复杂度：O(n)，为递归过程中栈的开销，平均情况下为O(logn)，最坏情况下树呈现链状，为O(n)。
     *
     * @param root 目标节点
     */
    private void inorder(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(result, root.left);
        result.add(root.val);
        inorder(result, root.right);
    }

    /**
     * 后序遍历二叉树
     *
     * @param root 目标节点
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(result, root);
        return result;
    }

    /**
     * 后序遍历二叉树 - 递归实现
     * 复杂度分析：
     * 时间复杂度：O(n)，其中 nn 是二叉树的节点数。每一个节点恰好被遍历一次。
     * 空间复杂度：O(n)，为递归过程中栈的开销，平均情况下为O(logn)，最坏情况下树呈现链状，为O(n)。
     *
     * @param root 目标节点
     */
    private void postorder(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(result, root.left);
        postorder(result, root.right);
        result.add(root.val);
    }
//
//    public List<List<Integer>> levelOrder(TreeNode root) {
//
//    }

}
