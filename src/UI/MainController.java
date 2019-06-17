package UI;

import UI.Customers.CustomerController;
import UI.Login.LoginManager;
import UI.Settings.SettingsController;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainController {

    // Tabs
    @FXML private TabPane tabPane;
    @FXML private Tab ordersTab;
    @FXML private Tab customersTab;
    @FXML private Tab couriersTab;
    @FXML private Tab orderTakersTab;
    @FXML private Tab mapTab;
    @FXML private Tab settingsTab;

    // Controllers
    @FXML private CustomerController ordersController;
    @FXML private CustomerController customersController;
    @FXML private CustomerController couriersController;
    @FXML private CustomerController orderTakerController;
    @FXML private CustomerController mapController;
    @FXML private CustomerController settingsController;

    // Button
    @FXML private Button logoutButton;

    private LoginManager loginManager;
    private Stage stage;

    public void init() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
            switch(newValue.toString()) {
                case "ordersTab":
                    break;
                case "customersTab":
                    break;
                case "couriersTab":
                    break;
                case "orderTakersTab":
                    break;
                case "mapTab":
                    break;
                case "settingsTab":
                    break;
            }
        });

        /**
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginManager.logout();
                stage.close();
            }
        });
         **/
    }
}