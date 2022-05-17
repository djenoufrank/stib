/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atl.StibRide.view;

import atl.StibRide.model.Model;
import atl.StibRide.presenter.FavoryPresenter;
import atl.StibRide.presenter.Presenter;
import java.awt.Image;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author g55301
 */
public class FavoriteView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
         FXMLLoader root = new FXMLLoader(getClass()
                .getResource("/fxml/favories.fxml"));
        Scene scene = new Scene(root.load(),900,700);
        stage.setTitle("Stib Ride");
        stage.setScene(scene);
        stage.show();
        
           Model model = new Model();
        FavoriesController view = root.getController();
        FavoryPresenter presenter = new FavoryPresenter(model, view);
        view.setPresenter(presenter);
        model.addObserver(presenter);
        view.modifyHandle();
        view.deleteHandle();
    }

}
