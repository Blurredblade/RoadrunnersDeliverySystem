package UI.Customers;

import DataManagement.DatabaseManager;
import Models.Business;
import Models.Customer;
import Models.CustomerOrder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class CustomerBillsController {
    @FXML private Button cancelButton;
    @FXML private Button createButton;

    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;

    private float Total;

    private Customer customer;

    public void initialize() {
        Total = 0;
    }

    public void setCustomer(Customer customer) {

    }

    @FXML
    public void CreateMultiple(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        ArrayList<Customer> customers = q.getCustomers(true);
        for(Customer customer: customers){
            this.customer = customer;
            Create();
        }
    }

    @FXML
    public void Create() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Generating");
        alert.setContentText("Generating Courier Report...");
        alert.setHeaderText(null);
        alert.setGraphic(null);

        alert.showAndWait();

        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        LocalDate locStartDate = startDate.getValue();
        Instant startI = Instant.from(locStartDate.atStartOfDay(ZoneId.systemDefault()));
        Date startDate = Date.from(startI);

        LocalDate locEndDate = endDate.getValue();
        Instant endI = Instant.from(locEndDate.atStartOfDay(ZoneId.systemDefault()));
        Date endDate = Date.from(endI);


        ArrayList<CustomerOrder> orders = q.getOrders(customer);
        Business business = q.getBusinessSettings();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");



        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

        try {

            PrintWriter writer = new PrintWriter("CustomerBills/" +
                    customer.getName().replaceAll("\\s+","") +
                    sdf.format(startDate).toString().replaceAll("\\s+","") + "to" +
                    sdf.format(endDate).toString().replaceAll("\\s+","") +
                    ".txt"
                    , "UTF-8");
            writer.println("Customer report for #" + customer.getID() +" Name:" + customer.getName());
            writer.println("Start date: " + sd.format(startDate).toString());
            writer.println("End date: "+ sd.format(endDate).toString());
            for(CustomerOrder o: orders){
                o.setEstimatedDelivery(new Date(System.currentTimeMillis()));
                float diff = (o.getEstimatedDelivery().getTime() - o.getDelivery().getTime()) * 1000f * 60f;
                writer.println("Order ID: " + o.getID());
                writer.println("Order Taker: " + o.getOrderTaker().getName());
                writer.println("Delivery Customer: " + o.getDeliveryCustomer().getName());
                writer.println("Pickup Customer: " + o.getPickupCustomer().getName());
                writer.println("Courier: " + o.getCourier().getName());
                writer.println("Distance: " + o.getDistance());
                writer.println("Price: " + o.getPrice());
            }
            writer.close();
            Alert b = new Alert(Alert.AlertType.INFORMATION);
            b.setTitle("Success");
            b.setContentText("Successfully created courier report!");
            b.setHeaderText(null);
            b.setGraphic(null);

            b.show();

            cancel();
        }catch(Exception e){

        }
    }
    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}