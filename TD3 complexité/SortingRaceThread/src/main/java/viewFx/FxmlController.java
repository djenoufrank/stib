/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewFx;

import controller.ModelController;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Informations;
import model.UsedThread;

/**
 * Represents the controlleur of the different element used in the view javaFx.
 *
 * @author 55301
 */
public class FxmlController implements Observer {

    @FXML
    private TableView<Informations> table;
    @FXML
    private TableColumn<Informations, String> nameCol;

    @FXML
    private TableColumn<Informations, Integer> sizeCol;

    @FXML
    private TableColumn<Informations, Integer> swapCol;

    @FXML
    private TableColumn<Informations, Integer> durationCol;
    @FXML
    private LineChart<Number, Number> chart;
    @FXML
    private NumberAxis sizeAxis;
    @FXML
    private NumberAxis operationAxis;
    private XYChart.Series mergePoints;
    private XYChart.Series bubblePoints;
    @FXML
    private Spinner<Integer> threadSpinner;
    @FXML
    private ChoiceBox<String> sortChoice;
    @FXML
    private ChoiceBox<String> configurationChoice;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button start;
    @FXML
    private Label leftStatus;
    @FXML
    private Label rightStatus;

    private ModelController controller;
    private int maxSizeArray;
    private int usedThreads;
    private String sortChoiceValue;
    private StartButtonFx startButton;
    private ObservableList<Informations> informations;
    private int scroleIndex = 0;

    /**
     * Simple constructor of FxmlController.
     */
    public FxmlController() {
        startButton = new StartButtonFx(this);
    }

    @FXML
    private void sortUpdate(ActionEvent event) {
        startButton.updateExecuteSort();
    }

    /**
     * simple getter of model Controller.
     *
     * @return the model controller.
     */
    public ModelController getModelController() {
        return this.controller;
    }

    /**
     * Simple getter of the maximum size of arrays to be sorted.
     *
     * @return the size of the array.
     */
    public int getMaxArraySize() {
        return maxSizeArray;
    }

    /**
     * Simple getter of number of used threads.
     *
     * @return the number of used threads.
     */
    public int getUsedThreads() {
        return usedThreads;
    }

    /**
     * Simple getter of the type of sort.
     *
     * @return the type of sort.
     */
    public String getSortChoiceValue() {
        return sortChoiceValue;
    }

    /**
     * Allows to initialize the view Fx with differents used components.
     */
    public void initialize() {

        this.controller = new ModelController(this);
        ObservableList<String> sortChoices = FXCollections.observableArrayList("Tri à bulle", "Tri à fusion");
        this.sortChoice.getItems().setAll(sortChoices);
        this.sortChoice.getSelectionModel().select(0);
        this.sortChoiceValue = this.sortChoice.getValue();

        SpinnerValueFactory<Integer> defaultInterval = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        this.threadSpinner.setValueFactory(defaultInterval);
        this.usedThreads = this.threadSpinner.getValue();

        ObservableList<String> configurationChoices = FXCollections.observableArrayList("Very easy : 0 - 100 - 10", "Easy : 0 - 1000 - 100", "Moderate : 0 - 10000 - 1000", "Hard : 0 - 100000 - 10000");
        this.configurationChoice.getItems().setAll(configurationChoices);
        this.configurationChoice.getSelectionModel().selectFirst();
        this.maxSizeArray = getMaxSizeArrayByUserSelection(this.configurationChoice.getValue());

        this.leftStatus.setText("Threads actifs: " + Thread.activeCount());
        this.nameCol.setCellValueFactory(new PropertyValueFactory<>("sortType"));
        this.sizeCol.setCellValueFactory(new PropertyValueFactory<>("sizeArray"));
        this.swapCol.setCellValueFactory(new PropertyValueFactory<>("operation"));
        this.durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));

        this.mergePoints = new XYChart.Series();
        this.bubblePoints = new XYChart.Series();
        this.mergePoints.setName(" Tri à fusion ");
        this.bubblePoints.setName(" Tri à bulles ");
    }

    /**
     * Converts the choice of maximum size of the array to a number.
     *
     * @param selection the choice of user.
     * @return the value of maximum of size of arrays to be sorted.
     */
    private int getMaxSizeArrayByUserSelection(String selection) {
        switch (selection) {

            case "Easy : 0 - 1000 - 100":
                return 1000;
            case "Moderate : 0 - 10000 - 1000":
                return 10000;
            case "Hard : 0 - 100000 - 10000":
                return 100000;
            default:
                return 100;
        }

    }

    /**
     * Updates the maximum size array.
     */
    public void updateMaxSizeArray() {
        this.maxSizeArray = getMaxSizeArrayByUserSelection(this.configurationChoice.getValue());
    }

    /**
     * Updates the number of threads to be used.
     */
    public void updateUsedThread() {
        this.usedThreads = this.threadSpinner.getValue();
    }

    /**
     * Updates the type of sort to be executed.
     */
    public void updatesortChoice() {
        this.sortChoiceValue = this.sortChoice.getValue();
    }

    /**
     * Updates the table view according to observable state.
     *
     * @param observable the given observable.
     */
    private void updateTableView(UsedThread observable) {
        this.table.getItems().add(new Informations(observable.getSortType(), observable.getArrayToSortSize(), observable.getOperations(), observable.getDuration()));
        scroleIndex++;
        this.table.scrollTo(scroleIndex);
    }

    /**
     * Updates informations in the labels according to observable state.
     *
     * @param observable the given observable.
     */
    private void updateLabelStatus(UsedThread observable) {
        this.leftStatus.setText("Threads actifs : " + ((Thread.activeCount() + this.usedThreads)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        if (observable.getArrayToSortSize() == this.maxSizeArray) {
            this.rightStatus.setText("Dernière Exécution | debut: " + UsedThread.firstDateStartAllThreads.format(formatter) + " - " + " fin: " + UsedThread.endDateStartAllThreads.format(formatter) + " Duration :" + Duration.between(UsedThread.firstDateStartAllThreads, UsedThread.endDateStartAllThreads).toMillis() + "ms");
        }
    }

    /**
     * Updates the line chart graph according to observable state.
     *
     * @param observable the given observable.
     */
    private void updateLineChart(UsedThread observable) {
        chart.setAnimated(false);
        XYChart.Data bubbleData;
        XYChart.Data mergeData;
        if (getSortChoiceValue().equals("Tri à bulle")) {
            bubbleData = new XYChart.Data(observable.getArrayToSortSize(), observable.getOperations());
            this.bubblePoints.getData().addAll(bubbleData);
        }
        if (getSortChoiceValue().equals("Tri à fusion")) {
            mergeData = new XYChart.Data(observable.getArrayToSortSize(), observable.getOperations());
            mergePoints.getData().addAll(mergeData);
        }

        if (!chart.getData().isEmpty()) {
            chart.getData().clear();
        }

        chart.getData().addAll(mergePoints, bubblePoints);
    }

    /**
     * Updates the progress bar state according to observable state.
     *
     * @param observable the given observable.
     */
    private void updateProgressBar(UsedThread observable) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateProgress(observable.getArrayToSortSize(), maxSizeArray);
                Thread.sleep(10);
                return null;
            }
        };
        this.progressBar.progressProperty().unbind();
        this.progressBar.progressProperty().bind(task.progressProperty());
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }

    @Override
    public void update(UsedThread observable) {
        updateTableView(observable);
        updateLabelStatus(observable);
        updateLineChart(observable);
        updateProgressBar(observable);
    }

}
