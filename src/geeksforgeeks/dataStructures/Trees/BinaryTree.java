package geeksforgeeks.dataStructures.Trees;

/**
 * Created by VikasN on 4/19/17.
 */
public class BinaryTree<T extends Comparable<T>> {
    Node<T> head;
    int size;

    public BinaryTree() {
    }

    public BinaryTree(Node<T> head) {
        this.head = head;
    }

    public int getSize(){
        return getSize(this.head);
    }

    public int getSize( Node<T> head) throws IllegalArgumentException{
        int size = 0;
        if (head == null){
            return 0;
        } else {
            return 1 + getSize(head.getLeft()) + getSize(head.getRight());
        }
    }

    public int getHeight(){
        return getHeight(this.head);
    }

    private int getHeight(Node<T> head){
        if ( head == null)
            return 0;
        else
            return 1 + Math.max(getHeight(head.getLeft()), getHeight(head.getRight()));
    }

    public static BinaryTree<Integer> getTree(int choice){
        BinaryTree<Integer> tree = new BinaryTree<>();
        switch (choice){
            //balanced Binary search tree
            case 1 :
                tree.head = new Node<>(25);
                tree.head.setLeft(new Node<>(15)
                        .setLeft(new Node<>(10).
                                setLeft(new Node<>(4)).setRight(new Node<>(12)))
                        .setRight(new Node<>(22).
                                setLeft(new Node<>(18)).setRight(new Node<>(24))));
                tree.head.setRight(new Node<>(50)
                        .setLeft(new Node<>(35)
                                .setLeft(new Node<>(31)).setRight(new Node<>(44)))
                        .setRight(new Node<>(70)
                                .setLeft(new Node<>(66)).setRight(new Node<>(90))));
                break;
            // Random tree not balanced
            case 2 :
                tree.head = new Node<>(10);
                tree.head.setLeft(new Node<>(7)
                        .setLeft(new Node<>(12).
                                setLeft(new Node<>(4)))
                        .setRight(new Node<>(22).
                                setLeft(new Node<>(18))));
                tree.head.setRight(new Node<>(50)
                        .setLeft(new Node<>(15)
                                .setLeft(new Node<>(3)).setRight(new Node<>(9)))
                        .setRight(new Node<>(14)
                                .setRight(new Node<>(2))));
                break;
            default:
                return tree;
        }
        return tree;
    }

    public BinaryTree(int[] array){
        for (int i = 0; i < array.length ; i ++){
            insert(this.head, (Node<T>) new Node<Integer>(array[i]));
        }
    }

    public void insert(Node<T> head, Node<T> newNode) {
        if (newNode == null)
            return;
        else if (head == null)
            this.head = newNode;
        else if (head.isLeaf()) {
            if (head.getData().compareTo(newNode.getData()) > 0)
                head.setLeft(newNode);
            else if (head.getData().compareTo(newNode.getData()) < 0) {
                head.setRight(newNode);
            } else if (head.getData().compareTo(newNode.getData()) > 0)
                insert(head.getLeft(), newNode);
            else
                insert(head.getRight(), newNode);
        }
        return;
    }

    public void print(){
        for (int i = 0; i < this.getHeight(); i++) {
            this.print(this.head, i);
        }
    }

    private void print(Node<T> head, int lvl) {
        if ( head == null )
            return;
        System.out.print(head.getData().toString());
        print(head.getLeft(), lvl -1);
        print(head.getRight(), lvl-1);
        System.out.println();
    }



}
