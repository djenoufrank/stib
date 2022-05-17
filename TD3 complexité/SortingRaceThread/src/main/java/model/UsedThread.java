/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.Duration;
import java.time.LocalDateTime;
import javafx.application.Platform;
import viewFx.FxmlController;

/**
 * Represent a thread which can be used to execute a specific operation.
 *
 * @author 55301
 */
public class UsedThread extends Observable implements Runnable {

    public static LocalDateTime firstDateStartAllThreads;
    public static LocalDateTime endDateStartAllThreads;
    private LocalDateTime startDateTimeCurrentThread;
    private LocalDateTime endDateTimeCurrentThreaad;
    private long duration;
    private String threadName;
    private String sortType;
    private int numberThreads;
    private int arrayToSortSize;
    private int arrayMaxSize;
    private int[] arrayTosort;
    private long operations;

    /**
     * Simple constructor of UsedThread.
     *
     * @param firstObserver the first observer.
     * @param secondObserver the second observer.
     * @param name the given name.
     * @param arrayToSortSize the given arrayToSortSize.
     */
    public UsedThread(FxmlController firstObserver, ThreadsRunner secondObserver, String name, int arrayToSortSize) {
        this.sortType = firstObserver.getSortChoiceValue();
        this.numberThreads = firstObserver.getUsedThreads();
        this.arrayToSortSize = arrayToSortSize;
        this.arrayMaxSize = firstObserver.getMaxArraySize();
        this.threadName = name;
        this.registerObserver(firstObserver);
        this.registerObserver(secondObserver);

    }

    /**
     * Simple getter of thread name.
     *
     * @return the thread name.
     */
    public String getThreadName() {
        return threadName;
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
     * @return the size of the array.
     */
    public int getArrayToSortSize() {
        return arrayToSortSize;
    }

    /**
     * Simple getter of number of operations.
     *
     * @return the number of operations.
     */
    public long getOperations() {
        return operations;
    }

    /**
     * Simple getter of the duration.
     *
     * @return the duration.
     */
    public long getDuration() {
        return this.duration;
    }

    /**
     * Simple getter of start date time of the current thread.
     *
     * @return the start date.
     */
    public LocalDateTime getStartDateTimeCurrentThread() {
        return startDateTimeCurrentThread;
    }

    /**
     * Simple getter of end date time of the current thread.
     *
     * @return the end date.
     */
    public LocalDateTime getEndDateTimeCurrentThreaad() {
        return endDateTimeCurrentThreaad;
    }

    /**
     * Simple setter of the size of the array.
     *
     * @param arrayToSortSize the given size of the array.
     */
    public void setArrayToSortSize(int arrayToSortSize) {
        this.arrayToSortSize = arrayToSortSize;
    }

    /**
     * Allows to execute the sort.
     */
    public synchronized void executeSort() {
        ArrayGenerator newArray = new ArrayGenerator(this.arrayToSortSize);
        this.arrayTosort = newArray.getArray();
        if (arrayToSortSize == 0) {
            this.firstDateStartAllThreads = LocalDateTime.now();
        }
        if (arrayToSortSize == arrayMaxSize) {
            this.endDateStartAllThreads = LocalDateTime.now();
        }
        this.startDateTimeCurrentThread = LocalDateTime.now();

        switch (this.sortType) {
            case "Tri à bulle":
                BubbleSort bubbleSort = new BubbleSort(arrayTosort, arrayToSortSize);
                this.operations = bubbleSort.getCountOperation();
                break;
            case "Tri à fusion":
                MergeSort mergeSort = new MergeSort(arrayTosort, arrayToSortSize);
                this.operations = mergeSort.getCountOperation();
                break;
        }

        this.endDateTimeCurrentThreaad = LocalDateTime.now();
        this.duration = Duration.between(this.startDateTimeCurrentThread, this.endDateTimeCurrentThreaad).toMillis();
        Platform.runLater(() -> {
            this.notifyObservers(this);
        });

    }

    @Override
    public void run() {
        executeSort();
    }

}
