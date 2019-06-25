package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Run extends Application {
    private int user_id;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Main is now in the LoginApp
        Scene scene = new Scene(new StackPane());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        scene.setRoot(loader.load());
        MainController controller = loader.getController();
        controller.setUID(user_id);
        controller.init();

        primaryStage.setTitle("ACME Delivery Service");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setUID(int user_id){
        this.user_id = user_id;
    }

    public static void main(String[] args) {
        launch(args);
    }
}