package UI.Settings;

/** TESTING
import DataManagement.DatabaseManager;
import Models.Business;
 **/
//import UI.BusinessReport.BusinessReportUIController;
import Models.DirectedGraph;
import Models.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    //@FXML private BusinessReportUIController customerController;
    @FXML private Tab settingsTab;
    @FXML private SettingsController settingsController;
    String filePath;

    FileChooser fileChooser = new FileChooser();

    public void initialize() {
        // Save Changes
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    saveChanges();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Select and Open a File
        openFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    openFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Generate Business Report
        generateButton.setOnAction(new EventHandler<>() {
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

        /** TESTING
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        Business b = q.getBusinessSettings();
         **/

        /** TESTING
        deliveryBaseField.setText(String.valueOf(b.getDeliveryBase()));
        deliveryBlockRateField.setText(String.valueOf(b.getDeliveryBlockRate()));
        bonusRateField.setText(String.valueOf(b.getBonusRate()));
        bonusGracePeriodField.setText(String.valueOf(b.getBonusGracePeriod()));
        addressField.setText(b.getAddress());

        q = null;**/
    }

    public void saveChanges() throws IOException {
        Map mapAPI = new Map();
        mapAPI.setFilePath(filePath);

        /**
        float deliveryBase = Float.parseFloat(deliveryBaseField.getText());
        float deliveryBlockRate = Float.parseFloat(deliveryBlockRateField.getText());
        float bonusRate = Float.parseFloat(bonusRateField.getText());
        int bonusGracePeriod = Integer.parseInt(bonusGracePeriodField.getText());
        String address = addressField.getText();
        **/
        String map = mapField.getText();
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.show();

        /** TESTING
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
         **/
    }

    public void openFile() throws IOException {
        System.out.println("\nOpening File");
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        filePath = selectedFile.getPath();
        mapField.setText(filePath);
    }
}