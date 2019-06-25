package UI.Order;

import DataManagement.DatabaseManager;
import Models.CustomerOrder;
import UI.Login.LoginManager;
import UI.MainController;
import UI.Order.AssignCourier.AssignCourierController;
import UI.Order.NewOrder.NewOrderController;
import UI.Order.OrderInfo.OrderInfoController;
import UI.Order.RecordTimes.RecordTimesController;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import Models.OrderStatus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unused")
public class OrderController {
    @FXML private Button logoutButton;
    @FXML private Label sessionLabel;

    // Order Buttons
    @FXML private Button newOrderBtn;
    @FXML private Button orderInfoBtn;
    @FXML private Button assignCourierBtn;
    @FXML private Button printRouteBtn;
    @FXML private Button recordTimesBtn;
    @FXML private Button cancelOrderBtn;

    @FXML private ChoiceBox statusSelection;
    @FXML private VBox tableView;

    MainController parentController;

    public void initialize(){
        try {
            statusSelection.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent e) {
                    refreshTable();
                }
            });

            initOrderTable();

            getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel)->{
                if(newSel != null){
                    orderInfoBtn.disableProperty().setValue(false);
                    assignCourierBtn.disableProperty().setValue(false);
                    printRouteBtn.disableProperty().setValue(false);
                    recordTimesBtn.disableProperty().setValue(false);
                    cancelOrderBtn.disableProperty().setValue(false);
                }
                if(obs.getValue() != null) {
                    if (obs.getValue().getStatus() != OrderStatus.AWAITING_DEPARTURE) {
                        cancelOrderBtn.disableProperty().setValue(true);
                        assignCourierBtn.disableProperty().setValue(true);
                    }
                    if(obs.getValue().getCourier() == null){
                        recordTimesBtn.disableProperty().setValue(true);
                    }
                }
            });

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public TableView<CustomerOrder> getTable(){
        TableView<CustomerOrder> tv = null;

        for(Node node:(tableView.getChildren())){
            if(node instanceof TableView){
                tv = (TableView)node;
            }
        }

        return tv;
    }

    @FXML
    public void newOrder(){
        FXMLLoader newOrderPage = new FXMLLoader(getClass().getResource("../Order/NewOrder/newOrder.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - New Order");
            AnchorPane flowPane = newOrderPage.load();
            NewOrderController controller = newOrderPage.getController();
            controller.setUID(parentController.getUID());
            stage.setScene(new Scene(flowPane));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding((c)->
                    refreshTable()
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void info(){
        FXMLLoader orderInfoPage = new FXMLLoader(getClass().getResource("../Order/OrderInfo/orderInfo.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - Order Info");
            AnchorPane flowPane = orderInfoPage.load();
            OrderInfoController controller = orderInfoPage.getController();
            controller.setOrder(getTable().getSelectionModel().getSelectedItem());
            stage.setScene(new Scene(flowPane));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding((c)->
                refreshTable()
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void assignCourier(){
        FXMLLoader assignCourierPage = new FXMLLoader(getClass().getResource("../Order/AssignCourier/assignCourier.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - Assign Courier");
            AnchorPane flowPane = assignCourierPage.load();
            AssignCourierController controller = assignCourierPage.getController();
            controller.setOrder(getTable().getSelectionModel().getSelectedItem());
            stage.setScene(new Scene(flowPane));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding((c)->
                    refreshTable()
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void printRoute(){
        /* TODO */
        System.out.println("print route");
    }

    @FXML
    public void recordTimes(){
        FXMLLoader newOrderPage = new FXMLLoader(getClass().getResource("../Order/RecordTimes/recordTimes.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - Record Times");
            AnchorPane flowPane = newOrderPage.load();
            RecordTimesController controller = newOrderPage.getController();
            controller.setOrder(getTable().getSelectionModel().getSelectedItem());
            stage.setScene(new Scene(flowPane));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding((c)->
                    refreshTable()
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void cancelOrder(){
        int order_id = getTable().getSelectionModel().getSelectedItem().getID();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Order Cancellation");
        confirm.setContentText("Are you sure you want to cancel order #" + order_id + "?");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);
        confirm.setGraphic(null);
        Optional<ButtonType> result = confirm.showAndWait();
        if(result.get() == ButtonType.YES) {
            DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
            q.cancelOrder(order_id);
            refreshTable();
        }
    }

    @FXML
    private void refreshTable(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        ArrayList<CustomerOrder> orders = q.getOrders(statusSelection());

        getTable().getItems().clear();
        getTable().getItems().addAll(orders);
        orderInfoBtn.disableProperty().setValue(true);
        assignCourierBtn.disableProperty().setValue(true);
        printRouteBtn.disableProperty().setValue(true);
        recordTimesBtn.disableProperty().setValue(true);
        cancelOrderBtn.disableProperty().setValue(true);
    }

    private int statusSelection(){
        int stat;
        switch(statusSelection.getValue().toString()){
            case "None":
                stat = -1;
                break;
            case "Awaiting Departure":
                stat = 0;
                break;
            case "Out For Delivery":
                stat = 1;
                break;
            case "Delivered":
                stat = 2;
                break;
            case "Canceled":
                stat = 3;
                break;
            default:
                stat = -1;
                break;
        }
        return stat;
    }

    private void initOrderTable(){

        TableView<CustomerOrder> orderTable = new TableView<>();
        TableColumn<CustomerOrder, Integer> order_id = new TableColumn<>("Order ID");
        order_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerOrder, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<CustomerOrder, Integer> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getID());
            }
        });

        TableColumn<CustomerOrder, String> pickupCustomer = new TableColumn<>("Pickup Customer");
        pickupCustomer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerOrder, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getPickupCustomer().getName());
            }
        });

        TableColumn<CustomerOrder, String> deliveryCustomer = new TableColumn<>("Delivery Customer");
        deliveryCustomer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerOrder, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getDeliveryCustomer().getName());
            }
        });

        TableColumn<CustomerOrder, String> pickupTime = new TableColumn<>("Pickup Time");
        pickupTime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerOrder, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getPickupTime().toString().substring(0,5));
            }
        });

        TableColumn<CustomerOrder, String> orderTaker = new TableColumn<>("Order Taker");
        orderTaker.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerOrder, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getOrderTaker().getName());
            }
        });

        TableColumn<CustomerOrder, String> courier = new TableColumn<>("Courier");
        courier.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerOrder, String> param) {
                String courier;
                if(param.getValue().getCourier() == null){
                    courier = "Unassigned";
                }else{
                    courier = param.getValue().getCourier().getName();
                }
                return new ReadOnlyObjectWrapper<>(courier);
            }
        });

        TableColumn<CustomerOrder, String> estDelivery = new TableColumn<>("Estimated Delivery");
        estDelivery.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerOrder, String> param) {
                String delivery;
                if(param.getValue().getEstimatedDelivery() == null){
                    delivery = "Unknown";
                }else{
                    delivery = param.getValue().getEstimatedDelivery().toString().substring(0,5) + "";
                }
                return new ReadOnlyObjectWrapper<>(delivery);
            }
        });

        TableColumn<CustomerOrder, String> status = new TableColumn<>("Status");
        status.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerOrder, String> param) {
                String s;
                switch(param.getValue().getStatus().getValue()){
                    case 0:
                        s = "Awaiting Departure";
                        break;
                    case 1:
                        s = "Out for Delivery";
                        break;
                    case 2:
                        s = "Delivered";
                        break;
                    case 3:
                        s = "Canceled";
                        break;
                    default:
                        s = "";
                        break;
                }
                return new ReadOnlyObjectWrapper<>(s);
            }
        });

        orderTable.getColumns().addAll(order_id, pickupCustomer, deliveryCustomer, pickupTime, orderTaker, courier, estDelivery, status);

        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        int stat;
        switch(statusSelection.getValue().toString()){
            case "None":
                stat = -1;
                break;
            case "Awaiting Departure":
                stat = 0;
                break;
            case "Out For Delivery":
                stat = 1;
                break;
            case "Delivered":
                stat = 2;
                break;
            case "Canceled":
                stat = 3;
                break;
            default:
                stat = -1;
                break;
        }
        ArrayList<CustomerOrder> orders = q.getOrders(stat);

        orderTable.getItems().addAll(orders);

        tableView.getChildren().add(orderTable);
    }

    public void setParentController(MainController m){
        this.parentController = m;
    }
}
