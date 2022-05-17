package atl.StibRide.view;

/**
 *
 * @author g55301
 */
import atl.StibRide.dto.StationsDto;
import atl.StibRide.exception.RepositoryException;
import atl.StibRide.model.PrintInformations;
import atl.StibRide.presenter.Presenter;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

/**
 * @author g55301
 */
public class MainViewController implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private SearchableComboBox<String> seachableOrigin;

    @FXML
    private SearchableComboBox<String> seachableDestination;
    @FXML
    private Button searchButton;

    @FXML
    private TableView<PrintInformations> table;

    @FXML
    private TableColumn<PrintInformations, String> stationsTable;

    @FXML
    private TableColumn<PrintInformations, String> linesTable;

    @FXML
    private TextField favoryTextField;
    

    @FXML
    private Button addFavory;

    @FXML
    private Button seeFavory;
    @FXML
    private Label numberOfStations;
private boolean langue=false;
private MenuItem nlStationsButton;
private MenuItem frStationsButton;
private Presenter presenter;

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }


    public MainViewController() {
    }

//    public MainViewController(String seachableOrigin, String seachableDestination) {
//        this.seachableOrigin.setValue(seachableOrigin);
//        this.seachableDestination.setValue(seachableDestination);
//    }

    private final ObservableList<String> seachableOriginGet
            = FXCollections.observableArrayList();
    private final ObservableList<String> seachableDestinationGet
            = FXCollections.observableArrayList();

       @Override
    public void initialize(URL url, ResourceBundle rb) {

        stationsTable = new TableColumn<>("STATIONS");
        linesTable = new TableColumn<>("LINES");
        table.getColumns().setAll(stationsTable, linesTable);
        stationsTable.setCellValueFactory(new PropertyValueFactory<>("station"));
        linesTable.setCellValueFactory(new PropertyValueFactory<>("line"));  
    }

    public void initialize(List<StationsDto> stations) {
        seachableOrigin.getItems().clear();
                seachableDestination.getItems().clear();
                seachableOriginGet.clear();
        seachableDestinationGet.clear();
   stations.stream().map((station) -> {
               seachableOriginGet.add(station.getName());
                return station;
            }).forEachOrdered((station) -> {
                seachableDestinationGet.add(station.getName());
            });
  
            seachableOrigin.setItems(seachableOriginGet);
            seachableDestination.setItems(seachableDestinationGet);
    }
    
    public void getNumberOfStations() {
       this.numberOfStations.setText("Number of stations "+table.getItems().size());
    }


    /**
     * button for research
     *
     */
    public void addHandlerButton() {           
        searchButton.setOnAction((ActionEvent t) -> {
            presenter.search(seachableOrigin.getValue(),seachableDestination.getValue());
            getNumberOfStations();
        });
    }
   
   public void addFavoryHandle() {
        
        addFavory.setOnAction((ActionEvent t) -> {
            
            try {
                presenter.addFavory(favoryTextField.getText(),seachableOrigin.getValue(),seachableDestination.getValue());
            } catch (RepositoryException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    

    /**
     * sets table after research
     *
     * @param tableResult given list of stations
     */
    public void setTable(List<PrintInformations> tableResult) {
            this.table.getItems().clear();
        tableResult.forEach(result -> this.table.getItems().add(result));
    }    
    
 @FXML
    void seeFavoryHandle(ActionEvent event) throws IOException {
        Stage favoryPage=(Stage)((Node)event.getSource()).getScene().getWindow();
FavoriteView fav=new FavoriteView();
        try {
            fav.start(favoryPage);
        } catch (Exception ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void nlStationsButton() {   
    
              langue=true;//eng
            try {
                presenter.changeLangue(langue);
            } catch (RepositoryException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

   if(table.getItems().size()>0){
      searchButton.setOnAction((ActionEvent t) -> {
            presenter.search(seachableOrigin.getValue(),seachableDestination.getValue());
            getNumberOfStations();
        });
      }
    }
   public void frStationsButton() {   

              langue=false;//francais
            try {
                presenter.changeLangue(langue);
                
            } catch (RepositoryException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
if(table.getItems().size()>0){
      searchButton.setOnAction((ActionEvent t) -> {
            presenter.search(seachableOrigin.getValue(),seachableDestination.getValue());
            getNumberOfStations();
        });
}
    }

}
