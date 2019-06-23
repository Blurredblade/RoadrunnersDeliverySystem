package Models;

public class OrderTaker {
    private int orderTaker_id;
    private String orderTaker_name;
    private boolean isActive;

    public OrderTaker(int orderTaker_id, String orderTaker_name, boolean isActive){
        this.orderTaker_id = orderTaker_id;
        this.setName(orderTaker_name);
        this.setActive(isActive);
    }

    public int getID() {
        return orderTaker_id;
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
}
