package UI.Couriers;

import DataManagement.DatabaseManager;
import Models.Courier;
import UI.Couriers.CourierReport.CourierReportController;
import UI.Couriers.EditCourier.EditCourierController;
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

public class CourierController {
    @FXML private Button addCourierBtn;
    @FXML private Button editCourierBtn;
    @FXML private Button courierReportBtn;
    @FXML private Button deleteCourierbtn;
    @FXML private CheckBox inactiveCouriers;
    @FXML private TableView<Courier> courierTable;

    private boolean displayOnlyActive;

    public void initialize(){
        displayOnlyActive = true;
        initCourierTable();


        courierTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel)->{
            if(newSel != null){
                editCourierBtn.disableProperty().setValue(false);
                courierReportBtn.disableProperty().setValue(false);
                deleteCourierbtn.disableProperty().setValue(false);
            }
        });
    }

    private void initCourierTable(){
        TableColumn<Courier, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Courier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Courier, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getName());
            }
        });

        TableColumn<Courier, String> active = new TableColumn<>("Active");
        active.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Courier, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Courier, String> param) {
                return new ReadOnlyObjectWrapper<>((param.getValue().isActive())? "Yes":"No");
            }
        });

        name.prefWidthProperty().bind(courierTable.widthProperty().divide(2.1));
        active.prefWidthProperty().bind(courierTable.widthProperty().divide(2.1));

        courierTable.getColumns().addAll(name, active);

        refreshTable();
    }

    @FXML
    public void toggle(){
        displayOnlyActive = !inactiveCouriers.isSelected();
        refreshTable();
    }

    @FXML
    public void refreshTable(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        ArrayList<Courier> couriers = q.getCouriers(displayOnlyActive);

        courierTable.getItems().clear();
        courierTable.getItems().addAll(couriers);
        editCourierBtn.disableProperty().setValue(true);
        courierReportBtn.disableProperty().setValue(true);
        deleteCourierbtn.disableProperty().setValue(true);
    }

    @FXML
    public void newCourier(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Couriers/NewCourier/newCourier.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - New Courier");
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
    public void deleteCourier(){
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        if(q.checkForDependence(courierTable.getSelectionModel().getSelectedItem())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("The courier you are trying to delete has orders associated with them, therefore they can't be deleted.");
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.showAndWait();
        }else{
            int courier_id = courierTable.getSelectionModel().getSelectedItem().getID();
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Courier Deletion");
            confirm.setContentText("Are you sure you want to remove " + courierTable.getSelectionModel().getSelectedItem().getName() + " from the system?");
            confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            confirm.setHeaderText(null);
            confirm.setGraphic(null);
            Optional<ButtonType> result = confirm.showAndWait();
            if(result.get() == ButtonType.YES) {
                q.deleteCourier(courier_id);
                refreshTable();
            }
        }


    }

    @FXML
    public void editCourier(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Couriers/EditCourier/editCourier.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - Edit Courier");
            AnchorPane a = loader.load();
            EditCourierController controller = loader.getController();
            controller.setCourier(courierTable.getSelectionModel().getSelectedItem());
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

    @FXML
    public void getReport(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Couriers/CourierReport/courierReport.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - Courier Report");
            AnchorPane a = loader.load();
            CourierReportController controller = loader.getController();
            controller.setCourier(courierTable.getSelectionModel().getSelectedItem());
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