package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import parser.PollutionInfo;
import parser.PollutionInfoInternet;
import parser.index.Index;
import parser.index.IndexLevel;
import parser.PollutionInfoLocal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
public class PollutionIndexController {

    // To toggle between local and online version just change "Local" to "Internet" and vice versa during its initialization

    private MainController mainController;
    private MenuController menuController;
    private StationChoiceController stationController;
    private int stationNumber;
    @FXML private ProgressBar progressBar;
    @FXML private ProgressBar progressBarNO2;
    @FXML private ProgressBar progressBarSO2;
    @FXML private ProgressBar progressBarCO;
    @FXML private ProgressBar progressBarPM10;
    @FXML private ProgressBar progressBarPM25;
    @FXML private ProgressBar progressBarC6H6;
    @FXML private ProgressBar progressBarO3;
    @FXML private Label stationNameLabel;


    void init(int stationNumber) throws IOException {
        this.stationNumber = stationNumber;
        PollutionInfo pollutionInfo = new PollutionInfoInternet();
        String stationName = pollutionInfo.getStationNameByNumber(stationNumber);
        this.stationNameLabel.setText(new String(stationName.getBytes(sun.nio.cs.ISO_8859_2.defaultCharset()), StandardCharsets.UTF_8));
        Index index = pollutionInfo.getIndex(stationNumber);

        progressBar.setProgress(setValue(index.getStIndexLevel()));
        progressBarNO2.setProgress(setValue(index.getNo2IndexLevel()));
        progressBarSO2.setProgress(setValue(index.getSo2IndexLevel()));
        progressBarCO.setProgress(setValue(index.getCoIndexLevel()));
        progressBarPM10.setProgress(setValue(index.getPm10IndexLevel()));
        progressBarPM25.setProgress(setValue(index.getPm25IndexLevel()));
        progressBarC6H6.setProgress(setValue(index.getC6h6IndexLevel()));
        progressBarO3.setProgress(setValue(index.getO3IndexLevel()));

        setColor(progressBar);
        setColor(progressBarNO2);
        setColor(progressBarSO2);
        setColor(progressBarCO);
        setColor(progressBarPM10);
        setColor(progressBarPM25);
        setColor(progressBarC6H6);
        setColor(progressBarO3);
    }

    private double setValue(IndexLevel indexLevel) {
        if(indexLevel == null) return 0.0;
        else return ((indexLevel.getId()+1)/6.0);
    }

    private void setColor(ProgressBar progressBar) {
        if(progressBar.getProgress() <= 0.2)  progressBar.setStyle("-fx-accent: green");
        else if(progressBar.getProgress() <= 0.4) progressBar.setStyle("-fx-accent: #44FF00");
        else if(progressBar.getProgress() <= 0.5) progressBar.setStyle("-fx-accent: yellow");
        else if(progressBar.getProgress() <= 0.7) progressBar.setStyle("-fx-accent: #FF9900");
        else if(progressBar.getProgress() <= 0.85) progressBar.setStyle("-fx-accent: #FF4400");
        else progressBar.setStyle("-fx-accent: red");
    }


    // detailed information about pollution measured by particular sensor
    private void details(String parameter) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/detailedMeasurements.fxml"));
        AnchorPane anchorPane = loader.load();
        DetailedMeasurementsController detailedMeasurementsController = loader.getController();
        detailedMeasurementsController.init(this.stationNumber, parameter);
        detailedMeasurementsController.setMainController(mainController);
        detailedMeasurementsController.setStationController(this.stationController);
        mainController.setScreen(anchorPane);
    }
    public void detailsNO2() throws IOException { details("NO2"); }
    public void detailsSO2() throws IOException { details("SO2"); }
    public void detailsCO() throws IOException { details("CO"); }
    public void detailsPM10() throws IOException { details("PM10"); }
    public void detailsPM25() throws IOException { details("PM2.5"); }
    public void detailsC6H6() throws IOException { details("C6H6"); }
    public void detailsO3() throws IOException { details("O3"); }


    @FXML
    public void back() throws IOException {menuController.openApplication(); }
    public void backMenu() throws IOException { mainController.loadMenuScreen(); }

    void setMainController(MainController mainController) { this.mainController = mainController; }
    void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
    void setStationController(StationChoiceController stationChoiceController) {
        this.stationController = stationChoiceController;
    }
}
