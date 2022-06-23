package datastructure.list;

/**
 * 移除链表元素
 * 删除linkedlist中等于定值val的所有节点
 * 输入：head = 【1,2，6,3,4,5,6,】，val = 6
 * 输出：【1,2,5,4,5】
 */
public class P1_203_RemoveLinkedListElement_iteration {
    /**
     * 有虚拟节点
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElementOfficial(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        ListNode now = head;
        while (now != null) {
            if (now.val == val) {
                pre.next = now.next;
            } else {
                pre = now;
            }
            now = now.next;
        }
        return dummy.next;
    }

    /**
     * 无虚拟节点
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElementOfficial2(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static ListNode removeElementSelf(ListNode head, int val) {
        ListNode pre = new ListNode(-1);
        ListNode now = head;
        while (now != null) {
            if (now.val == val) {
                pre.next = now.next;
            }else{
                pre = now;
            }
            now = now.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(6);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(4);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        ListNode headA = listNode1;
        while (headA != null) {
            System.out.print(headA.val);
            headA = headA.next;
        }
        headA = listNode1;
        removeElementSelf(headA, 6);
        System.out.println();
        while (headA != null) {
            System.out.print(headA.val);
            headA = headA.next;
        }
    }
}
