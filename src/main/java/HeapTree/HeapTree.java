package HeapTree;

import Util.HeapTreeArray;


import java.util.Iterator;

public class HeapTree implements Iterable {
    HeapTreeNode root;

    public void add (int priority){
        if (root == null){
            root = new HeapTreeNode(priority);
        }
        else {
            root.add(new HeapTreeNode(priority));
        }
    }


    public int remove(){
        int temp = -1;
        if (root != null) {
            temp = root.priority;
            if (root.children != 0) {
                root.lift();
            }
            else {
                root = null;
            }
        }
        return temp;
        }

        public int push (int increment){
        root.priority += increment;
        return root.push();
        }

    @Override
    public Iterator iterator() {
        return new HeapTreeIterator(this);
    }

    public void print(){
        root.print();
    }
}
