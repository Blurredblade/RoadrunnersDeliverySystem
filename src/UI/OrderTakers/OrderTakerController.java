package UI.OrderTakers;

import DataManagement.DatabaseManager;
import Models.OrderTaker;
//import UI.OrderTakers.EditOrderTaker.EditOrderTakerController;
import UI.OrderTakers.EditOrderTaker.EditOrderTakerController;
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

public class OrderTakerController {
    @FXML private Button addOrderTakerBtn;
    @FXML private Button editOrderTakerBtn;
    @FXML private Button OrderTakerReportBtn;
    @FXML private Button deleteOrderTakerbtn;
    @FXML private CheckBox inactiveOrderTakers;
    @FXML private TableView<OrderTaker> OrderTakerTable;

    private boolean displayOnlyActive;

    public void initialize(){
        displayOnlyActive = true;
        initOrderTakerTable();


        OrderTakerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel)->{
            if(newSel != null){
                editOrderTakerBtn.disableProperty().setValue(false);
                deleteOrderTakerbtn.disableProperty().setValue(false);
            }
        });
    }

    private void initOrderTakerTable(){
        TableColumn<OrderTaker, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OrderTaker, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<OrderTaker, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getName());
            }
        });

        TableColumn<OrderTaker, String> active = new TableColumn<>("Active");
        active.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OrderTaker, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<OrderTaker, String> param) {
                return new ReadOnlyObjectWrapper<>((param.getValue().isActive())? "Yes":"No");
            }
        });

        name.prefWidthProperty().bind(OrderTakerTable.widthProperty().divide(2.1));
        active.prefWidthProperty().bind(OrderTakerTable.widthProperty().divide(2.1));

        OrderTakerTable.getColumns().addAll(name, active);

        refreshTable();
    }

    @FXML
    public void toggle(){
        displayOnlyActive = !inactiveOrderTakers.isSelected();
        refreshTable();
    }

    @FXML
    public void refreshTable(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        ArrayList<OrderTaker> OrderTakers = q.getOrderTakers(displayOnlyActive);

        OrderTakerTable.getItems().clear();
        OrderTakerTable.getItems().addAll(OrderTakers);
        editOrderTakerBtn.disableProperty().setValue(true);
        deleteOrderTakerbtn.disableProperty().setValue(true);
    }

    @FXML
    public void newOrderTaker(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../OrderTakers/NewOrderTaker/newOrderTaker.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - New OrderTaker");
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
    public void deleteOrderTaker(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        if(q.checkForDependence(OrderTakerTable.getSelectionModel().getSelectedItem())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("The OrderTaker you are trying to delete has orders associated with them, therefore they can't be deleted.");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.showAndWait();
        }else{
            int OrderTaker_id = OrderTakerTable.getSelectionModel().getSelectedItem().getID();
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm OrderTaker Deletion");
            confirm.setContentText("Are you sure you want to remove " + OrderTakerTable.getSelectionModel().getSelectedItem().getName() + " from the system?");
            confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            confirm.setHeaderText(null);
            confirm.setGraphic(null);
            Optional<ButtonType> result = confirm.showAndWait();
            if(result.get() == ButtonType.YES) {
                q.deleteOrderTaker(OrderTaker_id);
                refreshTable();
            }
        }


    }

    @FXML
    public void editOrderTaker(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../OrderTakers/EditOrderTaker/editOrderTaker.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - Edit OrderTaker");
            AnchorPane a = loader.load();
            EditOrderTakerController controller = loader.getController();
            controller.setOrderTaker(OrderTakerTable.getSelectionModel().getSelectedItem());
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