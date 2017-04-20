package geeksforgeeks.dataStructures.Trees;

/**
 * Created by VikasN on 4/19/17.
 */
public class starter {
    public static void main (String... args){
        BinaryTree<Integer> tree = BinaryTree.getTree(1);
        tree.print();
        System.out.println("Size = "+ tree.getSize());
        tree = new BinaryTree<>(new int[]{8,2,3,15,6,7,9,10});


    }
}
