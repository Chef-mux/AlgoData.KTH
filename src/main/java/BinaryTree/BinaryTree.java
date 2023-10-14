package BinaryTree;

import java.util.Iterator;

public class BinaryTree implements Iterable<Integer> {
    BinaryTreeNode root;

    public void addRecursive(Integer key, Integer value) {
        if (root == null) {
            root = new BinaryTreeNode(key, value);
        } else {
            root.addRecursive(key, value);
        }
    }
    public void addIterative(Integer key, Integer value){
        if (root == null) {
            root = new BinaryTreeNode(key, value);
        } else {
            root.addIterative(key, value);
        }
    }

    public Integer lookUpRecursive(Integer key) {
        Integer found = null;
        if (root != null) {
            found = root.loookUpRecursive(key);
        }
        return found;
    }

    public void print() {
        if (root != null) {
            root.print();
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        //return new TreeIterator(this);
        return new TreeIteratorBreadthFirst(this);
    }
}
