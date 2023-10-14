package BinaryTree;

import LinkedLists.ListQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

class TreeIteratorBreadthFirst implements Iterator {
    private BinaryTreeNode current;
    private ListQueue listQueue;

    TreeIteratorBreadthFirst(BinaryTree binaryTree) {
        listQueue = new <BinaryTreeNode> ListQueue();
        current = binaryTree.root;
        queueUp();
    }

    @Override
    public boolean hasNext() {
        return (current != null);
    }

    @Override
    public Object next() {
        BinaryTreeNode temp = current;
        try {
            current = (BinaryTreeNode) listQueue.remove();
            queueUp();
        } catch (NoSuchElementException exception) {
            current = null;
        }
        return temp.value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void queueUp() {
        if (current.left != null)
            listQueue.add(current.left);
        if (current.right != null)
            listQueue.add(current.right);
    }
}
