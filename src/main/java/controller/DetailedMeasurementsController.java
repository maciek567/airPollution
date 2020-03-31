package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import parser.PollutionInfo;
import parser.PollutionInfoInternet;
import parser.PollutionInfoLocal;
import parser.values.Value;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

public class DetailedMeasurementsController {

// To toggle between local and online version just change "Local" to "Internet" and vice versa during its initialization

    @FXML private Label stationNameLabelDetailed;
    @FXML private Label sensorNameLabel;
    @FXML private TableView<Value> detailedTable;
    @FXML private TableColumn<Value, Date> dateColumn;
    @FXML private TableColumn<Value, Double> valueColumn;
    @FXML private LineChart<String, Double> lineChart;

    private int stationNumber;
    private String sensorName;
    private PollutionInfo pollutionInfo;

    private MainController mainController;
    private StationChoiceController stationChoiceController;


    void init(int stationNumber, String sensorName) throws IOException {
        this.stationNumber = stationNumber;
        this.sensorName = sensorName;
        this.pollutionInfo = new PollutionInfoLocal();
        String stationName =  pollutionInfo.getStationNameByNumber(stationNumber);
        this.stationNameLabelDetailed.setText(new String(stationName.getBytes(sun.nio.cs.ISO_8859_2.defaultCharset()), StandardCharsets.UTF_8));
        this.sensorNameLabel.setText(sensorName);

        // set dates and values to table
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        try{
            detailedTable.setItems(getValues());
        } catch(IOException | RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Cannot get file with details. Station may not check this particular air pollution temporarily or does not check it at all.", ButtonType.OK);
            alert.showAndWait();
        }

        // set default sorting (ascending according to date)
        dateColumn.setSortType(TableColumn.SortType.DESCENDING);
        detailedTable.getSortOrder().add(dateColumn);

        // set call's background color based on pollution value
        valueColumn.setCellFactory(column -> new TableCell<Value, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (value == null || empty) {
                    setText(null);
                    setStyle("");

                } else {
                    setText(String.valueOf(Math.round(value*100.0)/100.0)); // show values rounded up to 2 decimal places

                    int airQualityLevel = -1;
                    switch(sensorName) {
                        case "NO2": airQualityLevel = no2Color(value); break;
                        case "SO2": airQualityLevel = so2Color(value); break;
                        case "CO": airQualityLevel = coColor(value); break;
                        case "PM10": airQualityLevel = pm10Color(value); break;
                        case "PM2.5": airQualityLevel = pm25Color(value); break;
                        case "C6H6": airQualityLevel = c6h6Color(value); break;
                        case "O3": airQualityLevel = o3Color(value); break;
                    }
                    switch(airQualityLevel) {
                        case 0:
                            setStyle("-fx-background-color: rgba(0, 255, 0, .3)"); // GREEN
                           // setTextFill(Color.YELLOW);
                            break;
                        case 1:
                            setStyle("-fx-background-color: rgba(178, 255, 102, .3)");
                            break;
                        case 2:
                            setStyle("-fx-background-color: rgba(255, 255, 0, .3)"); // YELLOW
                            break;
                        case 3:
                            setStyle("-fx-background-color: rgba(255, 178, 102, .3)");
                            break;
                        case 4:
                            setStyle("-fx-background-color: rgba(255, 102, 102, .3)");
                            break;
                        case 5:
                            setStyle("-fx-background-color: rgba(255, 0, 0, .3)"); // RED
                            break;
                    }
                }
            }
        });

        // display line chart
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        Calendar calendar = Calendar.getInstance();
        try {
            for(Value value : this.pollutionInfo.getSensorMeasurements(this.stationNumber, this.sensorName).getValues()) {
                calendar.setTime(value.getDate());
                if(value.getValue() != null ) {
                    series.getData().add(new XYChart.Data<>(calendar.get(Calendar.DAY_OF_MONTH) + "." +
                            calendar.get(Calendar.MONTH) + "  " + calendar.get(Calendar.HOUR_OF_DAY) + ":00", value.getValue()));
                }
            }
            lineChart.getData().add(series);
        } catch(RuntimeException e) {
            // simply do not add null value do line chart
        }
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.X_AXIS);
    }

    private int no2Color(Double value) {
        if(value <= 41) return 0;
        else if(value <= 101) return 1;
        else if(value <= 151) return 2;
        else if(value <= 201) return 3;
        else if(value <= 401) return 4;
        else return 5;
    }
    private int so2Color(Double value) {
        if(value <= 51) return 0;
        else if(value <= 101) return 1;
        else if(value <= 201) return 2;
        else if(value <= 351) return 3;
        else if(value <= 501) return 4;
        else return 5;
    }
    private int coColor(Double value) {
        if(value <= 3000) return 0;
        else if(value <= 7000) return 1;
        else if(value <= 11000) return 2;
        else if(value <= 15000) return 3;
        else if(value <= 21000) return 4;
        else return 5;
    }
    private int pm10Color(Double value) {
        if(value <= 21) return 0;
        else if(value <= 61) return 1;
        else if(value <= 101) return 2;
        else if(value <= 141) return 3;
        else if(value <= 201) return 4;
        else return 5;
    }
    private int pm25Color(Double value) {
        if(value <= 13) return 0;
        else if(value <= 37) return 1;
        else if(value <= 61) return 2;
        else if(value <= 85) return 3;
        else if(value <= 121) return 4;
        else return 5;
    }
    private int c6h6Color(Double value) {
        if(value <= 6) return 0;
        else if(value <= 11) return 1;
        else if(value <= 16) return 2;
        else if(value <= 21) return 3;
        else if(value <= 51) return 4;
        else return 5;
    }
    private int o3Color(Double value) {
        if(value <= 71) return 0;
        else if(value <= 121) return 1;
        else if(value <= 151) return 2;
        else if(value <= 181) return 3;
        else if(value <= 241) return 4;
        else return 5;
    }

    // return dates and values in form applicable to TableView
    public ObservableList<Value>getValues() throws IOException {
        ObservableList<Value> values = FXCollections.observableArrayList();
        values.addAll(this.pollutionInfo.getSensorMeasurements(this.stationNumber, this.sensorName).getValues());
        return values;
    }

    @FXML public void back() throws IOException {stationChoiceController.showPollutionIndex(stationNumber); }
    @FXML public void backMenu() throws IOException { mainController.loadMenuScreen(); }

    void setMainController(MainController mainController) { this.mainController = mainController; }
    void setStationController(StationChoiceController stationChoiceController) {
        this.stationChoiceController = stationChoiceController;
    }


}
