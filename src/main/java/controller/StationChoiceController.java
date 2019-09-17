package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import java.io.IOException;


public class StationChoiceController {

    @FXML private MainController mainController;
    @FXML private MenuController menuController;

    public void chosenKrasinskiego() throws IOException { showPollutionIndex(400); } // "Kraków, Aleja Krasińskiego"
    public void chosenBujaka() throws IOException { showPollutionIndex(401); } // "Krakow, ul. Bujaka"
    public void chosenBulwarowa() throws IOException { showPollutionIndex(402); } // "Kraków, ul. Bulwarowa"
    public void chosenDietla() throws IOException { showPollutionIndex(10121); } // "Kraków, ul. Dietla"
    public void chosenZlotyRog() throws IOException { showPollutionIndex(10123); } // "Kraków, ul. Złoty Róg"
    public void chosenPiastow() throws IOException { showPollutionIndex(10139); } // "Kraków, os. Piastów"
    public void chosenWadow() throws IOException { showPollutionIndex(10447); } // "Kraków, os. Wadów"

    public void showPollutionIndex(int station) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/pollutionIndex.fxml"));

        try{
            GridPane gridPane = loader.load();
            PollutionIndexController pollutionIndexController = loader.getController();
            pollutionIndexController.init(station);
            pollutionIndexController.setMainController(mainController);
            pollutionIndexController.setMenuController(menuController);
            pollutionIndexController.setStationController(this);
            mainController.setScreen(gridPane);
        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot get file with air index on this station. Check Internet connection.", ButtonType.OK);
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    public void backMenu() throws IOException { mainController.loadMenuScreen(); }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

}
