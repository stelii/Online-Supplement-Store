package proiect.fis.store.model;

public class Order {
    private String name ;
    private double price ;
    private int quantity;
    private String deliveryStatus ;
    private String username ;

    public Order(String name, double price, int quantity, String deliveryStatus, String username) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.deliveryStatus = deliveryStatus;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getUsername() {
        return username;
    }
}
