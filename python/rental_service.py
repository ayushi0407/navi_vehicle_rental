import sys

class Branches:

    def __init__(self):
        self.branches = {}

    def add_branch(self, branch_id, branch_name, vehicles_type):
        self.branches[branch_id] = { "branch_name": branch_name, "vehicles_type": vehicles_type }

class Vehicles:

    def __init__(self):
        self.vehicles = {}

    def add_vehicle(self, vehicle_id, vehicle_type, price):
        self.vehicles[vehicle_id] = { "vehicle_type": vehicle_type, "price": price }
        return True
    
    def get_vehicle(self, vehicle_id):
        return  self.vehicles[vehicle_id]

class BranchesVehiclesMapping:

    def __init__(self):
        self.branch_vehicle_mapping = {}

    def add_vehicle_to_branch(self, branch_id, vehicle_id):
        if branch_id not in self.branch_vehicle_mapping:
            self.branch_vehicle_mapping[branch_id] = [vehicle_id]
        else:
            self.branch_vehicle_mapping[branch_id].append(vehicle_id)
    
    def is_vehicles_to_branch_available(self, branch_id):
        if branch_id not in self.branch_vehicle_mapping:
            return False
        return True

    def get_vehicles_to_branch(self, branch_id):
        return self.branch_vehicle_mapping[branch_id]

class VehicleBooking:

    def __init__(self):
        self.booked_vehicle = {}

    def add_booking(self, vehicle_id, start_time, end_time):
        self.booked_vehicle[vehicle_id] = { "start_time": start_time, "end_time": end_time }
    
    def is_vehicle_available(self, vehicle_id, start_time, end_time):
        if vehicle_id not in self.booked_vehicle:
            return True
        else:
            booking_details =  self.booked_vehicle[vehicle_id]
            if booking_details["start_time"] >= end_time  or booking_details["end_time"] <= start_time :
                return True
            return False

class RentalService(Branches, Vehicles, BranchesVehiclesMapping, VehicleBooking):

    def __init__(self):
        Branches.__init__(self)
        Vehicles.__init__(self)
        BranchesVehiclesMapping.__init__(self)
        VehicleBooking.__init__(self)

    def add_branch(self, branch_id, branch_name, vehicles_type):
        Branches.add_branch(self, branch_id, branch_name, vehicles_type)
        print("Branch added successfully")
        
    def add_vehicle(self, branch_id, vehicle_id, vehicle_type, price):
        Vehicles.add_vehicle(self, vehicle_id, vehicle_type, price)
        BranchesVehiclesMapping.add_vehicle_to_branch(self, branch_id, vehicle_id)
        print("Vehicle added successfully")

    def get_booking_price(self, price, start_time, end_time):
        return ((end_time - start_time) * price)

    def book_vehicle(self, branch_id, vehicle_type, start_time, end_time):
        if BranchesVehiclesMapping.is_vehicles_to_branch_available(self, branch_id) == True:
            vehicle_id_list = BranchesVehiclesMapping.get_vehicles_to_branch(self, branch_id)

            booking_vehicle_id = False
            vehicle_base_price = sys.maxsize
            for vehicle_id in vehicle_id_list:
                vehicle_detail = Vehicles.get_vehicle(self, vehicle_id)
                if vehicle_detail["vehicle_type"] == vehicle_type:
                    if VehicleBooking.is_vehicle_available(self, vehicle_id, start_time, end_time) == True:
                        if vehicle_base_price > vehicle_detail["price"]:
                            vehicle_base_price = vehicle_detail["price"]
                            booking_vehicle_id = vehicle_id
                      
            if booking_vehicle_id:
                VehicleBooking.add_booking(self, booking_vehicle_id, start_time, end_time)
                print(self.get_booking_price(vehicle_base_price, start_time, end_time))
            else:
                print("-1")
        else:
            print("Invalid Branch")

    def display_vehicle(self, branch_id, start_time, end_time):
        if BranchesVehiclesMapping.is_vehicles_to_branch_available(self, branch_id) == True:
            vehicle_id_list = BranchesVehiclesMapping.get_vehicles_to_branch(self, branch_id)

            available_vehicles = {}
            for vehicle_id in vehicle_id_list:
                if VehicleBooking.is_vehicle_available(self, vehicle_id, start_time, end_time) == True:
                    vehicle_detail = Vehicles.get_vehicle(self, vehicle_id)
                    available_vehicles[vehicle_id]  = vehicle_detail

            if len(available_vehicles) > 0:
                sorted_available_vehicles = sorted(available_vehicles.items(), key = lambda x: x[1]['price'])
                for vehicle in sorted_available_vehicles:
                    print(vehicle)
            else:
                print("No vehicle available")
        else:
            print("Invalid Branch")

   