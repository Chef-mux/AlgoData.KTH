package View;

import HeapTree.HeapTree;
import LinkedLists.HeapListSorted;
import LinkedLists.HeapListUnsorted;
import Util.BenchHeap;
import Util.Heap;
import Util.HeapTreeArray;

import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("size \ttree \tarray ");
        /*for (int i = 2; i < 2000000; i= i*2) {
            BenchHeap.benchmark(i);
        }
         */

        for (int i = 2; i < 262144; i= i*2) {
            BenchHeap.benchmark(i);
        }

    }
}
