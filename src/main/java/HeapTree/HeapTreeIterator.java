package HeapTree;

import LinkedLists.ListStack;

import java.util.Iterator;

public class HeapTreeIterator implements Iterator {
    private HeapTreeNode current;
    private ListStack<HeapTreeNode> stack;

    HeapTreeIterator(HeapTree tree){
        stack = new ListStack<>();
        current = goLeft(tree.root);

    }
    @Override
    public boolean hasNext() {
        return (current!= null);
    }

    @Override
    public Object next() {
        HeapTreeNode temp = current;
        if (current.right != null)
            current = goLeft(current.right);
        else
            current = stack.pop();
        return temp.priority;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private HeapTreeNode goLeft(HeapTreeNode node) {
        while (node.left != null) {
            stack.push(node);
            node = node.left;
        }
        return node;
    }
}
