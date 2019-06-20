package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Courier {

    private int courier_id;
    private String courier_name;
    private boolean isActive;

    /**
    public Courier(int id, String name, boolean isActive){
        this.setID(id);
        this.setName(name);
        this.setActive(isActive);
    }
     **/

    public int getID() { return courier_id; }
    public void setID(int courier_id) { this.courier_id = courier_id; }
    public String getName() { return courier_name; }
    public void setName(String courier_name) { this.courier_name = courier_name; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public void testPrint() { System.out.println("ID: " + courier_id + " Name: " + courier_name + " isActive: " + isActive); }

    // TEST
    public SimpleIntegerProperty courierID = new SimpleIntegerProperty();
    public SimpleStringProperty courierName = new SimpleStringProperty();
    public Integer getCourierID() { return courierID.get(); }
    public String getCourierName() { return courierName.get(); }
    public void test() { System.out.println("Courier ID: " + courierID + " NAME: " + courierName); }
}
