package Util;

import HeapTree.HeapTree;
import LinkedLists.HeapListSorted;
import LinkedLists.HeapListUnsorted;

import java.util.Arrays;
import java.util.Random;

public class BenchHeap {

    public static void benchmark(int length){


        Integer[] listAndArrayNumbers = new Integer[length];
        int[] treeNumbers = new int[1023];

        Random random = new Random();
        for (int i = 0; i < treeNumbers.length; i++) {
            treeNumbers[i] = random.nextInt(10000);

        }
        for (int i = 0; i < length; i++) {
            int temp = random.nextInt(length*5);
            listAndArrayNumbers[i]= temp;
        }
        HeapListSorted sorted = new HeapListSorted();
        HeapListUnsorted unsorted = new  HeapListUnsorted();
        HeapTreeArray array = new HeapTreeArray(length*4);
        System.out.print("\n"+length);

        //benchLists(length,listAndArrayNumbers,sorted);
        //benchLists(length,listAndArrayNumbers,unsorted);
        //benchArray(length,listAndArrayNumbers, array);
        //benchTree(treeNumbers,length);
        benchTreeVsArray(length);

    }

    private static void benchTreeVsArray(Integer length) {
        int k = 1000;

        Random random = new Random();
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = random.nextInt(10000);
        }
        HeapTree tree = new HeapTree();
        HeapTreeArray array = new HeapTreeArray(length*4);

        double[] times = new double[k];
        double median;

        for (int i = 0; i < k; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < length ; j++) {
                tree.add(numbers[j]);
            }
            for (int j = 0; j < length; j++) {
                tree.remove();
            }
            long stop = System.nanoTime();
            times[i] = stop-start;
        }
        Arrays.sort(times);
        median = times[times.length/2];
        System.out.printf("\t%.2f",median/length);

        for (int i = 0; i < k; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < length ; j++) {
                array.add(numbers[j]);
            }
            for (int j = 0; j < length; j++) {
                array.remove();
            }
            long stop = System.nanoTime();
            times[i] = stop-start;
        }
        Arrays.sort(times);
        median = times[times.length/2];
        System.out.printf("\t%.2f",median/length);
    }

    private static void benchLists(int length,Integer[] array, Heap heap){
        int outerLoop = 1000;

        double addTimes[] = new double[outerLoop]; // time it takes to only add
        double removeTimes[] = new double[outerLoop]; // time it takes to only remove
        double bothTimes[] = new double[outerLoop]; // time it takes to do both add and remove
        double minTimeAdd = Double.MAX_VALUE;
        double minTimeRemove = Double.MAX_VALUE;
        double minTimeBoth = Double.MAX_VALUE;

        for (int i = 0; i < outerLoop; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < length ; j++) {
                heap.add(array[j]);
            }
            long stop = System.nanoTime();
            if ((stop-start) < minTimeAdd ){
                minTimeAdd = (stop-start);
            }
            addTimes[i] = (stop-start);

            start = System.nanoTime();
            for (int j = 0; j < length ; j++) {
                heap.remove();
            }
            stop = System.nanoTime();
            if ((stop-start) < minTimeRemove ){
                minTimeRemove = (stop-start);
            }
            removeTimes[i] = (stop-start);

            start = System.nanoTime();
            for (int j = 0; j < length ; j++) {
                heap.add(array[j]);
            }
            for (int j = 0; j < length; j++) {
                heap.remove();
            }
            stop = System.nanoTime();
            if ((stop-start) < minTimeBoth ){
                minTimeBoth = (stop-start);
            }
            bothTimes[i] = (stop-start);
        }
        Arrays.sort(addTimes);
        Arrays.sort(removeTimes);
        Arrays.sort(bothTimes);

        double addMedian = addTimes[outerLoop/2];
        double removeMedian = removeTimes[outerLoop/2];
        double bothMedian = bothTimes[outerLoop/2];
        System.out.printf("\t%.2f \t%.2f \t%.2f\t", addMedian/length/1000, removeMedian/length/1000, bothMedian/length/1000);
    }

    private static void benchArray(int length,Integer[] array, HeapTreeArray heap){
        int outerLoop = 1000;

        double addTimes[] = new double[outerLoop]; // time it takes to only add
        double removeTimes[] = new double[outerLoop]; // time it takes to only remove
        double bothTimes[] = new double[outerLoop]; // time it takes to do both add and remove
        double minTimeAdd = Double.MAX_VALUE;
        double minTimeRemove = Double.MAX_VALUE;
        double minTimeBoth = Double.MAX_VALUE;

        for (int i = 0; i < outerLoop; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < length ; j++) {
                heap.add(array[j]);
            }
            long stop = System.nanoTime();
            if ((stop-start) < minTimeAdd ){
                minTimeAdd = (stop-start);
            }
            addTimes[i] = (stop-start);

            start = System.nanoTime();
            for (int j = 0; j < length ; j++) {
                heap.remove();
            }
            stop = System.nanoTime();
            if ((stop-start) < minTimeRemove ){
                minTimeRemove = (stop-start);
            }
            removeTimes[i] = (stop-start);

            start = System.nanoTime();
            for (int j = 0; j < length ; j++) {
                heap.add(array[j]);
            }
            for (int j = 0; j < length; j++) {
                heap.remove();
            }
            stop = System.nanoTime();
            if ((stop-start) < minTimeBoth ){
                minTimeBoth = (stop-start);
            }
            bothTimes[i] = (stop-start);
        }
        Arrays.sort(addTimes);
        Arrays.sort(removeTimes);
        Arrays.sort(bothTimes);

        double addMedian = addTimes[outerLoop/2];
        double removeMedian = removeTimes[outerLoop/2];
        double bothMedian = bothTimes[outerLoop/2];
        System.out.printf("\t%.2f \t%.2f \t%.2f\t", addMedian/length/1000, removeMedian/length/1000, bothMedian/length/1000);
    }

    private static void benchTree(int[] array, int incr){

        int k = 1000;
        int p = incr;
        HeapTree tree;

        Random random = new Random();

        double[]times = new double[k];//skapa array för tider
        double median;
        int temp;
        for (int i = 0; i < k ; i++) { //loopa k (1000) ggr)
            tree = makeTree();
            long start = System.nanoTime();// start tid
            for (int j = 0; j < p; j++) {   //k st remove, incr, add
                temp = tree.remove();
                temp += random.nextInt(90)+10;
                tree.add(temp);
            }
            long stop = System.nanoTime();// stop time
            times[i] = (stop-start);// add time
        }
        Arrays.sort(times);
        median = times[times.length/2];
        System.out.printf("\t0%.2f", median/p);

        for (int i = 0; i < k; i++) {
            tree = makeTree();
            long start = System.nanoTime();
            for (int j = 0; j < p; j++) {
                tree.push(random.nextInt(90)+10);
            }
            long stop = System.nanoTime();
            times[i] = (stop-start);
        }
        Arrays.sort(times);
        median = times[times.length/2];
        System.out.printf("\t0%.2f", median/p);

        Integer avrgDepth = 0;
        Integer depth = 0;
        for (int i = 0; i < k; i++) {
            tree = makeTree();
            for (int j = 0; j < p; j++) {
                depth += tree.push(random.nextInt(90)+10);
            }
            avrgDepth += depth/p;
            depth=0;
        }
        avrgDepth = avrgDepth/k;
        System.out.printf("\t%d\n", avrgDepth);

        }
        private static HeapTree makeTree(){
        HeapTree tree = new HeapTree();
        Random random = new Random();
            for (int i = 0; i < 1023; i++) {
                tree.add(random.nextInt(10000));
            }
        return tree;
    }
}
