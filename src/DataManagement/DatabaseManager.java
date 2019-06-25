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
                    e.printStackTrace();
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

        public int deleteCustomer(int ID){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement orderTest = con.prepareStatement("SELECT * FROM CUSTOMERORDER WHERE pickup_customer_id=? OR delivery_customer_id=?;");
                    orderTest.setInt(1, ID);
                    orderTest.setInt(2, ID);
                    ResultSet ordRs = orderTest.executeQuery();
                    if(ordRs.next()){
                        return 1;
                    }
                    PreparedStatement statement = con.prepareStatement("DELETE FROM CUSTOMER WHERE customer_id=?;");
                    statement.setInt(1, ID);
                    statement.execute();
                    return 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return 2;
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

        public OrderTaker getOrderTaker(int ID, boolean userID){
            OrderTaker o = null;
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = null;
                    if(userID){
                        rs = statement.executeQuery("SELECT * FROM ORDERTAKER WHERE orderTaker_id IN (SELECT orderTaker_id FROM USERCREDENTIALS WHERE user_id = " + ID + ");");
                    }else {
                        rs = statement.executeQuery("SELECT * FROM ORDERTAKER WHERE orderTaker_id = " + ID);
                    }
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

        public boolean checkForDependence(Courier c){
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    PreparedStatement s = con.prepareStatement("SELECT * FROM CUSTOMERORDER WHERE courier_id=?;");
                    s.setInt(1, c.getID());
                    ResultSet rs = s.executeQuery();
                    if(rs.next()){
                        return true;
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            return false;
        }

        public boolean checkForDependence(Customer c){
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    PreparedStatement s = con.prepareStatement("SELECT * FROM CUSTOMERORDER WHERE pickup_customer_id=? OR delivery_customer_id=?;");
                    s.setInt(1, c.getID());
                    s.setInt(2, c.getID());

                    ResultSet rs = s.executeQuery();
                    if(rs.next()){
                        return true;
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            return false;
        }

        public boolean checkForDependence(OrderTaker o){
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    PreparedStatement s = con.prepareStatement("SELECT * FROM CUSTOMERORDER WHERE order_Taker_id=?;");
                    s.setInt(1, o.getID());

                    ResultSet rs = s.executeQuery();
                    if(rs.next()){
                        return true;
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            return false;
        }

        public int deleteOrderTaker(int ID){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement orderTest = con.prepareStatement("SELECT * FROM CUSTOMERORDER WHERE order_Taker_id=?;");
                    orderTest.setInt(1, ID);
                    ResultSet ordRs = orderTest.executeQuery();
                    if(ordRs.next()){
                        return 1;
                    }
                    PreparedStatement statement = con.prepareStatement("DELETE FROM ORDERTAKER WHERE orderTaker_id=?;");
                    PreparedStatement ustatement = con.prepareStatement("DELETE FROM USERCREDENTIALS WHERE orderTaker_id=?;");
                    statement.setInt(1, ID);
                    ustatement.setInt(1, ID);
                    statement.execute();
                    ustatement.execute();
                    return 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return 2;
        }

        public ArrayList<OrderTaker> getOrderTakers(boolean Active) {
            ArrayList<OrderTaker> orderTakers = new ArrayList<>();
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM ORDERTAKER "+((Active)?" WHERE isActive = TRUE;":";"));
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

        public void updateOrderTaker(OrderTaker o, String username, String password, int permissions) {

            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    PreparedStatement s = con.prepareStatement("UPDATE ORDERTAKER SET orderTaker_name=?, isActive=? WHERE orderTaker_id=?;");
                    s.setString(1,o.getName());
                    s.setBoolean(2, o.isActive());
                    s.setInt(3, o.getID());
                    s.execute();

                    PreparedStatement su = con.prepareStatement("UPDATE USERCREDENTIALS SET username=?, pass=?, permissions=? WHERE orderTaker_id=?;");
                    su.setString(1, username);
                    su.setString(2, password);
                    su.setInt(3, permissions);
                    su.setInt(4, o.getID());
                    su.execute();
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());

                }
            }
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

        public ArrayList<CustomerOrder> getOrders(Courier courier, java.util.Date startDate, java.util.Date endDate){
            ArrayList<CustomerOrder> orders = new ArrayList<>();
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    ResultSet rs = statement.executeQuery("SELECT * FROM CUSTOMERORDER WHERE courier_id="
                            + courier.getID()+" AND pick_up_time BETWEEN DATE('"
                            + sdf.format(startDate).toString() + "') AND DATE('"
                            + sdf.format(endDate).toString() + "');");
                    System.out.println("SELECT * FROM CUSTOMERORDER WHERE courier_id="
                            + courier.getID()+" AND pick_up_time BETWEEN DATE('"
                            + sdf.format(startDate).toString() + "') AND DATE('"
                            + sdf.format(endDate).toString() + "');");
                    while(rs.next()){
                        int s = rs.getInt("order_status");

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

        public ArrayList<CustomerOrder> getOrders(Customer customer){
            ArrayList<CustomerOrder> orders = new ArrayList<>();
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM CUSTOMERORDER WHERE courier_id="+ customer.getID()+" AND status<>3;" );
                    while(rs.next()){
                        int s = rs.getInt("order_status");

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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                    statement.setString(7, sdf.format(order.getPickupTime()).toString());
                    statement.setInt(8, order.getStatus().getValue());
                    statement.execute();
                    con.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
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
                            ((order.getCourier() != null)?"courier_id="+order.getCourier().getID()+", ":"")+
                            "order_status=" + order.getStatus().getValue() +
                            " WHERE order_id=?");
                    statement.setInt(1, order.getID());

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

        public void createUser(String username, String password, String name, int permissions){
            Connection con = new DatabaseConnection().Connect();
            if (con != null) {
                try {
                    PreparedStatement ostatement = con.prepareStatement("INSERT INTO ORDERTAKER (orderTaker_name, isActive) VALUES (?,1);");
                    ostatement.setString(1, name);
                    ostatement.execute();

                    PreparedStatement istatement = con.prepareStatement("SELECT LAST_INSERT_ID();");
                    ResultSet rs = istatement.executeQuery();
                    if(rs.next()){
                        if(rs.getInt(1) != 0){
                            PreparedStatement ustatement = con.prepareStatement("INSERT INTO USERCREDENTIALS (username, pass, permissions, orderTaker_id) VALUES (?,?,?,?);");
                            ustatement.setString(1, username);
                            ustatement.setString(2, password);
                            ustatement.setInt(3, permissions);
                            ustatement.setInt(4, rs.getInt(1));

                            ustatement.execute();
                        }
                    }
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
                    e.printStackTrace();
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
                    e.printStackTrace();
                    return -1;
                }
            }
            return -1;
        }

        public int getUserID(String username, String password){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("SELECT user_id FROM USERCREDENTIALS WHERE username=? AND pass=?");
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    return rs.getInt(1);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            return -1;
        }

        public String getUsername(int ID){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("SELECT username FROM USERCREDENTIALS WHERE orderTaker_id=?;");
                    statement.setInt(1, ID);
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    return rs.getString(1);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        }

        public String getPassword(int ID){
            Connection con = new DatabaseConnection().Connect();
            if(con != null) {
                try {
                    PreparedStatement statement = con.prepareStatement("SELECT pass FROM USERCREDENTIALS WHERE orderTaker_id=?;");
                    statement.setInt(1, ID);
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    return rs.getString(1);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        }


        public Business getBusinessSettings(){
            Connection con = new DatabaseConnection().Connect();
            if(con != null){
                try{
                    PreparedStatement statement = con.prepareStatement("SELECT * FROM BUSINESS");
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    Business b = new Business(rs.getString(1), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4), rs.getInt(5), rs.getFloat(6));
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
                            "bonusGracePeriod=? " +
                            "courier_speed=?");
                    statement.setString(1, b.getAddress());
                    statement.setFloat(2, b.getDeliveryBlockRate());
                    statement.setFloat(3, b.getDeliveryBase());
                    statement.setFloat(4, b.getBonusRate());
                    statement.setFloat(5, b.getBonusGracePeriod());
                    statement.setFloat(6, b.getCourierSpeed());

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