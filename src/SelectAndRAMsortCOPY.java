/**
 * CSCI 333 Fall 2015, Professor Whitley
 * Homework 5: Select And RAM Sort
 * Created by Andrew Petriccione on 10/1/15.
 * The goal of this homework is to create a class with a method using the counting sort algorithm to sort an array,
 * and to also create a method using the randomizedQuickSelect algorithm to select digits of different specified
 * orders from an array.  To accomplish these goals we can use helper methods including a partition method,
 * a findMax method, a findMin method, and also an arrayPrinter method to help with testing.
 *
 */
import java.util.Arrays;
import java.util.Random;

/**
 * SelectAndRAMsort is a class that has methods to sort an array of integers using the countingsort algorithm,
 * and find integers of specified orders using the randomizedQuickSelect algorithm.
 */
public class SelectAndRAMsortCOPY {
    /**
     * partition is a helper method for the sorting methods quicksort and randomizedQuicksort which functions
     * to partition an array into smaller sub-arrays that can be sorted recursively.
     * @param arr The array to partition.
     * @param p   The starting point (lower bounds) index of the sub-array to partition.
     * @param r   The ending point (upper bounds) index of the sub-array to partition.
     * @return the index to be passed to the calling method
     */
    private static int partition(int[] arr, int p, int r) {
        int x = arr[r];
        int i = p - 1;
        for (int j = p;j < r;j++) {
            if (arr[j] <= x) {
                i++;
                int swap = arr[i];
                arr[i] = arr[j];
                arr[j] = swap;
            }
        }
        int swap2 = arr[i + 1];
        arr[i + 1] = arr[r];
        arr[r] = swap2;
        return i + 1;
    }
    /**
     * findMax is a helper method that finds the maximum integer in an array of integers.
     * @param arr  The array to find the maximum within.
     * @return  the maximum integer found within the array of integers.
     */
    public static int findMax(int[] arr) {
        int maxSoFar = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxSoFar) maxSoFar = arr[i];
        }
        return maxSoFar;
    }
    /**
     * findMin is a helper method that finds the minimum integer in an array of integers.
     * @param arr  The array to find the minimum within.
     * @return  the minimum integer found within the array of integers.
     */
    public static int findMin(int[] arr) {
        int minSoFar = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minSoFar) minSoFar = arr[i];
        }
        return minSoFar;
    }
    /**
     * arrayPrinter is a helper method to print the contents of an array starting at index 1.
     * @param arr The array to be printed.
     * @return a String that can be printed with System.out.print
     */
    public static String arrayPrinter(int[] arr){
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            result += "[" + arr[i] + "]";
        }
        return result;
    }
    /**
     * countingSort is a method that uses the counting sort algorithm to sort an array of integers.
     * @param A  The array to be sorted.
     * @param B  An empty array to copy the sorted contents of the 1st array A into.
     * @param largest  The largest digit in the array to be sorted.
     */
    public static void countingSort(int[] A,int[] B, int largest){
        int[] C = new int[largest + 1];
        for (int i = 0; i < C.length; i++) {
            C[i] = 0;
        }
        for (int i = 0; i < A.length; i++) {
            C[A[i]]++;
        }
        for (int i = 1; i < C.length; i++) {
            C[i] += C[i - 1];
        }
        for (int i = A.length - 1; i > -1; i--) {
            B[C[A[i]]-1] = A[i];
            C[A[i]]--;
        }
    }
    /**
     * randomizedQuickSelect is a method that can find the specified order digit within the given array of integers.
     * @param arr The array to select a digit of the specified order.
     * @param startAt  The inclusive lower bounds of the sub-array to select within.
     * @param endAt  The inclusive upper bounds of the sub-array to select within
     * @param i  The order digit to select.
     * @return  The digit to be passed to the calling method.
     */
    public static int randomizedQuickSelect(int[] arr, int startAt, int endAt, int i) {
        int[] aCopy = Arrays.copyOf(arr, arr.length);
        if (startAt == endAt) return aCopy[startAt];
        Random myRand = new Random();
        int z = myRand.nextInt(endAt - startAt + 1) + startAt;
        int swap = aCopy[z];
        aCopy[z] = aCopy[endAt];
        aCopy[endAt] = swap;
        int q = partition(aCopy, startAt, endAt);
        int k = q - startAt + 1;
        if (i == k) return aCopy[q];
        else if (i < k) return randomizedQuickSelect(aCopy, startAt, q - 1, i);
        else return randomizedQuickSelect(aCopy, q + 1, endAt, i - k);
    }
    public static void main(String[] args) {
        int[] arr1 = {333,21,441,14,77,11,13,101,7,2,17,1};
        int[] arr2 = new int[arr1.length];
        System.out.println("The input array arr1 looks like this:     " + arrayPrinter(arr1));
        countingSort(arr1, arr2, findMax(arr1));
        System.out.println("The output array arr2 looks like this:    " + arrayPrinter(arr2));
        System.out.println("The min is arr1 is: "+findMin(arr1));
        System.out.println("The max in arr1 is: " + findMax(arr1));
        System.out.println("The number that is of order " + 1 + " in this array is: ........."
                +randomizedQuickSelect(arr1, 0, arr1.length - 1, 1));
        System.out.println("The number that is of the median order in this array is: "
                +randomizedQuickSelect(arr1, 0, arr1.length - 1, (arr1.length)/2));
        System.out.println("The number that is of order " + arr1.length + " in this array is: ........"
                +randomizedQuickSelect(arr1, 0, arr1.length - 1, arr1.length));

        int[] arr3 = {33,16,8,9,11,24,16,22,18,4};
        int[] arr4 = new int[arr3.length];
        System.out.println("\nThe input array arr3 looks like this:     " + arrayPrinter(arr3));
        countingSort(arr3, arr4, findMax(arr3));
        System.out.println("The output array arr4 looks like this:    " + arrayPrinter(arr4));
        System.out.println("The min is arr3 is: "+findMin(arr3));
        System.out.println("The max in arr3 is: " + findMax(arr3));
        System.out.println("The number that is of order " + 1 + " in this array is: ........."
                +randomizedQuickSelect(arr3, 0, arr3.length - 1, 1));
        System.out.println("The number that is of the median order in this array is: "
                +randomizedQuickSelect(arr3, 0, arr3.length - 1, (arr3.length)/2));
        System.out.println("The number that is of order " + arr3.length + " in this array is: ........"
                +randomizedQuickSelect(arr3, 0, arr3.length - 1, arr3.length));

        int[] arr5 = {67,14,55,100,19,3,0,12,2,8};
        int[] arr6 = new int[arr5.length];
        System.out.println("\nThe input array arr5 looks like this:     " + arrayPrinter(arr5));
        countingSort(arr5, arr6, findMax(arr5));
        System.out.println("The output array arr6 looks like this:    " + arrayPrinter(arr6));
        System.out.println("The min is arr5 is: "+findMin(arr5));
        System.out.println("The max in arr5 is: " + findMax(arr5));
        System.out.println("The number that is of order " + 1 + " in this array is: ........."
                +randomizedQuickSelect(arr5, 0, arr5.length - 1, 1));
        System.out.println("The number that is of the median order in this array is: "
                +randomizedQuickSelect(arr5, 0, arr5.length - 1, (arr5.length)/2));
        System.out.println("The number that is of order " + arr5.length + " in this array is: ........"
                +randomizedQuickSelect(arr5, 0, arr5.length - 1, arr5.length));

        int[] arr7 = {3,2,1,0};
        int[] arr8 = new int[arr7.length];
        System.out.println("\nThe input array arr7 looks like this:     " + arrayPrinter(arr7));
        countingSort(arr7, arr8, findMax(arr7));
        System.out.println("The output array arr8 looks like this:    " + arrayPrinter(arr8));
        System.out.println("The min is arr7 is: "+findMin(arr7));
        System.out.println("The max in arr7 is: " + findMax(arr7));
        System.out.println("The number that is of order " + 1 + " in this array is: ........."
                +randomizedQuickSelect(arr7, 0, arr7.length - 1, 1));
        System.out.println("The number that is of the median order in this array is: "
                +randomizedQuickSelect(arr7, 0, arr7.length - 1, (arr7.length)/2));
        System.out.println("The number that is of order " + arr7.length + " in this array is: ........."
                +randomizedQuickSelect(arr7, 0, arr7.length - 1, arr7.length));

        int[] arr9 = {8,6,7,5,3,0,9};
        int[] arr10 = new int[arr9.length];
        System.out.println("\nThe input array arr9 looks like this:     " + arrayPrinter(arr9));
        countingSort(arr9, arr10, findMax(arr9));
        System.out.println("The output array arr10 looks like this:    " + arrayPrinter(arr10));
        System.out.println("The min is arr9 is: "+findMin(arr9));
        System.out.println("The max in arr9 is: " + findMax(arr9));
        System.out.println("The number that is of order " + 1 + " in this array is: ........."
                +randomizedQuickSelect(arr9, 0, arr9.length - 1, 1));
        System.out.println("The number that is of the median order in this array is: "
                +randomizedQuickSelect(arr9, 0, arr9.length - 1, (arr9.length)/2));
        System.out.println("The number that is of order " + arr9.length + " in this array is: ........."
                +randomizedQuickSelect(arr9, 0, arr9.length - 1, arr9.length));

    }

}