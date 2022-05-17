/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Allows to generate arrays.
 *
 * @author 55301
 */
public class ArrayGenerator {
    /**
     * Generates array according to the given size
     *
     * @param countElement the given size of element of the array.
     * @return the array.
     */
    private static int[] generateArray(int countElement) {
        int[] array;
        if (countElement == 0) {
            array = new int[0];
        } else {
            array = new int[countElement];
        }

        for (int i = 0; i < countElement; i++) {
            if (i % 2 == 0) {
                array[i] = i + 1 * 15;
            } else {
                array[i] = i * 6 + 2;
            }
        }
        return array;
    }

    private int[] array;

    /**
     * Simple constructor of ArrayGenerator.
     *
     * @param countElement the given number of elements.
     */
    public ArrayGenerator(int countElement) {
        this.array = generateArray(countElement);
    }


    /**
     * Simple getter of the array.
     *
     * @return the array.
     */
    public int[] getArray() {
        return array;
    }

}
