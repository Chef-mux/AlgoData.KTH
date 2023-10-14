package Util;

import java.util.Arrays;

public class ArrayQueue <E> implements Queue{

    int last = 0;
    int first = 0;
    int size = 4;
    E[] queue;

    public ArrayQueue (){
        queue = (E[]) new Object[size];
    }
    public boolean empty(){
        return (first == last);
    }
    public void add(Object value){
        queue[last] = (E) value;
        last = (last + 1) % size;
        if(last == first){
            E[] temp = (E[]) new Object[size*2];
            for (int i = 0; i < last; i++) {
                temp[i] = queue[i];
            }
            int j = first;
            for (int i = size+first; i <temp.length ; i++) {
                temp[i]= queue[j++];
            }
            first = size+first;
            queue = temp;
            size = size*2;
        }
    }

    public E remove(){
        if (first != last) {
            E temp = queue[first];
            queue[first] = null;
            first = (first + 1) % size;
            return temp;
        }
        System.out.println("queue is empty");
        return null;
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "last=" + last +
                ", first=" + first +
                ", size=" + size +
                ", queue=" + Arrays.toString(queue) +
                '}';
    }
}
