package atl.StibRide.view;

import atl.StibRide.exception.RepositoryException;
import atl.StibRide.model.Model;
import atl.StibRide.presenter.Presenter;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * class which contain main to load app
 *
 * @author g55301
 */
public class MainApp extends Application {
 
     @Override
    public void start(Stage stage) throws IOException, RepositoryException {
        FXMLLoader root = new FXMLLoader(getClass()
                .getResource("/fxml/scene.fxml"));
        Scene scene = new Scene(root.load(),900,700);
        stage.setTitle("Stib Ride");
        stage.setScene(scene);
        stage.show();

        Model model = new Model();
        MainViewController view = root.getController();
        Presenter presenter = new Presenter(model, view);
        view.setPresenter(presenter);
        model.addObserver(presenter);
        view.addHandlerButton();
        view.addFavoryHandle();
    }
}

