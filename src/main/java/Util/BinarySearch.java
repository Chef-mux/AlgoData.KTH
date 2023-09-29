package Util;

public class BinarySearch {


    public static boolean search(int[] array, int key) {
        int lowest = 0;
        int highest = array.length - 1;
        int mid = highest / 2;

        while(true) {
            while(array[mid] != key) {
                if (array[mid] >= key || mid >= highest) {
                    if (array[mid] <= key || mid <= lowest) {
                        return false;
                    }

                    highest = mid - 1;
                    mid = lowest + (highest - lowest) / 2;
                } else {
                    lowest = mid + 1;
                    mid = lowest + (highest - lowest) / 2;
                }
            }

            return true;
        }
    }

    public static int compare(int[] array1, int[] array2) {
        int counter = 0;

        label35:
        for(int i = 0; i < array1.length; ++i) {
            int lowestPossibleValue = 0;
            int highestPossibleValue = array2.length - 1;
            int mid = highestPossibleValue / 2;

            while(true) {
                while(array2[mid] != array1[i]) {
                    if (array2[mid] < array1[i] && mid < highestPossibleValue) {
                        lowestPossibleValue = mid + 1;
                        mid = lowestPossibleValue + (highestPossibleValue - lowestPossibleValue) / 2;
                    } else {
                        if (array2[mid] <= array1[i] || mid <= lowestPossibleValue) {
                            continue label35;
                        }

                        highestPossibleValue = mid - 1;
                        mid = lowestPossibleValue + (highestPossibleValue - lowestPossibleValue) / 2;
                    }
                }

                ++counter;
                break;
            }
        }

        return counter;
    }
}
