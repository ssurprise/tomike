package com.skx.tomike.tacticallaboratory.activity;

import android.widget.TextView;

import com.skx.tomike.tacticallaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * 描述 : 数据结构 - 链表 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
public class LinkedDemoActivity extends SkxBaseActivity<BaseViewModel> {

    private TextView mTvExample;
    private TextView mTvHasRing;
    private TextView mTvRingLength;
    private TextView mTvRingEnter;

    private Node mLink = new Node(0);


    private static final String QUESTION = "1.如何判断一个单向链表是否有环?\n2.环的长度如何计算?\n3.如何找到环的入口?";

    @Override
    protected void initParams() {
        mLink.next = new Node(1);
        mLink.next.next = new Node(2);
        mLink.next.next.next = new Node(3);
        mLink.next.next.next.next = new Node(4);
        mLink.next.next.next.next.next = new Node(5);
        mLink.next.next.next.next.next.next = new Node(6);
//        mLink.next.next.next.next.next.next.next = null;
        mLink.next.next.next.next.next.next.next = mLink.next.next;
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 链表").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_structure_linked;
    }

    @Override
    protected void initView() {
        TextView mTvQuestion = findViewById(R.id.tv_linked_question);
        mTvQuestion.setText(QUESTION);
        mTvExample = findViewById(R.id.tv_linked_example);
        mTvHasRing = findViewById(R.id.tv_linked_answer_hasRing);
        mTvRingLength = findViewById(R.id.tv_linked_answer_ringLength);
        mTvRingEnter = findViewById(R.id.tv_linked_answer_ringEnterPoint);

        createExampleText();
        checkLinkHasRing(mLink);
        calculateRingLength(mLink);
    }

    private void createExampleText() {
        Set<Node> ss = new HashSet<>();

        Node index = mLink;
        StringBuilder context = new StringBuilder();
        while (index.next != null && !ss.contains(index)) {
            ss.add(index);
            context.append(" -> ").append(index.index);
            index = index.next;
        }
        context.append(" -> ").append(index.index);

        mTvExample.setText(context.subSequence(" -> ".length(), context.length()));
    }

    /**
     * 检查链表是否环
     *
     * @return true 有环
     */
    private void checkLinkHasRing(Node link) {
        Node fast = link, slow = link;
        boolean hasRing = false;
        int index = 0;
        StringBuilder recordText = new StringBuilder("判断一个单向链表是否有环的检查过程：\n");
        recordText.append("   起点：")
                .append("fast -> ").append(fast.index)
                .append("   ")
                .append("slow -> ").append(slow.index)
                .append("\n");

        while (fast != null && fast.next != null) {
            index++;
            fast = fast.next.next;
            slow = slow.next;
            recordText.append("   第").append(index).append("步：")
                    .append("fast -> ").append(fast == null ? "null" : fast.index)
                    .append("   ")
                    .append("slow -> ").append(slow.index)
                    .append("\n");

            if (fast == slow) {
                hasRing = true;
                break;
            }
        }
        recordText.append("答案：该链表").append(hasRing ? "有" : "无").append("环");
        mTvHasRing.setText(recordText);
    }

    private void calculateRingLength(Node link) {
        Node fast = link, slow = link;

        int length = 0;
        int meetCount = 0;// 会面次数

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (meetCount == 1) {
                length++;
            }
            if (fast == slow) {
                meetCount++;
            }
            if (meetCount == 2) {
                break;
            }
        }
        mTvRingLength.setText(String.format(Locale.getDefault(), "该链表中环的长度为：%d", length));
    }

    static class Node {
        public int index;
        public Node next;

        public Node() {
        }

        public Node(int index) {
            this.index = index;
        }

        public Node(int index, Node next) {
            this.index = index;
            this.next = next;
        }
    }

    class Node2<T> {
        public T t;
        public Node2<T> pre;
        public Node2<T> next;

        public Node2(T t) {
            this.t = t;
        }
    }
}
