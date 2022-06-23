package alg.doublePointer.list;

/**
 * 删除链表的倒数第N个节点
 * 给你一个链表，删除链表第n个节点，并且返回链表的头节点
 */
public class P6_19_RemoveNthNodeFromEndOfList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (n-- > 0) {
            fast = fast.next;
        }
        //记住待删除节点slow的上一节点
        ListNode prev = null;
        while (fast != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = slow.next;
        slow.next = null;
        return dummy.next;
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

        while (headA != null) {
            System.out.print(headA.val + " ");
            headA = headA.next;
        }
        System.out.println();
        headA = removeNthFromEnd(listNode1,3);
        while (headA != null) {
            System.out.print(headA.val + " ");
            headA = headA.next;
        }
    }

}
