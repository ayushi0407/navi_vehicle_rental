package booking;
import java.util.HashMap;

public class VehicleBooking {
    private HashMap<String, Booking> bookedVehicles;

    public VehicleBooking() {
        this.bookedVehicles = new HashMap<>();
    }
    public void addBooking(String vehicleId, Booking booking) {
        this.bookedVehicles.put(vehicleId, booking);
    }
    public boolean isVehicleAvailable(String vehicleId, int startTime, int endTime) {
        if(!this.bookedVehicles.containsKey(vehicleId)){
            return true;
        }
        Booking booking = this.bookedVehicles.get(vehicleId);
        if(booking.getStartTime()>= endTime || booking.getEndTime()<= startTime){
            return true;
        }
        return false;
    }
}
