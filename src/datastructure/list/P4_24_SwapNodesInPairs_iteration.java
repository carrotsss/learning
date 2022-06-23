package datastructure.list;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表
 * 不能只是交换节点内部的值，而是要两两交换节点
 */
public class P4_24_SwapNodesInPairs_iteration {
    /**
     * 递归
     * @param head
     * @return
     */
    private static ListNode swapPairsRecursiveOfficial(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newListNode = swapPairsRecursiveOfficial(next.next);
        next.next = head;
        head.next = newListNode;
        return next;
    }

    /**
     * 非递归
     */
    private static ListNode swapPairsOfficial(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode tmp = head.next.next;//缓存 next
            pre.next = head.next;//将pre的next改为head的next
            head.next.next = head;//将head.next（pre.next)的next，指向head
            head.next = tmp;//将head的next接上缓存的tmp
            pre = head;//步进一位，一位相当于两个节点
            head = head.next;//步进一位，一位相当于两个节点
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode headA = listNode1;

        while (headA != null) {
            System.out.print(headA.val);
            headA = headA.next;
        }
        System.out.println();
        ListNode listNode = swapPairsOfficial(listNode1);
        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
    }

}
