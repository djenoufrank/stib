/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import viewFx.Observer;

/**
 * Represents the object to be observed.
 *
 * @author 55301
 */
public class Observable {

    List<Observer> observers = new ArrayList<>();

    /**
     * Allows to register observer to observe the observable.
     *
     * @param observer the given observer.
     */
    public void registerObserver(Observer observer) {
        if (!this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    /**
     * Removes observer from the list of observers.
     *
     * @param observer the given observer.
     */
    public void removeObserver(Observer observer) {
        if (this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    /**
     * Allows to notify all observers of the new state of observable.
     *
     * @param observable the given observable.
     */
    public void notifyObservers(Observable observable) {
        for (Observer obs : this.observers) {
            obs.update((UsedThread) observable);
        }
    }

}
