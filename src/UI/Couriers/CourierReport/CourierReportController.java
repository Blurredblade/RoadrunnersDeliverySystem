package UI.Couriers.CourierReport;


import DataManagement.DatabaseManager;
import Models.Business;
import Models.Courier;
import Models.CustomerOrder;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.control.*;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


public class CourierReportController {
    @FXML private Label courierNameLabel;
    @FXML private DatePicker start;
    @FXML private DatePicker end;

    private Courier courier;
    private int NumOfDeliveries;
    private float OnTime;
    private float bonus;

    public void initialize(){
        NumOfDeliveries = 0;
        OnTime = 0;
        bonus = 0;
    }

    public void setCourier(Courier courier){
        this.courier = courier;
    }

    @FXML
    public void create(){


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Generating");
        alert.setContentText("Generating Courier Report...");
        alert.setHeaderText(null);
        alert.setGraphic(null);

        alert.showAndWait();

        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        LocalDate locStartDate = start.getValue();
        Instant startI = Instant.from(locStartDate.atStartOfDay(ZoneId.systemDefault()));
        Date startDate = Date.from(startI);

        LocalDate locEndDate = end.getValue();
        Instant endI = Instant.from(locEndDate.atStartOfDay(ZoneId.systemDefault()));
        Date endDate = Date.from(endI);

        ArrayList<CustomerOrder> orders = q.getOrders(courier, startDate, endDate);
        Business business = q.getBusinessSettings();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        float bonusRate = business.getBonusRate();
        float bonusGrace = business.getBonusGracePeriod();

        for(CustomerOrder o: orders){
            NumOfDeliveries++;
            o.setEstimatedDelivery(new Date(System.currentTimeMillis()));
            float diff = (o.getEstimatedDelivery().getTime() - o.getDelivery().getTime()) * 1000f * 60f;
            if(diff < bonusGrace){
                bonus += bonusRate;
                OnTime++;
            }

        }

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

        try {

            PrintWriter writer = new PrintWriter("CourierReports/" +
                    courier.getName().replaceAll("\\s+","") +
                    sdf.format(startDate).toString().replaceAll("\\s+","") + "to" +
                    sdf.format(endDate).toString().replaceAll("\\s+","") +
                    ".txt"
                    , "UTF-8");
            writer.println("Courier report for #" + courier.getID() +" Name:" + courier.getName());
            writer.println("Start date: " + sd.format(startDate).toString());
            writer.println("End date: "+ sd.format(endDate).toString());
            writer.println("Number of deliveries: " + NumOfDeliveries);
            writer.println("Percentage on time: " + ((OnTime/NumOfDeliveries) * 100) + "%");
            writer.println("Bonus: $" + bonus);
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
    public void cancel(){
        Stage stage = (Stage) courierNameLabel.getScene().getWindow();
        stage.close();
    }
}
