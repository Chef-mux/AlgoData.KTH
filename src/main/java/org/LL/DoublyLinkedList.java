package org.LL;

public class DoublyLinkedList {
    Cell first;

    public class Cell{
        int head;
        Cell next;
        Cell previous;
        Cell (int value, Cell oldCell){
            head = value;
            next = oldCell;
        }
    }

    public void add (int value){
        Cell cell = new Cell(value, first);
        first = cell;
        if (cell.next != null) {
            cell.next.previous = cell;
        }
    }
    public void add (Cell cellToAdd){
        first.previous =cellToAdd;
        cellToAdd.next = first;
        first = cellToAdd;
    }
    public void remove (int value){
        if (first.head == value) {
            first = first.next;
            first.previous = null;
        }
        else {
            Cell current = first.next;
            while (current != null) {
                if (current.head == value) {
                    if ( current.next != null) {
                        current.previous.next = current.next;
                        current.next.previous = current.previous;
                        break;
                    }
                    current.previous.next = null;
                    break;
                }
                else
                    current = current.next;
            }
        }
    }

    public int length (){
        int count;
        if (first == null){
            count = 0;
        }
        else {
            Cell current = first;
            count = 1;
            while (current.next != null){
                current = current.next;
                count++;
            }
        }
        return count;
    }

    public boolean find (int value){
        boolean found = false;
        Cell current = first;
        while (current != null){
            if (current.head == value){
                found = true;
                break;
            }
            current = current.next;
        }
        return found;
    }

    public void append (DoublyLinkedList listB){
        Cell current = first;
        while (current.next != null){
            current = current.next;
        }
        current.next = listB.first;
        listB.first.previous = current;
        listB.first = null;
    }

    public void unlink (Cell cellToUnlink){

        if(cellToUnlink == first){
            first = first.next;
            first.previous = null;
        }
        else if( cellToUnlink.next == null){
            cellToUnlink.previous.next = null;
        }
        else {
            cellToUnlink.previous.next = cellToUnlink.next;
            cellToUnlink.next.previous = cellToUnlink.previous;
        }

    }
    public void print(){
        Cell cell = first;
        do {
            System.out.print(cell.head+", ");
            cell = cell.next;
        }
        while (cell!=null);
    }
}
