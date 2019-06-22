package UI.Order;

import DataManagement.DatabaseManager;
import Models.CustomerOrder;
import UI.Customers.CustomerController;
import UI.Login.LoginManager;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class OrderController extends Application {
    @FXML private Button logoutButton;
    @FXML private Label sessionLabel;

    private LoginManager loginManager;
    private Stage stage;

    // Order Buttons
    @FXML private Button newOrderBtn;
    @FXML private Button orderInfoBtn;
    @FXML private Button assignCourierBtn;
    @FXML private Button printRouteBtn;
    @FXML private Button recordTimesBtn;
    @FXML private Button cancelOrderBtn;

    @FXML private TableView orderTable;
    public Connection con;
    private Logger logger;
    private DatabaseManager objDbClass;

    @FXML public TableColumn colOrderNum;
    @FXML public TableColumn colDeliveryCust;
    @FXML public TableColumn colPickUpCust;
    @FXML public TableColumn colPickUpTime;
    @FXML public TableColumn colPickCourier;
    @FXML public TableColumn colEstDepart;

    @FXML
    void initialize(){

        /*
        assert orderTable != null : "fx:id=\"tableview\" was not injected: check your FXML file 'order.fxml'.";
        // Need to work on the order number col later
        colOrderNum.setCellValueFactory( new PropertyValueFactory<CustomerOrder,Integer>("orderID"));
        colDeliveryCust.setCellValueFactory( new PropertyValueFactory<CustomerOrder, Integer>("deliveryCustID"));
        colPickUpCust.setCellValueFactory( new PropertyValueFactory<CustomerOrder, Integer>("pickUpCustID"));
        //colPickUpTime.setCellValueFactory( new PropertyValueFactory<CustomerOrder,String>("pickUpTime"));
        colPickCourier.setCellValueFactory( new PropertyValueFactory<CustomerOrder,Integer>("courierID"));
        //colEstDepart.setCellValueFactory( new PropertyValueFactory<CustomerOrder,String>("estDepart"));

        objDbClass = new DatabaseManager();
        try{
            con = objDbClass.getConnection();
            buildData();
        }
        catch(ClassNotFoundException ce){ logger.info(ce.toString()); }
        catch(SQLException ce){ logger.info(ce.toString()); }
    }

    private ObservableList<CustomerOrder> data;
    public void buildData(){
        data = FXCollections.observableArrayList();
        try{
            String SQL = "Select * from customerorder Order By order_id";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()){
                CustomerOrder cm = new CustomerOrder();
                cm.orderID.set(rs.getInt("order_id"));
                cm.deliveryCustID.set(rs.getInt("delivery_customer_id"));
                cm.pickUpCustID.set(rs.getInt("pickup_customer_id"));
                //cm.pickUpTime.set(rs.getString("pick_up_time"));
                cm.courierID.set(rs.getInt("courier_id"));
                //cm.estDepart.set(rs.getString("estimated_departure"));
                cm.test();
                data.add(cm);
            }
            orderTable.setItems(data);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

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
                settingsTab.setContent(settingsTabVBox);
                tabPane.getTabs().add(settingsTab);
            }
        }

        orderLayout.getChildren().addAll(tabPane, logoutButton);
        // create a scene
        Scene scene = new Scene(orderLayout, 600, 500);
        // set the scene
        stage.setScene(scene);

         */
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
    }


}
