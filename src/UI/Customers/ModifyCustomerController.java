package UI.Customers;

import DataManagement.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyCustomerController {

    DatabaseManager.QueryManager queryManager;
    CustomerController controller;

    @FXML private Button cancelBtn;
    @FXML private Button saveChangesBtn;
    @FXML private TextField nameTextField;
    @FXML private TextField addressTextField;
    @FXML private Label nameLabel;
    @FXML private Label addressLabel;
    @FXML private Label modifycustomerLabel;
    @FXML private CheckBox activeCheckBox;

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
    public void update() {


        //queryManager.updateCustomer( controller );

    }

}
