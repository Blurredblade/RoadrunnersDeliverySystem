package settings;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SettingsController {
    @FXML private TextField deliveryBaseField;
    @FXML private TextField deliveryBlockRateField;
    @FXML private TextField bonusRateField;
    @FXML private TextField bonusGracePeriodField;
    @FXML private TextField addressField;
    @FXML private TextField mapField;

    @FXML private Button logoutButton;
    @FXML private Button saveButton;
    @FXML private Button openFileButton;
    @FXML private Button generateButton;

    // Test
    @FXML private TabPane tabPane;
    @FXML private Tab customerTab;
    @FXML private BusinessReportUIController customerController;
    @FXML private Tab settingsTab;
    @FXML private SettingsController settingsController;

    FileChooser fileChooser = new FileChooser();

    public void initialize() {
        // Save Changes
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                saveChanges();
            }
        });

        // Select and Open a File
        openFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                openFile();
            }
        });

        // Generate Business Report
        generateButton.setOnAction(new EventHandler<>() {
            @Override public void handle(ActionEvent e) {
                FXMLLoader loadBusinessReport = new FXMLLoader(getClass().getResource("businessReportUI.fxml"));
                try {
                    Stage stage = new Stage();
                    stage.setTitle("ACME Delivery Service - Business Report UI");
                    stage.setScene(new Scene(loadBusinessReport.load()));
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Log Out Button
        logoutButton.setOnAction(new EventHandler<>() {
            @Override public void handle(ActionEvent e) {
                logout();
            }
        });
    }

    public void saveChanges() {
        String deliveryBase = deliveryBaseField.getText();
        String deliveryBlockRate = deliveryBlockRateField.getText();
        String bonusRate = bonusRateField.getText();
        String bonusGracePeriod = bonusGracePeriodField.getText();
        String address = addressField.getText();
        String map = mapField.getText();

        // For Testing
        System.out.println(
                "\ndelivery Base = " + deliveryBase +
                "\ndelivery block rate = " + deliveryBlockRate +
                "\nbonus rate = " + bonusRate +
                "\nbonus grace period = " + bonusGracePeriod +
                "\naddress = " + address +
                "\nmap = " + map
        );
    }

    public void openFile() {
        System.out.println("/nTEST: Opening File");
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        mapField.setText(selectedFile.getAbsolutePath());
    }

    public void logout() { System.out.println("/nTEST: User has logged out"); }

}