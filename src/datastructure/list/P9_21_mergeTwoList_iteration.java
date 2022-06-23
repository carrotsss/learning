package datastructure.list;

/**
 * 合并链表
 * 将两个升序链表合并为一个新的升序链表并返回，新联表示通过拼接给定的两个链表的所有节点组成的。
 * 1-》2-》4
 * 1-》3-》4
 * 1-》1-》2-》3—》4-》4
 * 输入：[1,2,4] ,[1,3,4]
 * 输出：[1,1,2,3,4,4,]
 */
public class P9_21_mergeTwoList_iteration {

    /**
     * 递归解法
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoListOfficialRecursion(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoListOfficialRecursion(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListOfficialRecursion(list1, list2.next);
            return list2;
        }
    }

    /**
     * 迭代解法: 就是循环不满足条件就继续处理
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoListOfficialIteration(ListNode list1, ListNode list2) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            }else{
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 == null ? list2 : list1;
        return preHead.next;
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
        listNode5.next = listNode6;
        ListNode listNode = mergeTwoListOfficialIteration(listNode1, listNode5);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
