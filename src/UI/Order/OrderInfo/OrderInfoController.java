package UI.Order.OrderInfo;

import DataManagement.DatabaseManager;
import Models.CustomerOrder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.DateTimeStringConverter;
import java.text.SimpleDateFormat;
import javafx.scene.control.*;


import java.util.Date;

public class OrderInfoController {

    @FXML private Label orderTaker;
    @FXML private Label pickupCustomer;
    @FXML private Label deliveryCustomer;
    @FXML private Label pickupTime;
    @FXML private Label billTo;
    @FXML private Label packageID;
    @FXML private Label pickupAddress;
    @FXML private Label deliveryAddress;
    @FXML private Label orderStatus;
    @FXML private Label specialInstructions;
    @FXML private Label estimatedDelivery;
    @FXML private Label baseCharge;
    @FXML private Label total;
    @FXML private Label distance;
    @FXML private Label distanceCharge;
    @FXML private Label courier;
    @FXML private Label actualPickupTime;
    @FXML private Label deliveryTime;
    @FXML private Label departureTime;
    @FXML private Label title;

    private CustomerOrder order;

    public void initialize(){
        Platform.runLater(()->{
            if(order == null){
                Stage stage = (Stage)title.getScene().getWindow();
                stage.fireEvent(
                        new WindowEvent(
                                stage,
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        )
                );
                stage.close();
            }
            SetLabels();
        });
    }

    public void setOrder(CustomerOrder order){
        this.order = order;
    }

    private void SetLabels(){
        title.setText(
                "Order #" + order.getID() + " Information"
        );

        orderTaker.setText(
                (order.getOrderTaker() != null) ? order.getOrderTaker().getName() : NotFound()
        );

        pickupCustomer.setText(
                (order.getPickupCustomer() != null) ? order.getPickupCustomer().getName() : NotFound()
        );

        deliveryCustomer.setText(
                (order.getDeliveryCustomer() != null) ? order.getDeliveryCustomer().getName() : NotFound()
        );

        pickupTime.setText(
                (order.getPickupTime() != null) ? order.getPickupTime().toString() : NotFound()
        );

        billTo.setText(
                (order.isBillToDelivery()) ? "Delivery" : "Pickup"
        );

        packageID.setText(
                Integer.toString(order.getPackageID())
        );

        pickupAddress.setText(
                (order.getPickupCustomer() != null) ? order.getPickupCustomer().getAddress() : NotFound()
        );

        deliveryAddress.setText(
                (order.getDeliveryCustomer() != null) ? order.getDeliveryCustomer().getAddress() : NotFound()
        );

        String status;
        switch(order.getStatus().getValue()){
            case 0:
                status = "Awaiting Departure";
                break;
            case 1:
                status = "Out for Delivery";
                break;
            case 2:
                status = "Delivered";
                break;
            case 3:
                status = "Canceled";
                break;
            default:
                status = NotFound();
                break;
        }

        orderStatus.setText(
            status
        );

        specialInstructions.setText(
                (order.getSpecialInstructions() != null)? (order.getSpecialInstructions().equals(""))? order.getSpecialInstructions() : "None" : NotFound()
        );

        estimatedDelivery.setText(
                (order.getEstimatedDelivery() != null)? order.getEstimatedDelivery().toString() : NotFound()
        );

        baseCharge.setText(
                Float.toString(new DatabaseManager.QueryManager().getBusinessSettings().getDeliveryBase())
        );

        total.setText(
                Float.toString(order.getPrice())
        );

        distance.setText(
                Integer.toString(order.getDistance())
        );

        distanceCharge.setText(
                Float.toString(order.getDistance() * new DatabaseManager.QueryManager().getBusinessSettings().getDeliveryBlockRate())
        );

        courier.setText(
                (order.getCourier() != null) ? order.getCourier().getName() : "Unassigned"
        );

        actualPickupTime.setText(
                (order.getPickup() != null) ? order.getPickup().toString() : "Not Recorded"
        );

        deliveryTime.setText(
                (order.getDelivery() != null) ? order.getDelivery().toString() : "Not Recorded"
        );

        departureTime.setText(
                (order.getDeparture() != null) ? order.getDeparture().toString() : "Not Recorded"
        );
    }

    private String NotFound(){
        return "Error - not found";
    }

    @FXML
    public void close(){
        Stage stage = (Stage)title.getScene().getWindow();
        stage.close();
    }
}
