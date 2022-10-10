package vehicle;
import java.util.Comparator;

public class VehicleComparator implements Comparator<Vehicle> {
 
    @Override
    public int compare(Vehicle v1, Vehicle v2) {
        return (int)(v1.getPrice() - v2.getPrice());
    }
}
 

