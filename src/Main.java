import UI.SettingsManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) { launch(args); }
    @Override public void start(Stage stage) {
        Scene scene = new Scene(new StackPane());
        SettingsManager settingsScreen = new SettingsManager(scene);
        settingsScreen.showSettingsScreen();

        stage.setTitle("ACME Delivery Service - System Settings");
        stage.setScene(scene);
        stage.show();
    }
}
