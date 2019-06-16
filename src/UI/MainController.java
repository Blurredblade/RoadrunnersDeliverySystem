package UI;

import UI.Customers.CustomerController;
import UI.Settings.SettingsController;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController {

    // Tabs
    @FXML private TabPane tabPane;
    @FXML private Tab ordersTab;
    @FXML private Tab customersTab;
    @FXML private Tab couriersTab;
    @FXML private Tab orderTakersTab;
    @FXML private Tab mapTab;
    @FXML private Tab settingsTab;

    @FXML private Tab fooTab;
    @FXML private Tab barTab;

    // Controllers
    @FXML private CustomerController ordersController;
    @FXML private CustomerController customersController;
    @FXML private CustomerController couriersController;
    @FXML private CustomerController orderTakerController;
    @FXML private CustomerController mapController;
    @FXML private CustomerController settingsController;

    @FXML private CustomerController fooTabPageController;
    @FXML private SettingsController barTabPageController;


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
                case "barTab":
                    barTabPageController.handleButton();
                    break;
                case "fooTab":
                    fooTabPageController.handleButton();
                    break;
            }
        });
    }
}