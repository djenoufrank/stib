package atl.StibRide.presenter;

import atl.StibRide.dto.StationsDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.model.Model;
import atl.StibRide.view.MainViewController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import atl.StibRide.model.Research;
import atl.StibRide.pattern.Observable;
import atl.StibRide.pattern.Observer;
import atl.StibRide.repository.FavoryRepository;
import java.util.List;

/**
 * class presenter
 *
 * @author g55301
 */
public final class Presenter implements Observer {

    private final Model model;
    private final MainViewController view;

    /**
     * constructor of presenter
     *
     * @param model given model
     * @param view given view
     */
    public Presenter(Model model, MainViewController view) {
        this.model = model;
        this.view = view;
        try {
            view.initialize(initCombo());
        } catch (RepositoryException ex) {
            Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable observable, Object argument) {
        if (argument instanceof Research) {
            Model research = (Model) observable;
            view.setTable(research.getResearch().getTableResult());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("insertion");
            alert.setContentText("everything good\n");
            alert.showAndWait();
        }
    }

    /**
     * method to search lines and stations
     */
    public void search(String origine, String destination) {
        if (origine == null
                || destination == null
                || origine.
                        equals(destination)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("problem with source and destination");
            alert.setContentText("Something wrong with this entries .\n");
            alert.showAndWait();
        } else {

            this.model.compute(origine, destination);

        }
    }

    public List<StationsDto> initCombo() throws RepositoryException {
        return model.initStation();
    }

    public void addFavory(String favoryText, String origin, String destination) throws RepositoryException, IOException {
        if (!favoryText.isBlank() && origin != null && destination != null
                && !origin.equals(destination)) {
            model.insertion(favoryText, origin, destination);

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("problem with Name of favory or source"
                    + " and destination");
            alert.setContentText("Something wrong with this entries.");
            alert.showAndWait();
        }

    }

    public void changeLangue(boolean langue) throws RepositoryException {
        model.changeLangue(langue);
        try {
            view.initialize(initCombo());
        } catch (RepositoryException ex) {
            Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
