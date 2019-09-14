package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;


public class MenuController {

    @FXML private MainController mainController;

    @FXML public void openApplication() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/stationChoice.fxml"));
        AnchorPane anchorPaneStations = loader.load();
        StationChoiceController stationChoiceController = loader.getController();
        stationChoiceController.setMainController(mainController);
        stationChoiceController.setMenuController(this);
        mainController.setScreen(anchorPaneStations);
    }

    @FXML public void exit() {
        Platform.exit();
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
