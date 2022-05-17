/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Allows to sort given array.
 *
 * @author 55301
 */
public final class BubbleSort {

    private long countOperation;
    private int[] arr;

    /**
     * Simple constructor of BubbleSort.
     *
     * @param arrayToSort the given array to sort.
     * @param elementNumber the number of elements to Sort.
     */
    public BubbleSort(int[] arrayToSort, int elementNumber) {
        this.arr = this.optimizedBubbleSort(arrayToSort, elementNumber);

    }

    /**
     * Simple getter of array.
     *
     * @return the array.
     */
    public int[] getArr() {
        return this.arr;
    }

    /**
     * Allows to sort the given array.
     *
     * @param arr the given array.
     * @param elementNumber the given number of elements.
     * @return the array after sort.
     */
    private int[] optimizedBubbleSort(int[] arr, int elementNumber) {

        int i = 0, n = elementNumber;
        countOperation += 2;
        boolean swapNeeded = true;
        countOperation += 1;
        while (i < n - 1 && swapNeeded) {
            swapNeeded = false;
            countOperation += 1;
            for (int j = 1; j < n - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    swapNeeded = true;
                    countOperation += 8;
                }
            }
            if (!swapNeeded) {
                countOperation += 1;
                break;
            }
            i++;
            countOperation += 1;
        }
        return arr;
    }

    /**
     * Simple getter of the number of operations.
     *
     * @return the number of operations.
     */
    public long getCountOperation() {
        return countOperation;
    }

}
