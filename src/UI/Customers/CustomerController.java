package UI.Customers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {
    @FXML private Button addCustomerBtn;
    @FXML private Button editCustBtn;
    @FXML private Button singleBillBtn;
    @FXML private Button deleteCustbtn;
    @FXML private Button allBillBtn;
    @FXML private Button logoutButton;

    public void initialize() {
        // Add Customer
        addCustomerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.print("Adding Customer to Database");
            }
        });

        // Edit Customer Button
        editCustBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Edit an existing Customer in the Database");
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
        allBillBtn.setOnAction(new EventHandler<>() {
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
    }
}