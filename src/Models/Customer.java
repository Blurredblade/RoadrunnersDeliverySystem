package Models;

public class Customer {

    private int customer_id;
    private String customer_name;
    private String customer_address;
    private boolean isActive;

    public Customer(int customer_id, String customer_name, String customer_address, boolean isActive){
        setID(customer_id);
        setName(customer_name);
        setAddress(customer_address);
        setActive(isActive);
    }

    public int getID() {
        return customer_id;
    }

    public void setID(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return customer_name;
    }

    public void setName(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getAddress() {
        return customer_address;
    }

    public void setAddress(String customer_address) {
        this.customer_address = customer_address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
