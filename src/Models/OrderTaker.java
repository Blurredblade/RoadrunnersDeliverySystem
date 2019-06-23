package Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderTaker {
    private int orderTaker_id;
    private String orderTaker_name;
    private boolean isActive;


    public OrderTaker(int orderTaker_id, String orderTaker_name, boolean isActive){
        this.setID(orderTaker_id);
        this.setName(orderTaker_name);
        this.setActive(isActive);
    }

    public OrderTaker() {

    }


    public int getID() { return orderTaker_id; }
    public void setID(int orderTaker_id) {
        this.orderTaker_id = orderTaker_id;
    }
    public String getName() {
        return orderTaker_name;
    }
    public void setName(String orderTaker_name) {
        this.orderTaker_name = orderTaker_name;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    // TEST
    public SimpleIntegerProperty orderTakerID = new SimpleIntegerProperty();
    public SimpleStringProperty orderTakerName = new SimpleStringProperty();
    public SimpleBooleanProperty orderTakerActive = new SimpleBooleanProperty();
    public Integer getOrderTakerID() { return orderTakerID.get(); }
    public String getOrderTakerName() { return orderTakerName.get(); }
    public Boolean getOrderTakerActive() { return orderTakerActive.get(); }
    public void test() { System.out.println("Order Taker ID: " + orderTakerID + " NAME: " + orderTakerName
            + " Active: " + orderTakerActive ); }
}
