package DataManagement;

import Models.*;
import com.mysql.cj.xdevapi.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
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
                    c = new Courier(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return c;
        }

        public void updateCourier(Courier courier){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("Update COURIER SET courier_name=?, isActive=? WHERE courier_id=?");
                    statement.setString(1, courier.getName());
                    statement.setBoolean(2, courier.isActive());
                    statement.setInt(3, courier.getID());
                    statement.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public void createCourier(Courier courier){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("INSERT INTO COURIER (courier_name, isActive) VALUES (?,?)");
                    statement.setString(1, courier.getName());
                    statement.setBoolean(2, courier.isActive());
                    statement.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        //returns 2 if connection error, 1 if has associated data, and 0 if successful.
        public int deleteCourier(int ID){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement orderTest = con.prepareStatement("SELECT * FROM CUSTOMERORDER WHERE courier_id=?;");
                    orderTest.setInt(1, ID);
                    ResultSet ordRs = orderTest.executeQuery();
                    if(ordRs.next()){
                        return 1;
                    }
                    PreparedStatement statement = con.prepareStatement("DELETE FROM COURIER WHERE courier_id=?;");
                    statement.setInt(1, ID);
                    statement.execute();
                    return 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return 2;
        }

        public ArrayList<Courier> getCouriers(boolean Active) {
            ArrayList<Courier> couriers = new ArrayList<>();
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM COURIER" + ((Active)?" WHERE isActive = TRUE;":";"));
                    while(rs.next()){
                        Courier c = new Courier(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                        couriers.add(c);
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
                    c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return c;
        }

        public void updateCustomer(Customer customer){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("Update CUSTOMER SET customer_name=?, customer_address=?, isActive=? WHERE customer_id=?");
                    statement.setString(1, customer.getName());
                    statement.setString(2, customer.getAddress());
                    statement.setBoolean(3, customer.isActive());
                    statement.setInt(4, customer.getID());
                    statement.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public void createCustomer(Customer customer){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("INSERT INTO CUSTOMER (customer_name, customer_address, isActive) VALUES (?,?,?);");
                    statement.setString(1, customer.getName());
                    statement.setString(2, customer.getAddress());
                    statement.setBoolean(3, customer.isActive());
                    statement.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public ArrayList<Customer> getCustomers(boolean Active){
            ArrayList<Customer> customers = new ArrayList<>();
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM CUSTOMER" + ((Active)?" WHERE isActive = TRUE;":";"));
                    while(rs.next()){
                        Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                        customers.add(c);
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
                    o = new OrderTaker(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
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
                    ResultSet rs = statement.executeQuery("SELECT * FROM ORDERTAKER;");
                    while(rs.next()){
                        OrderTaker c = new OrderTaker(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
                        orderTakers.add(c);
                    }
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return orderTakers;
        }


        public ArrayList<CustomerOrder> getOrders(int status){
            ArrayList<CustomerOrder> orders = new ArrayList<>();
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {

                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM CUSTOMERORDER " +  ((status != -1)?("WHERE order_status = " + status + ";"):";"));
                    while(rs.next()){
                        int s = rs.getInt("order_status");
                        if(s == 3 && status == -1){
                            continue;
                        }
                        OrderStatus stat;
                        switch(s) {
                            case 0:
                                stat = OrderStatus.AWAITING_DEPARTURE;
                                break;
                            case 1:
                                stat = OrderStatus.OUT_FOR_DELIVERY;
                                break;
                            case 2:
                                stat = OrderStatus.DELIVERED;
                                break;
                            case 3:
                                stat = OrderStatus.CANCELED;
                                break;
                            default:
                                stat = OrderStatus.CANCELED;
                                break;
                        }
                        CustomerOrder o = new CustomerOrder(
                                rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getInt(5),
                                rs.getInt(6),
                                rs.getBoolean(7),
                                rs.getString(8),
                                rs.getTime(9),
                                rs.getTime(10),
                                rs.getTime(11),
                                rs.getInt(12),
                                rs.getFloat(13),
                                rs.getTime(14),
                                rs.getTime(15),
                                rs.getTime(16),
                                stat
                        );
                        orders.add(o);
                    }
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            return orders;
        }

        public void createOrder(CustomerOrder order){
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("INSERT INTO CUSTOMERORDER (" +
                            "package_id, pickup_customer_id, delivery_customer_id, order_taker_id," +
                            "bill_to_delivery, special_instructions, pick_up_time," +
                            "order_status" +
                            ") VALUES" +
                            "(?,?,?,?,?,?,?,?);");
                    statement.setInt(1, order.getPackageID());
                    statement.setInt(2, order.getPickupCustomer().getID());
                    statement.setInt(3, order.getDeliveryCustomer().getID());
                    statement.setInt(4, order.getOrderTaker().getID());
                    statement.setBoolean(5, order.isBillToDelivery());
                    statement.setString(6, order.getSpecialInstructions());
                    statement.setString(7, order.getPickupTime().toString());
                    statement.setInt(8, order.getStatus().getValue());
                    statement.execute();
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }

        public void updateOrder(CustomerOrder order){
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    PreparedStatement statement = con.prepareStatement("UPDATE CUSTOMERORDER SET " +
                            ((order.getDeparture() != null)?"departure='" + sdf.format(order.getDeparture()) + "', ":"") +
                            ((order.getPickup() != null)?"pickup='"+sdf.format(order.getPickup()) + "', ":"")+
                            ((order.getDelivery() != null)?"delivery='"+sdf.format(order.getDelivery())+"', ":"")+
                            ((order.getCourier() != null)?"courier="+order.getCourier().getID()+", ":"")+
                            "order_status=" + order.getStatus().getValue() +
                            " WHERE order_id=?");
                    statement.setInt(1, order.getID());
                    System.out.println(statement.toString());

                    statement.execute();
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }

        public void cancelOrder(int order_id){
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("Update CUSTOMERORDER SET order_status=3 WHERE order_id=?");
                    statement.setInt(1, order_id);
                    statement.execute();
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
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

        public void setUserPermissions(String username, String password, int permissions){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("Update USERCREDENTIALS SET permissions=? WHERE username=? AND pass=?");
                    statement.setInt(1, permissions);
                    statement.setString(2, username);
                    statement.setString(3, password);
                    statement.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
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
                BufferedReader br = new BufferedReader(new FileReader(new File("src/DataManagement/DBInfo.txt")));
                //Class.forName("com.mysql.jdbc.Driver");
                Properties info = new Properties();
                info.put("user", br.readLine());
                info.put("password", br.readLine());
                String url = br.readLine();
                br.close();
                return DriverManager.getConnection(url, info);
            }catch(Exception e){
                System.out.println(e);
                return null;
            }
        }
    }

}