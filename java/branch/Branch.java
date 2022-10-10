package branch;
import java.util.ArrayList;
import java.util.HashMap;

import vehicle.Vehicle;

public class Branch {
    
    private String branchName;
    private String[] vehicleTypes;
    private HashMap<String, Vehicle> vehiclesMapping;

    public Branch(String branchName, String[] vehicleTypes) {
        this.branchName = branchName;
        this.vehicleTypes = vehicleTypes;
        this.vehiclesMapping = new HashMap<>();
    }

    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String[] getVehicleTypes() {
        return vehicleTypes;
    }
    public void setVehicleTypes(String[] vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }
    public HashMap<String, Vehicle> getVehiclesMapping() {
        return vehiclesMapping;
    }
    public void setVehiclesMapping(HashMap<String, Vehicle> vehiclesMapping) {
        this.vehiclesMapping = vehiclesMapping;
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
