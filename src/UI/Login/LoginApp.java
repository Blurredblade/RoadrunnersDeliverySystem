package UI.Login;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/** Main application class for the login demo application */
public class LoginApp extends Application {
    public static void main(String[] args) { launch(args); }
    @Override public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new StackPane());

        LoginManager loginManager = new LoginManager(scene, stage);
        loginManager.showLoginScreen();

        stage.setScene(scene);
        stage.show();
    }
}