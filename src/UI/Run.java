package UI;

import Models.Map;
import UI.Login.LoginManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Run extends Application {

    LoginManager loginManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Main is now in the LoginApp
        Scene scene = new Scene(new StackPane());


        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        scene.setRoot(loader.load());
        MainController controller = loader.getController();
        controller.init();

        primaryStage.setTitle("ACME Delivery Service");
        primaryStage.setScene(scene);
        primaryStage.show();

        Map map = new Map();
        map.calculate();
    }

    public static void main(String[] args) {
        launch(args);
    }
}