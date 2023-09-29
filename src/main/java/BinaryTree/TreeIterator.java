package BinaryTree;

import Util.ListStack;

import java.util.Iterator;


class TreeIterator implements Iterator {
    private Node next;
    private ListStack <Node> stack;

    public TreeIterator (Tree tree){
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
        Node temp = next;
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
    private Node goLeft(Node node){
        while (node.left != null) {
            stack.push(node);
            node = node.left;
        }
        return node;
    }
}
