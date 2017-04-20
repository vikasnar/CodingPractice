package vikas.linkedList;

/**
 * Created by VikasN on 9/14/16.
 */
public class Node<T> {
    T value;
    Node<T> prev;
    Node<T> next;

    Node(T value){
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}
