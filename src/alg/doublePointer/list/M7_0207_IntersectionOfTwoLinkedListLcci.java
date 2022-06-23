package alg.doublePointer.list;

/**
 * 链表相交
 * 给你两个单链表的头节点，请找出两个链表相交的节点
 */
public class M7_0207_IntersectionOfTwoLinkedListLcci {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0, lenB = 0;
        while (curA != null) {
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        //让curA为最长链表的头，lenA为其长度
        if (lenB > lenA) {
            //swap（lenA , lenB)
            int templen = lenA;
            lenA = lenB;
            lenB = templen;
            //swap（curA，curB)
            ListNode tempNode = curA;
            curA = curB;
            curB = tempNode;
        }
        //求长度差
        int gap = lenA - lenB;
        //让curA和curB在同一起点上（末尾位置对其）
        while (gap-- > 0) {
            curA = curA.next;
        }
        //遍历curA和curB，遇到相同则直接返回
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
}
