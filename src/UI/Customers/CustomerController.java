package UI.Customers;

import DataManagement.DatabaseManager;
import Models.Customer;
import UI.Customers.EditCustomer.EditCustomerController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerController {
    @FXML private Button addcustomerBtn;
    @FXML private Button editcustomerBtn;
    @FXML private Button customerReportBtn;
    @FXML private Button deletecustomerbtn;
    @FXML private CheckBox inactiveCustomers;
    @FXML private TableView<Customer> customerTable;

    private boolean displayOnlyActive;

    public void initialize(){
        displayOnlyActive = true;
        initcustomerTable();


        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel)->{
            if(newSel != null){
                editcustomerBtn.disableProperty().setValue(false);
                deletecustomerbtn.disableProperty().setValue(false);
            }
        });
    }

    private void initcustomerTable(){
        TableColumn<Customer, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getName());
            }
        });

        TableColumn<Customer, String> address = new TableColumn<>("Address");
        address.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer, String> param) {
                return new ReadOnlyObjectWrapper<>((param.getValue().getAddress()));
            }
        });

        TableColumn<Customer, String> active = new TableColumn<>("Active");
        active.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer, String> param) {
                return new ReadOnlyObjectWrapper<>((param.getValue().isActive())? "Yes":"No");
            }
        });

        name.prefWidthProperty().bind(customerTable.widthProperty().divide(3.1));
        address.prefWidthProperty().bind(customerTable.widthProperty().divide(3.1));
        active.prefWidthProperty().bind(customerTable.widthProperty().divide(3.1));

        customerTable.getColumns().addAll(name, address, active);

        refreshTable();
    }

    @FXML
    public void toggle(){
        displayOnlyActive = !inactiveCustomers.isSelected();
        refreshTable();
    }

    @FXML
    public void refreshTable(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        ArrayList<Customer> customers = q.getCustomers(displayOnlyActive);

        customerTable.getItems().clear();
        customerTable.getItems().addAll(customers);
        editcustomerBtn.disableProperty().setValue(true);
        deletecustomerbtn.disableProperty().setValue(true);
    }

    @FXML
    public void newCustomer(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../customers/NewCustomer/newCustomer.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - New Customer");
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding((c)->
                    refreshTable()
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void deleteCustomer(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        if(q.checkForDependence(customerTable.getSelectionModel().getSelectedItem())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("The customer you are trying to delete has orders associated with them, therefore they can't be deleted.");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.showAndWait();
        }else{
            int customer_id = customerTable.getSelectionModel().getSelectedItem().getID();
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm customer Deletion");
            confirm.setContentText("Are you sure you want to remove " + customerTable.getSelectionModel().getSelectedItem().getName() + " from the system?");
            confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            confirm.setHeaderText(null);
            confirm.setGraphic(null);
            Optional<ButtonType> result = confirm.showAndWait();
            if(result.get() == ButtonType.YES) {
                q.deleteCustomer(customer_id);
                refreshTable();
            }
        }


    }

    @FXML
    public void editCustomer(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../customers/Editcustomer/editcustomer.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - Edit customer");
            AnchorPane a = loader.load();
            EditCustomerController controller = loader.getController();
            controller.setCustomer(customerTable.getSelectionModel().getSelectedItem());
            stage.setScene(new Scene(a));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding((c)->
                    refreshTable()
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}