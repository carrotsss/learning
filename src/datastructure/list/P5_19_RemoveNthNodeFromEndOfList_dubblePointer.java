package datastructure.list;

/**
 * 删除链表的倒数第n个节点
 * 删除链表倒数第N个节点，并且返回链表的头结点
 * 输入：【1,2,3,4,5】
 * 输出：【1,2,3,5】
 */
public class P5_19_RemoveNthNodeFromEndOfList_dubblePointer {
    private static ListNode removeElementFromEndOfList(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (n-- > 0) {
            fast = fast.next;
        }
        //记住 待删除节点slow的上一节点
        ListNode pre = null;
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        //上一节点的next指针绕过待删除节点slow，直接指向下一节点
        pre.next = slow.next;
        slow.next = null;
        return dummy.next;
    }

    private static ListNode removeFromEndOfListSelf(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        ListNode pre = null;
        while (fast != null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = slow.next;
        slow.next = null;
        return head;
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
        ListNode comm = removeFromEndOfListSelf(headA, 2);
        while (comm != null) {
            System.out.println(comm.val);
            comm = comm.next;
        }
    }
}
