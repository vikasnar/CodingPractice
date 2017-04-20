package geeksforgeeks.dataStructures.Trees;

/**
 * Created by VikasN on 4/13/17.
 */
public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> left;
    private Node<T> right;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Node<T> next(){
        return this.right;
    }

    public void setNext(Node<T> next){
        setRight(next);
        next.setLeft(this);
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> setLeft(Node<T> left) {
        this.left = left;
        return this;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> setRight(Node<T> right) {
        this.right = right;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isLeaf(){
        if(this.left == this.right)
            return true;
        return false;
    }

    public Node<T> copy(){
        Node<T> temp = new Node<T>();
        temp.data = this.data;
        temp.left = this.left;
        temp.right = this.right;
        return temp;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Node)){
            return false;
        }else if (obj == this){
            return true;
        }else {
            Node temp = (Node)obj;
            if (temp.data == this.data && this.left == temp.left && this.right == temp.right)
                return true;
        }
        return false;
    }

}
