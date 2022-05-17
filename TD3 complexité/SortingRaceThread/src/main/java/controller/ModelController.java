/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ThreadsRunner;
import viewFx.FxmlController;

/**
 * Represents the controller of the model.
 *
 * @author 55301
 */
public class ModelController {

    private ThreadsRunner threadsRunner;
    private FxmlController ctrl;

    /**
     * Simple constructor of model controller.
     *
     * @param ctrl
     */
    public ModelController(FxmlController ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * Allows to execute the sort.
     *
     * @param observer the given observer
     */
    public void executeSort(FxmlController observer) {
        this.threadsRunner = new ThreadsRunner(observer);
    }

}
