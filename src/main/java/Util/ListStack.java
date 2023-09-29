package Util;

public class ListStack<E> {

    Cell<E> first;

    private class Cell<F> {
        F value;
        Cell<F> next;
        Cell<F> previous;

        Cell (F value, Cell<F> oldCell){
            this.value = value;
            next = oldCell;
        }
    }
    public void push (E value) {
        Cell<E> cell = new Cell<E>(value, first);
        first = cell;
        if (cell.next != null) {
            cell.next.previous = cell;
        }
    }
    public E pop (){
        E value = null;
        if (first != null){
            value = first.value;
            first = first.next;
            if (first != null)
                first.previous = null;
        }

        return value;
    }

    public void print(){
        if (first != null) {
            Cell cell = first;
            do {
                System.out.print(cell.value + ", ");
                cell = cell.next;
            }
            while (cell != null);
        }
    }
}
