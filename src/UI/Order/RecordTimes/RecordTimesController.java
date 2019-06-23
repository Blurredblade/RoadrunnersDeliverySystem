package UI.Order.RecordTimes;

import DataManagement.DatabaseManager;
import Models.CustomerOrder;
import Models.OrderStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DateTimeStringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import javafx.scene.control.*;


import java.util.Date;
import java.util.ResourceBundle;

public class RecordTimesController implements Initializable{

    @FXML private Button departureButton;
    @FXML private Button pickupButton;
    @FXML private Button deliveryButton;
    @FXML private TextField time;
    @FXML private CheckBox currTime;
    private CustomerOrder order;

    public void initialize(URL location, ResourceBundle resources){
        try{
            new TextFormatter<Date>(new DateTimeStringConverter());
            Date current = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
            time.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), current));
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void RefreshButtons(){
        if(order.getDeparture() == null){
            departureButton.disableProperty().setValue(false);
        }
        else{
            if(order.getPickup() == null){
                pickupButton.disableProperty().setValue(false);
            }else{
                if(order.getDelivery() == null){
                    deliveryButton.disableProperty().setValue(false);
                }
            }
        }

        if(order.getDeparture() != null && order.getPickup() != null && order.getDelivery() != null){
            departureButton.disableProperty().setValue(false);
            pickupButton.disableProperty().setValue(false);
            deliveryButton.disableProperty().setValue(false);
        }
    }

    public void setOrder(CustomerOrder order){
        this.order = order;
        RefreshButtons();
    }

    @FXML
    public void departure(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        order.setDeparture(getTime());
        order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
        q.updateOrder(order);
        RefreshButtons();
    }

    @FXML
    public void pickup(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        order.setPickup(getTime());
        q.updateOrder(order);
        RefreshButtons();
    }

    @FXML
    public void delivery(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        order.setDelivery(getTime());
        order.setStatus(OrderStatus.DELIVERED);
        q.updateOrder(order);
        RefreshButtons();
    }

    public Date getTime(){
        if(currTime.isSelected()){
            return new Date(System.currentTimeMillis());
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
            return format.parse(time.getText());
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return new Date(System.currentTimeMillis());
    }

    @FXML
    public void currTimeCheck(){
        if(currTime.isSelected()){
            time.disableProperty().setValue(true);
        }else{
            time.disableProperty().setValue(false);
        }
    }
}
