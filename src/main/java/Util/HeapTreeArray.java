package Util;

import java.util.Arrays;

public class HeapTreeArray {
    int size;
    int elements;
    int nextFreeSpace;
    Integer[] heap;

    public HeapTreeArray(int size){
        this.size = size;
        heap = new Integer[size];
        elements = 0;
        nextFreeSpace = 0;
    }

    public void add(Integer prio){
        if(heap[0] == null){
            heap[0] = prio;
        }
        else{
            heap[nextFreeSpace]= prio;
            bubble(nextFreeSpace);
        }
        elements++;
        nextFreeSpace++;
    }

    private void bubble(int pos){
        int parent = (pos-1)/2;
        if (heap[pos]< heap[parent]){
            swap(pos, parent);
            bubble(parent);
        }
    }
    private void swap(int pos, int parent){
        Integer temp = heap[pos];
        heap[pos] = heap[ parent];
        heap[parent] = temp;
    }

    public Integer remove(){
        if (nextFreeSpace != 0){
            nextFreeSpace--;
            Integer toReturn = heap[0];
            if (nextFreeSpace == 0){
                heap[0] = null;
            }
            else {
                heap[0] = heap[nextFreeSpace];
                heap[nextFreeSpace] = null;
                sink(0);
            }
            return toReturn;
        }
        return null;
    }

    private void sink(int pos){
        int left = (pos*2)+1;
        int right = (pos*2)+2;

        if(heap[right] != null){
            if ( heap[pos] > heap[left] && heap[left] < heap[right]){
                swap(left, pos);
                sink(left);
            }
            else if(heap[pos] > heap[right]){
                swap(right, pos);
                sink(right);
            }
        }
        else if (heap[left] != null && heap[left] < heap[pos]){
            swap(left, pos);
            sink(left);
        }
    }

    public void print(){
        System.out.println(Arrays.toString(heap));
    }

}
