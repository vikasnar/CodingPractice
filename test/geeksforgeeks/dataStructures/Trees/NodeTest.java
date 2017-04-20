package geeksforgeeks.dataStructures.Trees;

import static org.junit.Assert.*;

/**
 * Created by VikasN on 4/13/17.
 */
public class NodeTest {
    Node<Integer> left = new Node<>(4);
    Node<Integer> right = new Node<>(6);
    Node<Integer> node = new Node<>(5, left, right);
    Node<Integer> left1 = new Node<>(7);
    Node<Integer> right1 = new Node<>(9);
    Node<Integer> node1 = new Node<>(8, left1, right1);


    @org.junit.Test
    public void isLeaf() throws Exception {
        assertTrue(left.isLeaf());
        assertFalse(node.isLeaf());
    }

    @org.junit.Test
    public void copy() throws Exception {
        Node temp = node.copy();
        assertTrue(node.equals(temp));
        assertTrue(temp.equals(node));
    }

    @org.junit.Test
    public void equals() throws Exception {
        Node temp = node1.copy();
        assertTrue(node1.equals(temp));
        assertFalse(node.equals(node1));
    }

}