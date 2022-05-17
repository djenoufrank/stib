/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.StibRide.presenter;

import atl.StibRide.dto.FavoryDto;
import atl.StibRide.dto.StationsDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.model.Model;
import atl.StibRide.pattern.Observable;
import atl.StibRide.pattern.Observer;
import atl.StibRide.view.FavoriesController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author User
 */
public class FavoryPresenter implements Observer {

    private final Model model;
    private FavoriesController view;

    /**
     * constructor of presenter
     *
     * @param model given model
     * @param view given view
     */
    public FavoryPresenter(Model model, FavoriesController view) {
        this.model = model;
        this.view = view;
        try {
            view.initialize(initCombo(), initComboFav());
        } catch (RepositoryException ex) {
            Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable observable, Object argument) {
        if (argument instanceof List) {
            List<FavoryDto> favories = (List<FavoryDto>) argument;
            view.setTable(favories);
        }
    }

    public void modify(String favName, String origine, String destination) {
        if (favName == null || origine == null
                || destination == null
                || origine.
                        equals(destination)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("problem with source and destination");
            alert.setContentText("Something wrong with this entries .\n");
            alert.showAndWait();
        } else {
            this.model.update(favName, origine, destination);
        }
    }

    public void delete(String favorySelected) {
        this.model.delete(favorySelected);
    }

    public List<StationsDto> initCombo() throws RepositoryException {
        return model.initStation();
    }

    public List<FavoryDto> initComboFav() throws RepositoryException {
        return model.initFavory();
    }
}
