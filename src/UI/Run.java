package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Run extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new StackPane());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        scene.setRoot(loader.load());
        MainController controller = loader.getController();
        controller.init();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}