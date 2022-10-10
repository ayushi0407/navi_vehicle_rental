package vehicle;
import java.util.HashMap;

public class Vehicles {
    private HashMap<String, Vehicle> vehicles;

    public Vehicles() {
        this.vehicles = new HashMap<>();
    }
    public Vehicle getVehiclesById(String id) {
        return this.vehicles.getOrDefault(id, null);
    }
    public void putVehicleById(Vehicle vehicle) {
        this.vehicles.put(vehicle.getId(), vehicle);
    }
}

