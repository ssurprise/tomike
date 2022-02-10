package com.skx.tomike.missile.ds.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.missile.R;
import com.skx.tomike.missile.ds.bean.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_TREE;

/**
 * 描述 : 数据结构 - 树 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
@Route(path = ROUTE_PATH_TREE)
public class TreeDemoActivity extends SkxBaseActivity<BaseViewModel> {

    private TreeNode mTree = null;

    private TextView mTvValue;
    private TextView mTvSampleGraph;
    private TextView mTvPreorder;
    private TextView mTvInorder;
    private TextView mTvPostorder;
    private TextView mTvLevelOrder;
    private TextView mTvMaxDepth;

    @Override
    protected void initParams() {
        mTree = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 树").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_data_structure_tree;
    }

    @Override
    protected void initView() {
        mTvValue = findViewById(R.id.tv_tree_value);
        mTvSampleGraph = findViewById(R.id.tv_tree_sampleGraph);
        mTvPreorder = findViewById(R.id.tv_tree_preorderTraversal);
        mTvInorder = findViewById(R.id.tv_tree_inorderTraversal);
        mTvPostorder = findViewById(R.id.tv_tree_postorderTraversal);
        mTvLevelOrder = findViewById(R.id.tv_tree_levelTraversal);
        mTvMaxDepth = findViewById(R.id.tv_tree_maxDepth);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTvValue.setText("[3,9,20,null,null,15,7]");
        mTvSampleGraph.setText("   3\n" +
                "  / \\ \n" +
                " 9  20\n" +
                "     /  \\ \n" +
                "   15   7");

        List<Integer> preorder = preorderTraversal(mTree);
        mTvPreorder.setText(preorder.toString());

        List<Integer> inorder = inorderTraversal(mTree);
        mTvInorder.setText(inorder.toString());

        List<Integer> postorder = postorderTraversal(mTree);
        mTvPostorder.setText(postorder.toString());

        List<List<Integer>> levelOrder = levelOrder(mTree);
        for (int i = 0; i < levelOrder.size(); i++) {
            mTvLevelOrder.append(levelOrder.get(i).toString() + "\n");
        }

        int maxDepth = maxDepth(mTree);
        mTvMaxDepth.setText(String.valueOf(maxDepth));
    }

    /**
     * 前序遍历二叉树
     *
     * @param root 输入节点
     * @return 排序结果list
     */
    private List<Integer> preorderTraversal(TreeNode root) {
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
    private List<Integer> inorderTraversal(TreeNode root) {
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
    private List<Integer> postorderTraversal(TreeNode root) {
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

    /**
     * 二叉树的层序遍历.
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 返回其层序遍历结果：
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     *
     * @param root 目标树
     * @return 层序结果
     */
    private List<List<Integer>> levelOrder(TreeNode root) {
        // 整体思路：使用一个队列进行辅助操作。
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // 根节点入队
        queue.add(root);
        while (!queue.isEmpty()) {
            // levelNum 表示的是每层的结点数
            int levelNum = queue.size();
            // 存储每层的结点值
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                // 取出队列元素，放入集合
                TreeNode node = queue.poll();
                assert node != null;
                level.add(node.val);
                //左右子节点如果不为空就加入到队列中
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // 把每层的结点值存储在result中
            result.add(level);
        }
        return result;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

}
