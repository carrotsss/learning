package datastructure.list;

/**
 *  链表判断是否有环
 */
public class P7_141_LinkedListCycle_doublePointer {

    public static class Node {
        int val;
        Node next;
        public Node (int data, Node next)
        {
            this.val  = data;
            this.next  = next;
        }

        public Node (int data){
            this.val = data;
        }
    }

    public static boolean isLoop(Node node){
        Node slow = node ;
        Node fast = node.next;
        while(slow.next != null){
            if (slow.equals(fast)){
                return true;
            }
            if(fast.next == null){
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
            if (fast== null){
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P7_141_LinkedListCycle_doublePointer.Node ndoe1 = new P7_141_LinkedListCycle_doublePointer.Node(1);
        P7_141_LinkedListCycle_doublePointer.Node ndoe2 = new P7_141_LinkedListCycle_doublePointer.Node(2);
        P7_141_LinkedListCycle_doublePointer.Node ndoe3 = new P7_141_LinkedListCycle_doublePointer.Node(3);
        P7_141_LinkedListCycle_doublePointer.Node ndoe4 = new P7_141_LinkedListCycle_doublePointer.Node(4);
        P7_141_LinkedListCycle_doublePointer.Node ndoe5 = new P7_141_LinkedListCycle_doublePointer.Node(5);
        ndoe1.next = ndoe2;
        ndoe2.next = ndoe3;
        ndoe3.next = ndoe1;
        System.out.println(P7_141_LinkedListCycle_doublePointer.isLoop(ndoe1));;

    }

}
