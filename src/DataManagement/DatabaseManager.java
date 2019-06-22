package DataManagement;

import Models.*;
import com.mysql.cj.xdevapi.Result;

import java.sql.*;
import java.util.Properties;
import java.util.ArrayList;

public class DatabaseManager {
    public static class QueryManager{
        public Courier getCourier(int ID){
            Courier c = null;
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM COURIER WHERE courier_id = " + ID);
                    rs.next();
                    // TEST
                    //c = new Courier(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return c;
        }

        public ArrayList<Courier> getCouriers() {
            ArrayList<Courier> couriers = new ArrayList<>();
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM COURIER");
                    while(rs.next()){
                        // TEST
                        //Courier c = new Courier(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                        //couriers.add(c);
                    }
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return couriers;
        }

        public Customer getCustomer(int ID){
            Customer c = null;
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM CUSTOMER WHERE customer_id = " + ID);
                    rs.next();
                    //c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return c;
        }

        public ArrayList<Customer> getCustomers(){
            ArrayList<Customer> customers = new ArrayList<>();
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM CUSTOMER");
                    while(rs.next()){
                        //Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                        //customers.add(c);
                    }
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return customers;
        }

        public OrderTaker getOrderTaker(int ID){
            OrderTaker o = null;
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM ORDERTAKER WHERE orderTaker_id = " + ID);
                    rs.next();
                    //o = new OrderTaker(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return o;
        }

        public ArrayList<OrderTaker> getOrderTakers() {
            ArrayList<OrderTaker> orderTakers = new ArrayList<>();
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM ORDERTAKER");
                    while(rs.next()){
                        //OrderTaker c = new OrderTaker(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                        //orderTakers.add(c);
                    }
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return orderTakers;
        }


        public ArrayList<CustomerOrder> getOrders(){
            ArrayList<CustomerOrder> orders = new ArrayList<>();
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM CUSTOMERORDER");
                    while(rs.next()){
                        //CustomerOrder o = new CustomerOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getBoolean(7), rs.getString(8), rs.getTime(9));
                        //orders.add(o);
                    }
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return orders;
        }

        //returns 0 if user not found, 1 if user found but password doesn't match, and 2 if match
        public int verifyUser(String username, String password){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("SELECT pass FROM USERCREDENTIALS WHERE username=?");
                    statement.setString(1, username);
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        if (rs.getString(1).compareTo(password) == 0) {
                            con.close();
                            return 2;
                        }
                        con.close();
                        return 1;
                    } else {
                        con.close();
                        return 0;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return -1;
                }
            }
            return -1;
        }

        public int getUserPermissions(String username, String password){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("SELECT permissions FROM USERCREDENTIALS WHERE username=? AND pass=?");
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    return rs.getInt(1);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return -1;
                }
            }
            return -1;
        }

        public Business getBusinessSettings(){
            Connection con = new DatabaseConnection().Connect();
            if(con != null){
                try{
                    PreparedStatement statement = con.prepareStatement("SELECT * FROM BUSINESS");
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    Business b = new Business(rs.getString(1), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4), rs.getInt(5));
                    con.close();
                    return b;
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return null;
        }

        public void setBusinessSettings(Business b){
            Connection con = new DatabaseConnection().Connect();
            if(con != null){
                try{
                    PreparedStatement statement = con.prepareStatement("UPDATE BUSINESS SET " +
                            "address=?, " +
                            "deliveryBlockRate=?, " +
                            "deliveryBase=?, " +
                            "bonusRate=?, " +
                            "bonusGracePeriod=? ");
                    statement.setString(1, b.getAddress());
                    statement.setFloat(2, b.getDeliveryBlockRate());
                    statement.setFloat(3, b.getDeliveryBase());
                    statement.setFloat(4, b.getBonusRate());
                    statement.setFloat(5, b.getBonusGracePeriod());

                    statement.executeUpdate();
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    static class DatabaseConnection{
        public Connection Connect(){
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/acmedelivery";
                Properties info = new Properties();
                info.put("user", "root");
                info.put("password", "Aerithv24");
                return DriverManager.getConnection(url, info);
            }catch(Exception e){
                System.out.println(e);
                return null;
            }
        }
    }

    // This is here temporarily
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/acmedelivery","root","Password");
    }

}