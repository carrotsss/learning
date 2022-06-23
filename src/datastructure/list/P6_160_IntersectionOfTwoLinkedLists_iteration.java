package datastructure.list;

/**
 * 链表相交
 * A和B两个链表相交于c1：
 * a1 -> a2 -> c1 -> c2 -> c3
 * b1 -> b2 -> b3 -> c1 -> c2 -> c3
 * 设A的长度为a+c b的长度为b+c ,可知a+c+b = b+c+a
 * 当访问A的链表的指针访问到链表尾部时，令他从链表b的头部开始访问链表b，这样就能控制A和B两个链表指针能同时访问到交点。
 * 如果不存在交点，那么a+b=b+a，以下实现代码l1和l2会同时为null，从而退出循环。
 */
public class P6_160_IntersectionOfTwoLinkedLists_iteration {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l1;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode6.next = listNode4;
        ListNode headA = listNode1;
        ListNode headB = listNode6;
        ListNode comm = getIntersectionNode(headA, headB);
        System.out.println(comm.val);
    }
}
