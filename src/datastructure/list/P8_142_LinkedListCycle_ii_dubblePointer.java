package datastructure.list;

/**
 * 环境链表II
 * 给定一个链表，返回链表开始入环的第一个节点，如果链表无环，则返回null
 * 为了表示给定链表中的环，使用证书pos来表示链表尾连接到链表中的位置（索引从0开始），如果pos是-1，则该链表不存在环。
 * 输入：【3,2,0，-4】，pos = 1
 * 输出：tail connect to node index 1
 */
public class P8_142_LinkedListCycle_ii_dubblePointer {
    public static ListNode detectCycleOfficial(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode index1 = fast;
                ListNode index2 = head;
                //两个指针，从头结点和相遇节点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode ndoe1 = new ListNode(1);
        ListNode ndoe2 = new ListNode(2);
        ListNode ndoe3 = new ListNode(3);
        ListNode ndoe4 = new ListNode(4);
        ListNode ndoe5 = new ListNode(5);
        ndoe1.next = ndoe2;
        ndoe2.next = ndoe3;
        ndoe3.next = ndoe4;
        ndoe4.next = ndoe5;
        ndoe5.next = ndoe3;
        System.out.println(detectCycleOfficial(ndoe1).val);;

    }
}
