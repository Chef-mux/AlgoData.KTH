package BinaryTree;

import LinkedLists.ListStack;

import java.util.Iterator;


class TreeIterator implements Iterator {
    private BinaryTreeNode next;
    private ListStack<BinaryTreeNode> stack;

    TreeIterator(BinaryTree tree) {
        stack = new ListStack<>();
        next = tree.root;
        next = goLeft(next);
    }

    @Override
    public boolean hasNext() {
        return (next != null);
    }

    @Override
    public Object next() {
        BinaryTreeNode temp = next;
        if (next.right != null)
            next = goLeft(next.right);
        else
            next = stack.pop();
        return temp.value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private BinaryTreeNode goLeft(BinaryTreeNode binaryTreeNode) {
        while (binaryTreeNode.left != null) {
            stack.push(binaryTreeNode);
            binaryTreeNode = binaryTreeNode.left;
        }
        return binaryTreeNode;
    }
}
