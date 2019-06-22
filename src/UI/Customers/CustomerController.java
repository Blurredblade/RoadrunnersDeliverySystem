package UI.Customers;

import DataManagement.DatabaseManager;
import Models.Courier;
import Models.Customer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CustomerController {
    @FXML private Button addCustomerBtn;
    @FXML private Button editCustBtn;
    @FXML private Button singleBillBtn;
    @FXML private Button deleteCustbtn;
    @FXML private Button allBillBtn;
    @FXML private Button logoutButton;

    public Connection con;
    private Logger logger;
    private DatabaseManager objDbClass;
    @FXML private TableView customerTable;
    @FXML public TableColumn colCustomerID;
    @FXML public TableColumn colCustomerName;
    @FXML public TableColumn colCustomerLoc;
    @FXML public TableColumn colCustomerStatus;

    @FXML
    public void initialize() {

        // Add Customer
        addCustomerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                FXMLLoader newCustomer = new FXMLLoader( getClass().getResource("../Customers/newCustomer.fxml") );
                try {
                    System.out.print("\nAdding Customer to Database");
                    Stage stage = new Stage();
                    stage.setScene( new Scene( newCustomer.load() ) );
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Edit Customer Button
        editCustBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                FXMLLoader modifyCustomer = new FXMLLoader( getClass().getResource("../Customers/modifyCustomer.fxml") );
                try {
                    System.out.println("\nEdit an existing Customer in the Database");
                    Stage stage = new Stage();
                    stage.setScene( new Scene( modifyCustomer.load() ) );
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });

        // Delete Customer Button
        deleteCustbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Deleting Customer from Database");
            }
        });

        // Generate Bill for a single Customer
        singleBillBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FXMLLoader singleCustomersBill = new FXMLLoader(getClass().getResource("../Customers/singleBillUI.fxml"));
                try {
                    System.out.println("Generate Bills for all selected Customers");
                    Stage stage = new Stage();
                    stage.setScene(new Scene(singleCustomersBill.load()));
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Generate Bills for all selected Customers
        allBillBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FXMLLoader allCustomersBill = new FXMLLoader(getClass().getResource("../Customers/multipleBillUI.fxml"));
                //FXMLLoader loadBusinessReport = new FXMLLoader(getClass().getResource("../BusinessReport/businessReportUI.fxml"));
                try {
                    System.out.println("Generate Bills for all selected Customers");
                    Stage stage = new Stage();
                    stage.setScene(new Scene(allCustomersBill.load()));
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Tableview Population starts here
        assert customerTable != null : "fx:id=\"tableview\" was not injected: check your FXML file 'customerUI.fxml'.";
        //colCustomerID.setCellValueFactory( new PropertyValueFactory<Courier,Integer>("customerID"));
        colCustomerName.setCellValueFactory( new PropertyValueFactory<Courier,String>("customerName"));
        colCustomerLoc.setCellValueFactory( new PropertyValueFactory<Courier,String>("customerLoc"));

        customerTable.setEditable(true);


        // This line works for Active Status - DO NOT DELETE!!!!!!!
        colCustomerStatus.setCellValueFactory( new PropertyValueFactory<Courier, Boolean>("customerStatus") );




        objDbClass = new DatabaseManager();
        try{
            con = objDbClass.getConnection();
            buildData();
        }
        catch(ClassNotFoundException ce){ logger.info(ce.toString()); }
        catch(SQLException ce){ logger.info(ce.toString()); }
    }

    private ObservableList<Customer> data;
    public void buildData(){
        data = FXCollections.observableArrayList();
        try{
            String SQL = "Select * from customer Order By customer_id";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()){
                Customer cm = new Customer();
                //cm.customerID.set(rs.getInt("customer_id"));
                cm.customerName.set(rs.getString("customer_name"));
                cm.customerLoc.set(rs.getString("customer_address"));
                cm.customerStatus.set(rs.getBoolean("isActive"));
                cm.test();
                data.add(cm);
            }
            customerTable.setItems(data);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}