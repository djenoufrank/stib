/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewFx;

import model.UsedThread;

/**
 * Represents the observers.
 *
 * @author 55301
 */
public interface Observer {

    /**
     * Allows to update the state of observer according to the state of
     * observable.
     *
     * @param observable the given observable.
     */
    public void update(UsedThread observable);

}
