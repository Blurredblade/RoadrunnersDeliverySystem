package UI.Couriers;

import DataManagement.DatabaseManager;
import Models.Courier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @FXML public TableColumn colCourierID;
    @FXML public TableColumn colCourierName;

    @FXML
    void initialize(){
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logging out");
                //loginManager.logout();
            }
        });

        assert courierTable != null : "fx:id=\"tableview\" was not injected: check your FXML file 'couriers.fxml'.";
        colCourierID.setCellValueFactory( new PropertyValueFactory<Courier,Integer>("courierID"));
        colCourierName.setCellValueFactory( new PropertyValueFactory<Courier,String>("courierName"));
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
                cm.courierID.set(rs.getInt("courier_id"));
                cm.courierName.set(rs.getString("courier_name"));
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