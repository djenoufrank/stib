/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import viewFx.FxmlController;
import viewFx.Observer;

/**
 * Represents the factory of threads used to exectute availables threads.
 *
 * @author 55301
 */
public class ThreadsRunner implements Observer {

    private FxmlController ctrlObserver;
    private List<UsedThread> threads;
    private String sortType;
    private int numberThreadsToRun;
    private int arrayMaxSize;
    private int arraySizeToSort;
    private int minimSize;

    /**
     * Simple constructor of ThreadsRunner.
     *
     * @param ctrlObserver the given observer.
     */
    public ThreadsRunner(FxmlController ctrlObserver) {
        this.ctrlObserver = ctrlObserver;
        this.arrayMaxSize = ctrlObserver.getMaxArraySize();
        this.sortType = ctrlObserver.getSortChoiceValue();
        numberThreadsToRun = ctrlObserver.getUsedThreads();
        this.threads = new ArrayList<>();
        this.executeThreads();
    }

    /**
     * Allows to execute threads.
     */
    private void executeThreads() {

        this.minimSize = (arrayMaxSize / 10);
        arraySizeToSort = minimSize;
        boolean firstSort = false;
        if (!firstSort) {
            this.threads.add(new UsedThread(ctrlObserver, this, 0 + "", 0));
            firstSort = true;
        }
        for (int i = 1; i < numberThreadsToRun; i++) {
            this.threads.add(new UsedThread(ctrlObserver, this, i + "", arraySizeToSort));
            arraySizeToSort += minimSize;
        }
        for (UsedThread thread : threads) {
            Thread t = new Thread(thread);
            t.start();
        }
    }

    @Override
    public void update(UsedThread observable) {
        if (arrayMaxSize >= arraySizeToSort) {
            for (UsedThread thread : threads) {
                if (thread.getThreadName().equals(observable.getThreadName())) {
                    thread.setArrayToSortSize(arraySizeToSort);
                    Thread t = new Thread(thread);
                    t.start();
                    arraySizeToSort += minimSize;
                }
            }
        }
    }

}
