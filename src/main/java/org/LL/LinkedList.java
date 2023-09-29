package org.LL;

public class LinkedList {
    Cell first;

    public class Cell {
        int head;
        Cell tail;

       Cell (int val, Cell t1){
           head = val;
           tail = t1;
        }
    }
    public void add (int item){
        Cell cell = new Cell(item, first);
        first = cell;
    }
    public void add (Cell cellToAdd){
        cellToAdd.tail = first;
        first = cellToAdd;
    }

    public void remove(int item){
        if (first.head == item)
            first.tail = first;
        else{
            Cell current = first;
            do {
                if (current.tail.head == item) {
                    current.tail = current.tail.tail;
                    break;
                }
                current = current.tail;
            }
            while (current.tail != null);
        }
    }

    public int length(){
        int length;
        if(first == null){
           length = 0;
        }
        else {
            length = 1;
            Cell last = first;
            while (last.tail != null) {
                length++;
                last = last.tail;
            }
        }
        return length;
    }

    public boolean find(int item){
        boolean state = false;
        if (first!= null) {
            Cell checking = first;
           do {
                if (checking.head == item){
                    state = true;
                    break;
                }
                checking = checking.tail;
            }
           while (checking != null);
        }
        return state;
    }

    public void append(LinkedList b) {
        if(first == null)
            first = b.first;
        Cell next = this.first;
        while (next.tail != null) {
            next = next.tail;
        }
        next.tail = b.first;
        b.first = null;
    }

    public void unlink (Cell cellToUnlink){
        if (first == null)
            System.out.println("No Cells in List");

        else if (cellToUnlink == first)
            first = cellToUnlink.tail;
        else {
            Cell current = first;
            while (current.tail != cellToUnlink) {
                current = current.tail;
            }
            current.tail = cellToUnlink.tail;
        }
    }

    public void cut (int element){
        if (this.find(element) == true){
            Cell cell = this.first;
            while(true) {
                if (cell.head == element) {
                    cell.tail = null;
                    break;
                }
                cell = cell.tail;
            }
        }
    }

    public void print(){
        Cell cell = first;
        do {
            System.out.print(cell.head);
            cell = cell.tail;
        }
        while (cell!=null);
    }

    public void push(int value){
        this.add(value);
    }

    public int pop(){
        Cell cell = first;
        first = first.tail;
        return cell.head;
    }
}