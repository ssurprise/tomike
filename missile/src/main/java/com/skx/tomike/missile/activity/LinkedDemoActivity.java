package com.skx.tomike.missile.activity;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.missile.R;
import com.skx.tomike.missile.bean.ListNode;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static com.skx.tomike.missile.RouteConstantsKt.ROUTER_GROUP;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_LINKED;
import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_QUEUE;

/**
 * 描述 : 数据结构 - 链表 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
@Route(path = ROUTE_PATH_LINKED, group = ROUTER_GROUP)
public class LinkedDemoActivity extends SkxBaseActivity<BaseViewModel> {

    private TextView mTvExample;
    private TextView mTvHasRing;
    private TextView mTvRingLength;
    private TextView mTvRingEnter;
    private TextView mTvReverse;

    private final ListNode mRingLink = new ListNode(0);
    private final ListNode mLink = new ListNode(0);

    private static final String QUESTION = "1.如何判断一个单向链表是否有环?\n2.环的长度如何计算?\n3.如何找到环的入口?\n4.倒叙输出";

    @Override
    protected void initParams() {
        mRingLink.next = new ListNode(1);
        mRingLink.next.next = new ListNode(2);
        mRingLink.next.next.next = new ListNode(3);
        mRingLink.next.next.next.next = new ListNode(4);
        mRingLink.next.next.next.next.next = new ListNode(5);
        mRingLink.next.next.next.next.next.next = new ListNode(6);
        mRingLink.next.next.next.next.next.next.next = mRingLink.next.next;

        mLink.next = new ListNode(1);
        mLink.next.next = new ListNode(2);
        mLink.next.next.next = new ListNode(3);
        mLink.next.next.next.next = new ListNode(4);
        mLink.next.next.next.next.next = new ListNode(5);
        mLink.next.next.next.next.next.next = new ListNode(6);
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
        mTvReverse = findViewById(R.id.tv_linked_answer_reverse);

        createExampleText();
        checkLinkHasRing(mRingLink);
        calculateRingLength(mRingLink);
        ReverseLink(mLink);


        mergeTwoLists(
                new ListNode(1, new ListNode(2, new ListNode(4, null))),
                new ListNode(1, new ListNode(3, new ListNode(4, null)))
        );
    }

    /**
     * 判断链表是否有环
     *
     * @param head 目标链表
     * @return true:有环
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != null && fast.next != null) {
            if (fast.val == slow.val) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    /**
     * 反转链表
     *
     * @param link 原链表
     */
    private void ReverseLink(ListNode link) {
        if (link == null) return;

//        StringBuilder context = new StringBuilder();
//        while (link.next != null) {
//            context.append(link.index).append("->");
//            link = link.next;
//        }
//        Log.e("原链表输出为：", context.toString());

        // 1、2、3、4
        ListNode cur = null;
        ListNode pre = link;

        while (pre != null) {
            ListNode next = pre.next;
            pre.next = cur;
            cur = pre;
            pre = next;
        }

        ListNode logPoint = cur;
        StringBuilder context2 = new StringBuilder();
        while (logPoint != null && logPoint.next != null) {
            context2.append(logPoint.val).append("->");
            logPoint = logPoint.next;
        }
        mTvReverse.setText(String.format(Locale.getDefault(), "倒叙输出为：%s", context2.toString()));
    }

    private void createExampleText() {
        Set<ListNode> ss = new HashSet<>();

        ListNode index = mRingLink;
        StringBuilder context = new StringBuilder();
        while (index.next != null && !ss.contains(index)) {
            ss.add(index);
            context.append(" -> ").append(index.val);
            index = index.next;
        }
        context.append(" -> ").append(index.val);

        mTvExample.setText(context.subSequence(" -> ".length(), context.length()));
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     *
     * @param head 目标链表
     * @param n    删除的倒数第N个位置的节点
     */
    private ListNode removeNthFromEnd(ListNode head, int n) {
        // 示例：1->5->6->8->8->2->7  n=2
        // 方案：卡尺原理，双指针
        // 1.找到需要删除位置节点的偏移节点(快指针)。
        ListNode fast = head;
        ListNode slow = head;
        int i = 0;
        while (i < n && fast != null) {
            fast = fast.next;
            i++;
        }
        // 2.当快指针到达尾部的时候，慢指针也就到了要删除位置的前一个节点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        if (fast == null) {
            return null;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 找到两个单链表相交的起始节点
     *
     * @param headA 链表A
     * @param headB 链表B
     * @return 相交节点
     */
    private ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = a.next;
            b = b.next;

            if (a == null && b == null) {
                return null;
            }

            if (a == null) {
                a = headB;
            }
            if (b == null) {
                b = headA;
            }
        }
        return a;
    }

    /**
     * 删除值为 val 的节点
     *
     * @param head 目标链表
     * @param val  需要删除的值
     * @return 删除后的链表
     */
    private ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode index = head;
        // 如果当前节点的 next节点值和 val相同，则删除。如果不相同，继续执行下一个。
        while (index != null && index.next != null) {
            if (index.next.val == val) {
                index.next = index.next.next;
                continue;// 这里是关键，防止出现多个目标值时露删的情况
            }
            index = index.next;
        }
        // 最后处理头部
        if (head.val == val) {
            head = head.next;
        }
        return head;
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <p>
     * 示例：
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 合并后的升序链表
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode index = result;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                index.next = l1;
                l1 = l1.next;

            } else {
                index.next = l2;
                l2 = l2.next;
            }
            index = index.next;
        }
        if (l1 == null) {
            index.next = l2;
        }
        if (l2 == null) {
            index.next = l1;
        }
        result = result.next;
        return result;
    }

    /**
     * 判断一个链表是否为回文链表。
     * <p>
     * 示例 1:
     * 输入: 1->2
     * 输出: false
     * <p>
     * 示例 2:
     * 输入: 1->2->2->1
     * 输出: true
     * <p>
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * @param head 目标链表
     * @return true:是回文链表
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        // 1.使用快慢指针找到中间位置的节点，慢指针即表示中间的
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 2.反转后半部分节点
        // 1,2,3,2,1  ->  3/5 -> 1、2、3、1、2

        // 1,2,3,3,2,1 -> 1/3 2/2 3/1

        if (fast != null) {
            slow = slow.next;
        }

        ListNode cur = null;
        ListNode pre = slow;

        while (pre != null) {
            ListNode next = pre.next;
            pre.next = cur;
            cur = pre;
            pre = next;
        }

        fast = head;
        while (cur != null) {
            //然后比较，判断节点值是否相等
            if (fast.val != cur.val)
                return false;
            fast = fast.next;
            cur = cur.next;
        }
        return true;
    }

    /**
     * 检查链表是否环
     */
    private void checkLinkHasRing(ListNode link) {
        ListNode fast = link, slow = link;
        boolean hasRing = false;
        int index = 0;
        StringBuilder recordText = new StringBuilder("判断一个单向链表是否有环的检查过程：\n");
        recordText.append("   起点：")
                .append("fast -> ").append(fast.val)
                .append("   ")
                .append("slow -> ").append(slow.val)
                .append("\n");

        while (fast != null && fast.next != null) {
            index++;
            fast = fast.next.next;
            slow = slow.next;
            recordText.append("   第").append(index).append("步：")
                    .append("fast -> ").append(fast == null ? "null" : fast.val)
                    .append("   ")
                    .append("slow -> ").append(slow.val)
                    .append("\n");

            if (fast == slow) {
                hasRing = true;
                break;
            }
        }
        recordText.append("答案：该链表").append(hasRing ? "有" : "无").append("环");
        mTvHasRing.setText(recordText);
    }

    private void calculateRingLength(ListNode link) {
        ListNode fast = link, slow = link;

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
}
