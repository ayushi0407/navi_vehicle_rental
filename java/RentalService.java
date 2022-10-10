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
        System.out.println("Branch added successfully: " + branch.getBranchName());
    }

    public void addVehicleToBranch(int branchId, Vehicle vehicle) {
        Branch branch = this.branches.getBranch(branchId);
        this.vehicles.putVehicleById(vehicle);
        branch.addVehicle(vehicle);
        System.out.println("Vehicle added successfully to branch: " + branch.getBranchName());
    }

    public double getBookingPrice(double price, int start_time, int end_time){
        return ((end_time - start_time) * price);
    }

    public void bookVehicle(int branchId, String vehicleType, int startTime, int endTime){
        Branch branch = this.branches.getBranch(branchId);
        ArrayList<Vehicle> branchVehicles= null;
        Integer availableVechileId = null;
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
                System.out.println("Booking price: "+ this.getBookingPrice(basePrice, startTime, endTime));
            } else {
                System.out.println("-1");
            }
        } else{
            System.out.println("Invalid Branch");
        }
    }

    public void displayVehicle(int branchId, int startTime, int endTime) {
        Branch branch = this.branches.getBranch(branchId);
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
                System.out.println("No vehicle available");
                return;
            }
            availableVehicles.sort(new VehicleComparator());
            System.out.println("Available vechiles: ");
            for(Vehicle vehicle: availableVehicles) {
                System.out.println(vehicle.getId());
            }
        } else {
            System.out.println("Invalid branch ");
        }
        
    }

    public static void main(String[] args) {
        RentalService rentalService = new RentalService();

        // add branch
        rentalService.addBranch(new Branch("branch_1", new String[]{"CAR", "BIKE", "VAN"}, 1));
        rentalService.addBranch(new Branch("branch_2", new String[]{"CAR", "VAN"}, 2));
        rentalService.addBranch(new Branch("branch_3", new String[]{"CAR", "BIKE"}, 3));

        //  add vehicles branch 1
        rentalService.addVehicleToBranch(1, new Vehicle(1500,"CAR",1));
        rentalService.addVehicleToBranch(1, new Vehicle(1000,"CAR",2));
        rentalService.addVehicleToBranch(1, new Vehicle(250,"BIKE",3));
        rentalService.addVehicleToBranch(1, new Vehicle(300,"VAN",4));
        //  add vehicles branch 2
        rentalService.addVehicleToBranch(2, new Vehicle(100,"CAR",5));
        rentalService.addVehicleToBranch(2, new Vehicle(200,"CAR",6));
        rentalService.addVehicleToBranch(2, new Vehicle(1000,"VAN",7));
        //  add vehicles branch 3
        rentalService.addVehicleToBranch(3, new Vehicle(2500,"BIKE",8));
        rentalService.addVehicleToBranch(3, new Vehicle(300,"BIKE",9));
        
        //display branch 1 before booking 
        rentalService.displayVehicle(1, 1, 5);
        //display branch 2 before booking 
        rentalService.displayVehicle(2, 1, 5);
        //display branch 3 before booking 
        rentalService.displayVehicle(3, 1, 5);

        // book vehicle from branch 1
        rentalService.bookVehicle(1, "CAR", 2, 4); 
        rentalService.bookVehicle(1, "BIKE", 1, 3);
        rentalService.bookVehicle(1, "VAN", 6, 7);

        //display after booking
        rentalService.displayVehicle(1, 1, 5);

        // book vehicle from branch 1
        rentalService.bookVehicle(2, "BIKE", 1, 3); 
        rentalService.bookVehicle(2, "VAN", 1, 3);
        rentalService.bookVehicle(2, "VAN", 4, 7);
        rentalService.bookVehicle(2, "CAR", 2, 3);
        rentalService.bookVehicle(2, "CAR", 4, 5);

        //display after booking
        rentalService.displayVehicle(2, 1, 5);

        // book vehicle from branch 1
        rentalService.bookVehicle(1, "BIKE", 1, 3); 
        rentalService.bookVehicle(1, "VAN", 1, 3);
        

        //display after booking
        rentalService.displayVehicle(3, 4, 5);

        
    }
}
