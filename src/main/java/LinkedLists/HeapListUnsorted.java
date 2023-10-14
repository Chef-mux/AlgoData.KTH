package LinkedLists;

import Util.Heap;

public class HeapListUnsorted implements Heap {

    Node first;
    Node last;

    private class Node<E extends Comparable> {
        E value;
        Node<E> next;
        Node(E value, Node<E> first){
            this.value = value;
            next = first;
        }
    }

    @Override
    public void add(Comparable value) {
        first = new Node(value, first);
    }

    @Override
    public Comparable remove() {
        if(first != null){
            Node candidate = first;
            Node current = first.next;
            Node previous = first;
            Node candPrev = null;
            while (current != null){
                if (current.value.compareTo(candidate.value)<0){
                    candidate = current;
                    candPrev = previous;
                }
                previous = current;
                current =current.next;
            }
            if (candPrev!= null) {
                candPrev.next = candidate.next;
            }
            else {
                first = candidate.next;
            }
            candidate.next = null;
            return candidate.value;
        }
        else
            return null;
    }

    public boolean empty(){
        return (first == null);
    }
    @Override
    public void print() {
        Node temp = first;
        while (temp != null){
            System.out.println(temp.value.toString());
            temp = temp.next;
        }
    }
}
