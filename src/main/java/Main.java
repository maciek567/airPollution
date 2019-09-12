import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/mainScreen.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane, 800, 480);

        primaryStage.setScene(scene);
        primaryStage.setWidth(860);
        primaryStage.setHeight(480);
        primaryStage.setTitle("Air pollution");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/img/icon.jpg"));
        primaryStage.show();
    }

}
