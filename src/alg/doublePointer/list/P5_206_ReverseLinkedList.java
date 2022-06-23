package alg.doublePointer.list;

import alg.doublePointer.list.ListNode;

/**
 * 给你一个单链表的头结点head，请你翻转链表，并返回翻转后的链表
 */
public class P5_206_ReverseLinkedList {
    /**
     * 双指针法
     * @param head
     * @return
     */
    public static ListNode reverseListOfficial(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }


}
