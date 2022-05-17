/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Represents the defferents informations about the execution of threads.
 *
 * @author 55301
 */
public class Informations {

    private String sortType;
    private int sizeArray;
    private long duration;
    private long operation;

    /**
     * Simple constructor of Informations.
     *
     * @param sortType the given sort type.
     * @param arraySize the given array size.
     * @param operation the given number of operations.
     * @param duration the given duration.
     */
    public Informations(String sortType, int arraySize, long operation, long duration) {
        this.sortType = sortType;
        this.sizeArray = arraySize;
        this.duration = duration;
        this.operation = operation;
    }

    /**
     * Simple getter of sort type.
     *
     * @return the type of sort.
     */
    public String getSortType() {
        return sortType;
    }

    /**
     * Simple getter of the size of the array.
     *
     * @return array size.
     */
    public int getSizeArray() {
        return sizeArray;
    }

    /**
     * Simple getter of the duration.
     *
     * @return the duration.
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Simple getter of the number of operation.
     *
     * @return the number of operation.
     */
    public long getOperation() {
        return operation;
    }

}
