package branch;
import java.util.ArrayList;
import java.util.HashMap;

import vehicle.Vehicle;

public class Branch {
    private String branchName;


private String[] vehicleTypes;

private int id;
private HashMap<Integer, Vehicle> vehiclesMapping;

public Branch(String branchName, String[] vehicleTypes, int id) {
    this.branchName = branchName;
    this.vehicleTypes = vehicleTypes;
    this.id = id;
    this.vehiclesMapping = new HashMap<>();
}
public String[] getVehicleTypes() {
    return vehicleTypes;
}
public void setVehicleTypes(String[] vehicleTypes) {
    this.vehicleTypes = vehicleTypes;
}
public HashMap<Integer, Vehicle> getVehiclesMapping() {
    return vehiclesMapping;
}
public void setVehiclesMapping(HashMap<Integer, Vehicle> vehiclesMapping) {
    this.vehiclesMapping = vehiclesMapping;
}
public String getBranchName() {
    return branchName;
}
public void setBranchName(String branchName) {
    this.branchName = branchName;
}
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}

public void addVehicle(Vehicle vehicle) {
    this.vehiclesMapping.put(vehicle.getId(), vehicle);
}
public boolean isVehicleAvailable() {
    return !this.vehiclesMapping.isEmpty();
}
public ArrayList<Vehicle> getVehicles() {
    return new ArrayList<Vehicle>( this.vehiclesMapping.values());
}
}
