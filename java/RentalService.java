import java.util.ArrayList;

import booking.Booking;
import booking.VehicleBooking;
import branch.Branch;
import branch.Branches;
import vehicle.Vehicle;
import vehicle.VehicleComparator;
import vehicle.Vehicles;

public class RentalService {
    
    private Branches branches;
    private Vehicles vehicles;
    private VehicleBooking vehicleBooking;

    public RentalService() {
        this.branches = new Branches();
        this.vehicleBooking = new VehicleBooking();
        this.vehicles = new Vehicles();
    }

    public void addBranch(Branch branch) {
        this.branches.addBranch(branch);
        System.out.println(true);
    }

    public void addVehicleToBranch(String branchName, Vehicle vehicle) {
        Branch branch = this.branches.getBranch(branchName);
        this.vehicles.putVehicleById(vehicle);
        branch.addVehicle(vehicle);
        System.out.println(true);
    }

    public double getBookingPrice(double price, int start_time, int end_time){
        return ((end_time - start_time) * price);
    }

    public void bookVehicle(String branchName, String vehicleType, int startTime, int endTime){
        Branch branch = this.branches.getBranch(branchName);
        ArrayList<Vehicle> branchVehicles= null;
        String availableVechileId = null;
        Double basePrice  = Double.MAX_VALUE;
        if(branch.isVehicleAvailable()) {
            branchVehicles = branch.getVehicles();
        
            for(Vehicle vehicle: branchVehicles) {
                if(vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                    if(vehicleBooking.isVehicleAvailable(vehicle.getId(), startTime, endTime)){
                        if(vehicle.getPrice()< basePrice) {
                            basePrice = vehicle.getPrice();
                            availableVechileId =vehicle.getId();
                        }
                    }
                }
            }
            if(availableVechileId!= null) {
                this.vehicleBooking.addBooking(availableVechileId, new Booking(startTime, endTime));
                System.out.println(this.getBookingPrice(basePrice, startTime, endTime));
            } else {
                System.out.println("-1");
            }
        }
    }

    public void displayVehicle(String branchName, int startTime, int endTime) {
        Branch branch = this.branches.getBranch(branchName);
        ArrayList<Vehicle> branchVehicles= null;
        ArrayList<Vehicle> availableVehicles= new ArrayList<>();

        if(branch.isVehicleAvailable()) {
            branchVehicles = branch.getVehicles();
            for(Vehicle vehicle: branchVehicles) {
                if(vehicleBooking.isVehicleAvailable(vehicle.getId(), startTime, endTime)){
                    availableVehicles.add(vehicle);
                }
            }
            if(availableVehicles.size()==0) {
                return;
            }
            availableVehicles.sort(new VehicleComparator());
            for(Vehicle vehicle: availableVehicles) {
                System.out.println(vehicle.getId());
            }
        } 
    }

    public static void main(String[] args) {
        RentalService rentalService = new RentalService();

        // add branch
        rentalService.addBranch(new Branch("B1", new String[]{"CAR", "BIKE", "VAN"}));
        rentalService.addBranch(new Branch("B2", new String[]{"CAR", "VAN"}));
        rentalService.addBranch(new Branch("B3", new String[]{"CAR", "BIKE"}));

        //  add vehicles branch 1
        rentalService.addVehicleToBranch("B1", new Vehicle(1500,"CAR","V1"));
        rentalService.addVehicleToBranch("B1", new Vehicle(1000,"CAR","V2"));
        rentalService.addVehicleToBranch("B1", new Vehicle(250,"BIKE","V3"));
        rentalService.addVehicleToBranch("B1", new Vehicle(300,"VAN","V4"));
        //  add vehicles branch 2
        rentalService.addVehicleToBranch("B2", new Vehicle(100,"CAR","V5"));
        rentalService.addVehicleToBranch("B2", new Vehicle(200,"CAR","V6"));
        rentalService.addVehicleToBranch("B2", new Vehicle(1000,"VAN","V7"));
        //  add vehicles branch 3
        rentalService.addVehicleToBranch("B3", new Vehicle(2500,"BIKE","V8"));
        rentalService.addVehicleToBranch("B3", new Vehicle(300,"BIKE","V9"));
        
        //display branch 1 before booking 
        rentalService.displayVehicle("B1", 1, 5);
        //display branch 2 before booking 
        rentalService.displayVehicle("B2", 1, 5);
        //display branch 3 before booking 
        rentalService.displayVehicle("B3", 1, 5);

        // book vehicle from branch 1
        rentalService.bookVehicle("B1", "CAR", 2, 4); 
        rentalService.bookVehicle("B1", "BIKE", 1, 3);
        rentalService.bookVehicle("B1", "VAN", 6, 7);

        //display after booking
        rentalService.displayVehicle("B1", 1, 5);

        // book vehicle from branch 1
        rentalService.bookVehicle("B2", "BIKE", 1, 3); 
        rentalService.bookVehicle("B2", "VAN", 1, 3);
        rentalService.bookVehicle("B2", "VAN", 4, 7);
        rentalService.bookVehicle("B2", "CAR", 2, 3);
        rentalService.bookVehicle("B2", "CAR", 4, 5);

        //display after booking
        rentalService.displayVehicle("B2", 1, 5);

        // book vehicle from branch 1
        rentalService.bookVehicle("B3", "BIKE", 1, 3); 
        rentalService.bookVehicle("B3", "VAN", 1, 3);
        
        //display after booking
        rentalService.displayVehicle("B3", 4, 5);     
    }
}
