package UI;

import DataManagement.DatabaseManager;
import UI.Customers.CustomerController;
import UI.Login.LoginManager;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.logging.Logger;

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

    @FXML private Label sessionLabel;

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
    void init(){

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


        /*

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginManager.logout();
                stage.close();
            }
        });


         */


    }
}