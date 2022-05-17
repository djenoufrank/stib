package atl.StibRide.view;

/**
 *
 * @author g55301
 */
import atl.StibRide.config.ConfigManager;
import atl.StibRide.dto.FavoryDto;
import atl.StibRide.dto.StationsDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.model.Model;
import atl.StibRide.model.PrintInformations;
import atl.StibRide.presenter.FavoryPresenter;
import atl.StibRide.presenter.Presenter;
import atl.StibRide.repository.FavoryRepository;
import atl.StibRide.repository.StationsRepository;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

public class FavoriesController implements Initializable {

 @FXML
    private TableView<FavoryDto> table;

    @FXML
    private TableColumn<FavoryDto, String> name;

    @FXML
    private TableColumn<FavoryDto, String> source;

    @FXML
    private TableColumn<FavoryDto, String> destination;

    @FXML
    private Button modify;

    @FXML
    private Button delete;
    @FXML
    private SearchableComboBox<String> seachableDestination;

    @FXML
    private SearchableComboBox<String> seachableFavory;

    @FXML
    private SearchableComboBox<String> seachableOrigin;

    @FXML
    private Button backToStartPage;

    FavoryDto favorySelected;    
private FavoryPresenter presenter;

    public void setPresenter(FavoryPresenter presenter) {
        this.presenter = presenter;
    }
    /**
     * default construtor of second view
     */
    public FavoriesController() {     
        
    }
    private final ObservableList<String> seachableFavoryGet
            = FXCollections.observableArrayList();
    private final ObservableList<String> seachableOriginGet
            = FXCollections.observableArrayList();
    private final ObservableList<String> seachableDestinationGet
            = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        favorySelected=null;
        name = new TableColumn<>("Name");
        source = new TableColumn<>("Sources");
        destination = new TableColumn<>("Destinations");
        table.getColumns().setAll(name, source, destination);
        name.setCellValueFactory(new PropertyValueFactory<>("key"));
        source.setCellValueFactory(new PropertyValueFactory<>("source"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
 
        

        try {
            ConfigManager.getInstance().load();
            FavoryRepository favory = new FavoryRepository();

            List<FavoryDto> listFav = favory.getAll();
            for (FavoryDto favoryDto : listFav) {
seachableFavoryGet.add(favoryDto.getKey());
                table.getItems().add(favoryDto);
            }
            StationsRepository demo=new StationsRepository();
            List<StationsDto> stations = demo.getAll();
            for (StationsDto station : stations) {
                
                seachableOriginGet.add(station.getName());
                seachableDestinationGet.add(station.getName());
            }
                 seachableFavory.setItems(seachableFavoryGet);
        seachableOrigin.setItems(seachableOriginGet);
            seachableDestination.setItems(seachableDestinationGet);
        } catch (RepositoryException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(FavoriesController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
   

    }

    /**
     * delete favory in database
     *
     * @param event click on delete button
     * @throws RepositoryException
     */
    void deleteHandle() {
              delete.setOnAction((ActionEvent t) -> {
                
            presenter.delete(seachableFavory.getValue());
       });
    }



    /**
     * butoon to go to start page
     * @param event click on startPage button
     * @throws RepositoryException 
     */
    @FXML
    void startPageHandle(ActionEvent event) throws RepositoryException, IOException {
              Stage mainPage=(Stage)((Node)event.getSource()).getScene().getWindow();
MainApp main=new MainApp();
        try {
            main.start(mainPage);
        } catch (Exception ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    /**
     * updates and restart table
     */
    private void updateTable() {
        table.getItems().clear();
        name = new TableColumn<>("Name");
        source = new TableColumn<>("Sources");
        destination = new TableColumn<>("Destinations");
        table.getColumns().setAll(name, source, destination);
        name.setCellValueFactory(new PropertyValueFactory<>("key"));
        source.setCellValueFactory(new PropertyValueFactory<>("source"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
       List<FavoryDto> listFav =null;
               try {
            ConfigManager.getInstance().load();
            FavoryRepository favory = new FavoryRepository();
            listFav = favory.getAll();
        } catch (RepositoryException ex) {
        } catch (IOException ex) {
            Logger.getLogger(FavoriesController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
         for (FavoryDto favoryDto : listFav) {

                table.getItems().add(favoryDto);
            }
         
    }

    public void modifyHandle() {
           modify.setOnAction((ActionEvent t) -> {
            presenter.modify(seachableFavory.getValue(),seachableOrigin.getValue(),seachableDestination.getValue());
        });

    }


    public void setTable() {
updateTable(); 
    }
}
