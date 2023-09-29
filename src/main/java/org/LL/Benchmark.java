package org.LL;

import BinaryTree.Tree;
import Util.BinarySearch;

import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static void betterMergeSort (double[] array){
        if (array.length == 0) {
            return;
        }
        double[] auxArray = new double[array.length];
        for (int i = 0; i < array.length; i++)
            auxArray[i] = array[i];

        betterSort(array,auxArray,0,array.length-1);
    }
    private static void betterSort(double[] array, double[] auxArray, int from, int to) {
        if( from != to){
            int mid = (from + to) / 2;
            betterSort(auxArray, array, from, mid);
            betterSort(auxArray, array, mid+1, to);
            betterMerge(array, auxArray, from, mid, to);
        }
    }

    private static void betterMerge(double[] array, double[] auxArray, int from, int mid, int to) {

        int i = from;
        int j = mid +1;
        for( int k = from; k<= to; k++){
            if (i > mid){
                array[k] = auxArray[j];
                j++;
            }
            else if (j > to) {
                array[k] = auxArray[i];
                i++;
            }
            else if (auxArray[i] < auxArray[j]) {
                array[k]= auxArray[i];
                i++;
            }
            else{
                array[k] = auxArray[j];
                j++;
            }
        }
    }
    public static void benchmarkLists(int elements, int stat){
        int k = 1000;
        double[] times = new double[k];
        DoublyLinkedList doubleElements = new DoublyLinkedList();
        LinkedList singleElements = new LinkedList();
        LinkedList statList = new LinkedList();

        LinkedList.Cell[] singleReferences =
                new LinkedList.Cell[elements];
        DoublyLinkedList.Cell[] doubleReferences =
                new DoublyLinkedList.Cell[elements];

        Random random = new Random();
        int[] keys = new int[k];
        for (int i = 0; i < k; i++)
            keys[i] = random.nextInt(elements);

        int[] arrayA = new int[elements];
        int[] arrayB = new int[stat];

        for (int i = 0; i < elements; i++){
            singleElements.add(i);
            doubleElements.add(i);

            singleReferences[i] = singleElements.first;
            doubleReferences[i] = doubleElements.first;

            arrayA[i]= i;
        }
        int counter = stat-1;
        for (int i = elements+stat; i > elements; i--){
            statList.add(i);
            arrayB[counter--] = i;
        }

        double minTime;
        double median;
        System.out.printf("%d", elements);


        //appending
        minTime = Double.MAX_VALUE;
        median = 0;
        for (int i = 0; i < k; i++) {
            long start = System.nanoTime();
            singleElements.append(statList);
            long stop = System.nanoTime();
            double time = stop - start;
            times[i] = time;
            if (time < minTime)
                minTime = time;
            singleElements.cut(0);
        }
        betterMergeSort(times);
        median = times[times.length/2];
        System.out.printf("%13.1f %6.1f", minTime/1000.0, median/1000.0);

        minTime = Double.MAX_VALUE;
        median = 0;
        for (int i = 0; i < k; i++) {
            long start = System.nanoTime();
            statList.append(singleElements);
            long stop = System.nanoTime();
            double time = stop - start;
            times[i] = time;
            if (time < minTime)
                minTime = time;
            statList.cut(elements+stat);
        }
        betterMergeSort(times);
        median = times[times.length/2];
        System.out.printf("%13.1f %6.1f", minTime/1000.0, median/1000.0);

        //appending array
        minTime = Double.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            long start = System.nanoTime();
            int[] arrayAClone = arrayA.clone();
            int[] arrayC = new int[elements + stat];
            int j = 0;
            for (int p = 0; p < elements; p++) {
                arrayC[j++] = arrayAClone[p];
            }
            for (int p = 0; p < stat; p++) {
                arrayC[j++] = arrayB[p];
            }
            long stop = System.nanoTime();
            double time = stop - start;
            times[i] = time;
            if (time < minTime)
                minTime = time;

        }
        betterMergeSort(times);
        median = times[times.length/2];
        System.out.printf("%13.1f %6.1f", minTime/1000.0, median/1000.0);

        // building list
        minTime = Double.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            long start = System.nanoTime();
            LinkedList build = new LinkedList();
            for(int j = 0; j <elements; j++){
                build.add(j);
            }
            long stop = System.nanoTime();
            double time = stop - start;
            times[i] = time;
            if (time < minTime)
                minTime = time;
        }
        betterMergeSort(times);
        median = times[times.length/2];
        System.out.printf("%13.1f %6.1f", minTime/1000, median/1000);

        int loop = 1000;

        minTime = Double.MAX_VALUE;
        for (int j = 0; j < loop; j++) {
            long start = System.nanoTime();
            for (int i = 0; i < k; i++) {
                int[] test = new int[k];
            }
            long stop = System.nanoTime();
            double time = stop - start;
            times[j] = time / loop;
            if (time < minTime)
                minTime = time;
        }
        betterMergeSort(times);
        median = times[times.length/2];
        System.out.printf("%13.1f %6.1f", minTime/loop, median);

        loop = 1000;
        // single unlink
        minTime = Double.MAX_VALUE;
        for (int j = 0; j < loop; j++) {
            for (int i = 0; i < k; i++)
                keys[i] = random.nextInt(elements);
            long start = System.nanoTime();
            for (int i = 0; i < k; i++) {
                int key = keys[i];
                LinkedList.Cell cellToUnlink = singleReferences[key];
                singleElements.unlink(cellToUnlink);
                singleElements.add(cellToUnlink);
            }
            long stop = System.nanoTime();
            double time = stop - start;
            times[j] = time / loop;
            if (time < minTime)
                minTime = time;
        }
        betterMergeSort(times);
        median = times[times.length/2];
        System.out.printf("%13.1f %6.1f", minTime/loop, median);

        // double unlink
        minTime = Double.MAX_VALUE;
        for (int j = 0; j < loop; j++) {
            for (int i = 0; i < k; i++)
                keys[i] = random.nextInt(elements);
            long start = System.nanoTime();
            for (int i = 0; i < k; i++) {
                int key = keys[i];
                DoublyLinkedList.Cell cellToUnlink = doubleReferences[key];
                doubleElements.unlink(cellToUnlink);
                doubleElements.add(cellToUnlink);
            }
            long stop = System.nanoTime();
            double time = stop - start;
            times[j] = time/loop;
            if (time < minTime)
                minTime = time;
        }

        betterMergeSort(times);
        median = times[times.length/2];
        System.out.printf("%13.1f %6.1f\n", minTime/loop, median);
    }
    public static void benchmarkTree(int size){
        Tree tree = new Tree();
        int outerLoop = 1000;
        int innerLoop = 1000;
        int[] arrayForBinarySearch = sortedArray(size);
        int[] treeIndexes = scrambleArray(arrayForBinarySearch);

        for (int i = 0; i < size; i++) {
            tree.addRecursive(treeIndexes[i], treeIndexes[i]+10);
        }
        if (size < innerLoop) {
            Arrays.sort(treeIndexes);
            int[] temp = new int[innerLoop];
            Random random = new Random();
            for (int i = 0; i < innerLoop ; i++) {
                temp[i] = random.nextInt(treeIndexes[treeIndexes.length-1]+1);
            }
            treeIndexes = temp;
        }
        else
            treeIndexes = scrambleArray(treeIndexes);


        double median;
        double[] times = new double[outerLoop];

        for (int i = 0; i < outerLoop; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < innerLoop; j++) {
                BinarySearch.search(arrayForBinarySearch,treeIndexes[j]);
            }
            long stop = System.nanoTime();

            times[i]= stop- start;
        }
        betterMergeSort(times);
        median = times[times.length/2];
        System.out.print(size + "     "+ median/innerLoop + "     \n");

/*
        for (int i = 0; i < outerLoop; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < innerLoop; j++) {
                tree.lookUpRecursive(treeIndexes[j]);
            }
            long stop = System.nanoTime();

            times[i]= stop- start;
        }
        betterMergeSort(times);
        median = times[times.length/2];
        System.out.println(median/innerLoop);

 */
    }

    private static int[] scrambleArray(int[] array) {
        int[] scrambled = (int[]) array.clone();
        Random random = new Random();

        for (int i = scrambled.length - 1; i > 0; --i) {
            int j = random.nextInt(i + 1);
            int temp = scrambled[i];
            scrambled[i] = scrambled[j];
            scrambled[j] = temp;
        }
        return scrambled;
    }

    private static int[] sortedArray ( int n){
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = rnd.nextInt(10);

        for (int i = 0; i < n; ++i) {
            array[i] = nxt;
            nxt += rnd.nextInt(10) + 5;
        }

        return array;
    }
}
