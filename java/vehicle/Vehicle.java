package vehicle;
public class Vehicle {
    private double price;
    private String vehicleType;
    private int id;

public Vehicle(double price, String vehicleType, int id) {
    this.price = price;
    this.vehicleType = vehicleType;
    this.id = id;
}
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public double getPrice() {
    return price;
}
public void setPrice(double price) {
    this.price = price;
}
public String getVehicleType() {
    return vehicleType;
}
public void setVehicleType(String vehicleType) {
    this.vehicleType = vehicleType;
}
}
