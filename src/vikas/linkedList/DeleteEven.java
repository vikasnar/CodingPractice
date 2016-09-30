package vikas.linkedList;

/**
 * Created by VikasN on 9/18/16.
 */
public class DeleteEven {
    public static void main(String... args) {
        LinkedListNode two = new LinkedListNode(2);
        LinkedListNode four = new LinkedListNode(4);
        LinkedListNode three = new LinkedListNode(3);
        LinkedListNode six = new LinkedListNode(6);
        LinkedListNode five = new LinkedListNode(6);
        LinkedListNode one = new LinkedListNode(1);
        two.next = four;
        four.next = six;
        print(two);
        print(deletEven(two));
        one.next = two;
        two.next = five;
        five.next = three;
        print(one);
        print(deletEven(one));

    }

    public static class LinkedListNode {
        int val;
        LinkedListNode next;

        LinkedListNode(int node_value) {
            val = node_value;
            next = null;
        }
    }

    public static void print(LinkedListNode head){
        while(head!=null){
            System.out.print(head.val+"->");
            head = head.next;
        }
        System.out.println();
    }

    public static LinkedListNode deletEven(LinkedListNode head){
        if(head == null) return null;
        LinkedListNode dummy = new LinkedListNode(0);
        dummy.next = head;
        LinkedListNode prev = dummy;
        LinkedListNode now = head;

        while (now!=null)
        {
            if(now.val%2 == 0){
                prev.next = now.next;
                now = now.next;
            }
            else{
                prev = prev.next;
                now = now.next;
            }
        }
        return dummy.next;
    }
}