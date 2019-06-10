package src.sample;

import DataManagement.DatabaseManager;
import UI.SettingsManager;
import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import Models.CustomerOrder;
import java.util.ArrayList;

public class OrderController extends Application {

    @FXML private Button logoutButton;
    @FXML private Label sessionLabel;

    // Order Buttons
    @FXML private Button newOrderBtn;
    @FXML private Button orderInfoBtn;
    @FXML private Button assignCourierBtn;
    @FXML private Button printRouteBtn;
    @FXML private Button recordTimesBtn;
    @FXML private Button cancelOrderBtn;
    @FXML private TableView OrderTable;

    public void initSessionID(final LoginManager loginManager, String sessionID) {

        sessionLabel.setText(sessionID);

        Stage stage = new Stage();

        // set title for the stage
        stage.setTitle("ACME Delivery Service");

        // create a tabpane
        TabPane tabPane = new TabPane();

        VBox orderLayout = new VBox(20);

        // create multiple tabs
        for (int tabCount = 0; tabCount < 7; tabCount++) {

            logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loginManager.logout();
                    stage.close();
                }
            });

            if (tabCount == 1) {

                Tab ordersTab = new Tab("Orders");

                DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();

                ArrayList<CustomerOrder> orders = q.getOrders();
                OrderTable = new TableView();
                TableColumn ordernumber = new TableColumn("Order Number");

                for(CustomerOrder o:orders){

                }

                VBox orderTabVBox = new VBox();
                orderTabVBox.getChildren().addAll(

                        newOrderBtn,
                        orderInfoBtn,
                        assignCourierBtn,
                        printRouteBtn,
                        recordTimesBtn,
                        cancelOrderBtn

                );

                ordersTab.setContent(orderTabVBox);
                tabPane.getTabs().add(ordersTab);

            } else if (tabCount == 2) {

                Tab customersTab = new Tab("Customers");

                VBox customerTabVBox = new VBox();
                customerTabVBox.getChildren().addAll(

                        new Button("New Customer"),
                        new Button("Edit Customer"),
                        new Button("Delete Customer"),
                        new Button("Generate Bills"),
                        new Button("Generate All Bills")
                );

                customersTab.setContent(customerTabVBox);
                tabPane.getTabs().add(customersTab);

            } else if (tabCount == 3) {

                Tab couriersTab = new Tab("Couriers");

                VBox courierTabVBox = new VBox();
                courierTabVBox.getChildren().addAll(

                        new Button("New Courier"),
                        new Button("Edit Courier"),
                        new Button("Delete Courier"),
                        new Button("Get Report")

                );

                couriersTab.setContent(courierTabVBox);
                tabPane.getTabs().add(couriersTab);

            } else if (tabCount == 4) {

                Tab orderTakersTab = new Tab("Order Takers");

                VBox orderTakerTabVBox = new VBox();
                orderTakerTabVBox.getChildren().addAll(

                        new Button("Add Order Taker"),
                        new Button("Edit Order Taker"),
                        new Button("Delete Order Taker")

                );

                orderTakersTab.setContent(orderTakerTabVBox);
                tabPane.getTabs().add(orderTakersTab);

            } else if (tabCount == 5) {

                Tab mapTab = new Tab("Map");

                VBox mapTabVBox = new VBox();
                mapTabVBox.getChildren().addAll(

                        new Button("Mark Open"),
                        new Button("Mark Closed")

                );

                mapTab.setContent(mapTabVBox);
                tabPane.getTabs().add(mapTab);

            } else if (tabCount == 6) {

                Tab settingsTab = new Tab("Settings");

                VBox settingsTabVBox = new VBox();
                settingsTabVBox.getChildren().addAll(

                        new Label("System Settings"),
                        new Label("Base Price"),
                        new Button("Save"),
                        new Button("Generate Business Report"),
                        new Button("Open File")

                );

                stage.setTitle("ACME Delivery Service - System Settings");

                settingsTab.setContent(settingsTabVBox);
                tabPane.getTabs().add(settingsTab);

            }
        }

        orderLayout.getChildren().addAll(tabPane, logoutButton);

        // create a scene
        Scene scene = new Scene(orderLayout, 600, 500);


        // set the scene
        stage.setScene(scene);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
