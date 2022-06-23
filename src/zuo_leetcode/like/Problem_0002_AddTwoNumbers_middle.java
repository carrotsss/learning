package zuo_leetcode.like;

/**
 * 给两个非空链表，他们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数字相加，并以相同形式返回一个表示和的链表
 * 你可以假设除了数字0以外，两个数字都不会以0开头
 *
 *  ②→④→③
 *  ⑤→⑥→④
 *  ----------
 *  ⑦→0→⑧
 */
public class Problem_0002_AddTwoNumbers_middle {

	// 不要提交这个类描述
	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int value) {
			this.val = value;
		}
	}

	public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
		int ca = 0;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		ListNode c1 = head1;
		ListNode c2 = head2;
		ListNode node = null;
		ListNode pre = null;
		while (c1 != null || c2 != null) {
			n1 = c1 != null ? c1.val : 0;
			n2 = c2 != null ? c2.val : 0;
			n = n1 + n2 + ca;
			pre = node;
			node = new ListNode(n % 10);
			node.next = pre;
			ca = n / 10;
			c1 = c1 != null ? c1.next : null;
			c2 = c2 != null ? c2.next : null;
		}
		if (ca == 1) {
			pre = node;
			node = new ListNode(1);
			node.next = pre;
		}
		return reverseList(node);
	}

	private static ListNode addTwoNumbersSelf(ListNode head1, ListNode head2) {
		int val1 = 0;
		int val2 = 0;
		int up = 0;
		ListNode nodeNew = null;
		ListNode pre = null;
		while (head1 != null || head2 != null) {
			val1 = head1 != null ? head1.val : 0;
			val2 = head2 != null ? head2.val : 0;
			int val = val1 + val2 + up;
			up = val / 10;
			nodeNew = new ListNode(val % 10);
			nodeNew.next = pre;
			pre = nodeNew;
			head1 = head1 != null ? head1.next : null;
			head2 = head2 != null ? head2.next : null;
		}
		if (up == 1) {
			nodeNew = new ListNode(1);
			nodeNew.next = pre;
		}
		return reverseList(nodeNew);
	}
	public ListNode addTwoNumbersOfficial(ListNode l1, ListNode l2) {
		ListNode head = null, tail = null;
		int carry = 0;
		while (l1 != null || l2 != null) {
			int n1 = l1 != null ? l1.val : 0;
			int n2 = l2 != null ? l2.val : 0;
			int sum = n1 + n2 + carry;
			if (head == null) {
				head = tail = new ListNode(sum % 10);
			} else {
				tail.next = new ListNode(sum % 10);
				tail = tail.next;
			}
			carry = sum / 10;
			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
		}
		if (carry > 0) {
			tail.next = new ListNode(carry);
		}
		return head;
	}

	public static ListNode reverseList(ListNode head) {
		ListNode pre = null;
		ListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		node4.next = node5;
		node5.next = node6;
		ListNode node = addTwoNumbersSelf(node1, node4);
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
	}

}
