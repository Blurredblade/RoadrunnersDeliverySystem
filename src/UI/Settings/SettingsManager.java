package UI.Settings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsManager {
    private Scene scene;

    public SettingsManager(Scene scene) {
        this.scene = scene;
    }

    // Show the Settings Screen
    public void showSettingsScreen() {
        try {
            FXMLLoader loadSettings = new FXMLLoader(getClass().getResource("../Settings/settingsUI.fxml"));
            scene.setRoot(loadSettings.load());
        } catch (IOException ex) {
            Logger.getLogger(SettingsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Show the Business Report Generation Screen
    public void showBusinessReport() {
        try {
            FXMLLoader loadBusinessReport = new FXMLLoader(getClass().getResource("../Business/Report/businessReportUI.fxml"));
            scene.setRoot(loadBusinessReport.load());
        } catch (IOException ex) {
            Logger.getLogger(SettingsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
