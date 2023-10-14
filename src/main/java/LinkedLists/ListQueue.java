package LinkedLists;

import Util.Queue;

import java.util.NoSuchElementException;

public class ListQueue implements Queue {
    Node  first;
    Node last;

    private class Node<F>{
        F item;
        Node<F> next;

        Node (F item, Node <F> last){
            this.item = item;
            if (last!= null)
                last.next = this;
        }
    }

    public void add(Object item){
        Node node =  new Node<>(item, last);
        last = node;
        if(first == null)
            first = node;
    }

    public Object remove() throws NoSuchElementException {
        if(first != null) {
            Node node = first;
            first = first.next;
            if (first == null)
                last = null;
            return node.item;
        }
       throw new NoSuchElementException("ListQueue is empty");
    }
}
