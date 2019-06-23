package UI.Customers;

import DataManagement.DatabaseManager;
import Models.Customer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class NewCustomerController {

    public Connection con;

    Customer customer = new Customer( 1, "", "", false );

    DatabaseManager.QueryManager queryManager;

    @FXML private Button createBtn;
    @FXML private Button cancelBtn;
    @FXML private TextField nameTextField;
    @FXML private TextField addressTextField;
    @FXML private Label nameLabel;
    @FXML private Label addressLabel;
    @FXML private Label newCustomerLabel;

    public void initialize() {
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage stage = (Stage)cancelBtn.getScene().getWindow();
                stage.close();

            }
        });

    }

    @FXML
    public void create(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        System.out.println("Create");
        q.createCustomer(new Customer(0, nameTextField.getText(), addressTextField.getText(), true));
    }

}
