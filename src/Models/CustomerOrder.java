package Models;

import DataManagement.DatabaseManager;

import java.util.Date;

public class CustomerOrder {
    private int order_id;
    private int package_id;
    private Customer pickupCustomer;
    private Customer deliveryCustomer;
    private OrderTaker orderTaker;
    private Courier courier;
    private boolean billToDelivery;
    private String specialInstructions;
    private Date pickupTime;
    private Date estimatedDelivery;
    private Date estimatedDeparture;
    private int distance;
    private float price;
    private Date departure;
    private Date pickup;
    private Date delivery;
    private OrderStatus status;

    public CustomerOrder(int order_id, int package_id, int pickupCustomer, int deliveryCustomer, int orderTaker, int courier, boolean billToDelivery, String specialInstructions, Date pickupTime){
        this.order_id = order_id;
        this.setPackageID(package_id);
        this.setPickupCustomer(pickupCustomer);
        this.setDeliveryCustomer(deliveryCustomer);
        this.setCourier(courier);
        this.setOrderTaker(orderTaker);
        this.setBillToDelivery(billToDelivery);
        this.setSpecialInstructions(specialInstructions);
        this.setPickupTime(pickupTime);
        this.setStatus(OrderStatus.AWAITING_DEPARTURE);
    }

    public CustomerOrder(
            int order_id,
            int package_id,
            int pickupCustomer,
            int deliveryCustomer,
            int orderTaker,
            int courier,
            boolean billToDelivery,
            String specialInstructions,
            Date pickupTime,
            Date estimatedDelivery,
            Date estimatedDeparture,
            int distance,
            float price,
            Date departure,
            Date pickup,
            Date delivery,
            OrderStatus status){
        this.order_id = order_id;
        this.setPackageID(package_id);
        this.setPickupCustomer(pickupCustomer);
        this.setDeliveryCustomer(deliveryCustomer);
        this.setCourier(courier);
        this.setOrderTaker(orderTaker);
        this.setBillToDelivery(billToDelivery);
        this.setSpecialInstructions(specialInstructions);
        this.setPickupTime(pickupTime);
        this.setEstimatedDelivery(estimatedDelivery);
        this.setEstimatedDeparture(estimatedDeparture);
        this.setDistance(distance);
        this.setPrice(price);
        this.setDeparture(departure);
        this.setPickup(pickup);
        this.setDelivery(delivery);
        this.setStatus(status);
    }

    public int getID() {
        return order_id;
    }

    public int getPackageID() {
        return package_id;
    }

    public void setPackageID(int package_id) {
        this.package_id = package_id;
    }

    public Customer getPickupCustomer() {
        return pickupCustomer;
    }

    public void setPickupCustomer(int pickupCustomer) {
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        this.pickupCustomer = q.getCustomer(pickupCustomer);
        q = null;
    }

    public Customer getDeliveryCustomer() {
        return deliveryCustomer;
    }

    public void setDeliveryCustomer(int deliveryCustomer) {
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        this.deliveryCustomer = q.getCustomer(deliveryCustomer);
        q = null;
    }

    public OrderTaker getOrderTaker() {
        return orderTaker;
    }

    public void setOrderTaker(int orderTaker) {
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        this.orderTaker = q.getOrderTaker(orderTaker, false);
        q = null;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(int courier) {
        if(courier != 0) {
            DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
            this.courier = q.getCourier(courier);
            q = null;
        }
    }

    public boolean isBillToDelivery() {
        return billToDelivery;
    }

    public void setBillToDelivery(boolean billToDelivery) {
        this.billToDelivery = billToDelivery;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Date getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public void setEstimatedDelivery(Date estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }

    public Date getEstimatedDeparture() {
        return estimatedDeparture;
    }

    public void setEstimatedDeparture(Date estimatedDeparture) {
        this.estimatedDeparture = estimatedDeparture;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getPickup() {
        return pickup;
    }

    public void setPickup(Date pickup) {
        this.pickup = pickup;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
