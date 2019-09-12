package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;


public class MainController {

   @FXML private AnchorPane mainAnchorPane;

    @FXML void initialize() throws IOException {
        loadMenuScreen();
    }

    public void loadMenuScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/menuScreen.fxml"));
        AnchorPane anchorPaneMenu = loader.load();
        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        setScreen(anchorPaneMenu);
    }

    public void setScreen(Pane pane) {
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add(pane);
    }

}
