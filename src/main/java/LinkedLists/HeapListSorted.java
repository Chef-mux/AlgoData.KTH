package LinkedLists;

import Util.Heap;

public class HeapListSorted implements Heap {
    Node first;

    private class Node<E extends Comparable> {
        E value;
        Node next;

        Node(E value) {
            this.value = value;
            next = null;
        }
    }

    @Override
    public void add(Comparable value) {
        Node node = new Node(value);
        if (first != null) {
            if (first.value.compareTo(node.value) > 0) {
                node.next = first;
                first = node;
            } else {
                Node current = first;
                while (current.next != null) {
                    if (current.next.value.compareTo(node.value) > 0) {
                        break;
                    }
                    current = current.next;
                }
                node.next = current.next;
                current.next = node;
            }
        } else {
            first = node;
        }
    }

    @Override
    public Comparable remove() {
        if(first != null){
            Node toReturn = first;
            first = first.next;
            return toReturn.value;
        }
        return null;
    }

    @Override
    public void print() {
        Node temp = first;
        while (temp != null) {
            System.out.println(temp.value.toString());
            temp = temp.next;
        }
    }
}
