/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Allows to sort the given array.
 *
 * @author 55301
 */
public final class MergeSort {

    private long countOperation;

    /**
     * Simple constructor of MergeSort.
     *
     * @param arrayToSort the array to sort.
     * @param elementNumber the number of elements to sort.
     */
    public MergeSort(int[] arrayToSort, int elementNumber) {

        this.mergeSort(arrayToSort, elementNumber);
    }

    /**
     * Allows to merge arrays according to directions.
     *
     * @param a the array to be merged.
     * @param l the array to be merged.
     * @param r the array to be merged.
     * @param left the direction of merge.
     * @param right the direction of merge.
     */
    private void merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        countOperation += 3;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
                countOperation += 4;
            } else {
                a[k++] = r[j++];
                countOperation += 3;
            }
        }
        while (i < left) {
            a[k++] = l[i++];
            countOperation += 4;
        }
        while (j < right) {
            a[k++] = r[j++];
            countOperation += 4;
        }

    }

    /**
     * Simple getter of the number of operations.
     *
     * @return the number of operations.
     */
    public long getCountOperation() {
        return countOperation;
    }

    /**
     * Allows to sort array.
     *
     * @param a the array to sort
     * @param n the number of element to sort.
     */
    public void mergeSort(int[] a, int n) {
        if (n < 2) {
            countOperation += 1;
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        countOperation += 3;

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            countOperation += 1;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
            countOperation += 1;
        }
        mergeSort(l, mid);
        countOperation += 1;
        mergeSort(r, n - mid);
        countOperation += 1;

        merge(a, l, r, mid, n - mid);
        countOperation += 1;

    }

}
