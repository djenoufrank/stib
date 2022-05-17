/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewFx;

/**
 * Represents the button which allows to start executing the application.
 *
 * @author 55301
 */
public class StartButtonFx {

    private FxmlController ctrl;

    /**
     * Smple constructor of startButtonFx
     *
     * @param ctrl
     */
    public StartButtonFx(FxmlController ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * Allows to update the execution of the sort.
     */
    public void updateExecuteSort() {
        ctrl.updateUsedThread();
        ctrl.updateMaxSizeArray();
        ctrl.updatesortChoice();
        ctrl.getModelController().executeSort(ctrl);
    }

}
