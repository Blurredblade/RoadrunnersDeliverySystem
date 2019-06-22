package UI.Couriers;

import DataManagement.DatabaseManager;
import Models.Courier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CourierController {
    @FXML private Button addCourierBtn;
    @FXML private Button editCourierBtn;
    @FXML private Button courierReportBtn;
    @FXML private Button deleteCourierbtn;
    @FXML private Button logoutButton;
    @FXML private CheckBox inactiveCouriers;
    @FXML private TableView courierTable;


    public Connection con;
    private Logger logger;
    private DatabaseManager objDbClass;

    @FXML public TableColumn colCourierRow;
    @FXML public TableColumn colCourierID;
    @FXML public TableColumn colCourierName;
    @FXML public TableColumn colCourierActive;

    @FXML
    void initialize(){

        // New Courier
        addCourierBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader newCourier = new FXMLLoader( getClass().getResource("../Couriers/newCourier.fxml") );
                try {
                    System.out.print("\nAdding Courier to Database");
                    Stage stage = new Stage();
                    stage.setScene( new Scene( newCourier.load() ) );
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // Edit Courier
        editCourierBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                FXMLLoader modifyCourier = new FXMLLoader( getClass().getResource("../Couriers/modifyCourier.fxml") );
                try {
                    System.out.println("\nEdit an existing Courier in the Database");
                    Stage stage = new Stage();
                    stage.setScene( new Scene( modifyCourier.load() ) );
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });

        // Delete Courier
        deleteCourierbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("\nDeleting Courier from Database");
            }
        });

        // Generate Report for a single Courier
        courierReportBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FXMLLoader courierReport = new FXMLLoader(getClass().getResource("../Couriers/courierReport.fxml"));
                try {
                    System.out.println("\nGenerate Report for selected Courier");
                    Stage stage = new Stage();
                    stage.setScene(new Scene(courierReport.load()));
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Table
        assert courierTable != null : "fx:id=\"tableview\" was not injected: check your FXML file 'couriers.fxml'.";
        //colCourierID.setCellValueFactory( new PropertyValueFactory<Courier,Integer>("courierID"));
        colCourierName.setCellValueFactory( new PropertyValueFactory<Courier,String>("courierName"));
        colCourierActive.setCellValueFactory( new PropertyValueFactory<Courier, Boolean>("courierActive") );
        objDbClass = new DatabaseManager();
        try{
            con = objDbClass.getConnection();
            buildData();
        }
        catch(ClassNotFoundException ce){ logger.info(ce.toString()); }
        catch(SQLException ce){ logger.info(ce.toString()); }
    }

    private ObservableList<Courier> data;
    public void buildData(){
        data = FXCollections.observableArrayList();
        try{
            String SQL = "Select * from courier Order By courier_id";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()){
                Courier cm = new Courier();
                //cm.courierID.set(rs.getInt("courier_id"));
                cm.courierName.set(rs.getString("courier_name"));
                cm.courierActive.set(rs.getBoolean("isActive"));
                cm.test();
                data.add(cm);
            }
            courierTable.setItems(data);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}