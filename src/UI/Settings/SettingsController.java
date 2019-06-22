package UI.Settings;


import DataManagement.DatabaseManager;
import Models.Business;
import UI.BusinessReport.BusinessReportUIController;
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
    public void handleButton() {
        // Testing
        }

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
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                FXMLLoader loadBusinessReport = new FXMLLoader(getClass().getResource("../BusinessReport/businessReportUI.fxml"));
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
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                logout();
            }
        });

        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        Business b = q.getBusinessSettings();

        //TEST
        /**
        deliveryBaseField.setText(String.valueOf(b.getDeliveryBase()));
        deliveryBlockRateField.setText(String.valueOf(b.getDeliveryBlockRate()));
        bonusRateField.setText(String.valueOf(b.getBonusRate()));
        bonusGracePeriodField.setText(String.valueOf(b.getBonusGracePeriod()));
        addressField.setText(b.getAddress());
         **/
        q = null;
    }

    public void saveChanges() {
        float deliveryBase = Float.parseFloat(deliveryBaseField.getText());
        float deliveryBlockRate = Float.parseFloat(deliveryBlockRateField.getText());
        float bonusRate = Float.parseFloat(bonusRateField.getText());
        int bonusGracePeriod = Integer.parseInt(bonusGracePeriodField.getText());
        String address = addressField.getText();
        String map = mapField.getText();

        Business b = new Business(
                address,
                deliveryBlockRate,
                deliveryBase,
                bonusRate,
                bonusGracePeriod
        );

        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        q.setBusinessSettings(b);
        q = null;
    }

    public void openFile() {
        System.out.println("/nTEST: Opening File");
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        mapField.setText(selectedFile.getAbsolutePath());
    }

    public void logout() { System.out.println("/nTEST: User has logged out"); }

}