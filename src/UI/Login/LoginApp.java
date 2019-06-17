package UI.Login;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApp extends Application {
    public static void main(String[] args) { launch(args); }
    @Override public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new StackPane());

        LoginManager loginManager = new LoginManager(scene, stage);
        loginManager.showLoginScreen();

        stage.setTitle("ACME Delivery Service");
        stage.setScene(scene);
        stage.show();
    }
}
