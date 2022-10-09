from rental_service import RentalService

if __name__=='__main__':

    rental_service_object = RentalService()

    #  add branches
    rental_service_object.add_branch(1, "branch_1", ["CAR", "BIKE", "VAN"])

    #  add vehicles
    rental_service_object.add_vehicle(1, 1, "CAR", 1500)
    rental_service_object.add_vehicle(1, 2, "CAR", 1000)
    rental_service_object.add_vehicle(1, 3, "BIKE", 250)
    rental_service_object.add_vehicle(1, 4, "VAN", 300)

    # display before booking
    rental_service_object.display_vehicle(1, 1, 5)

    # book vehicle
    rental_service_object.book_vehicle(1, "CAR", 2, 4) # will pick car 2 as price is less
    rental_service_object.book_vehicle(1, "BIKE", 1, 3)
    rental_service_object.book_vehicle(1, "VAN", 6, 7)

    rental_service_object.display_vehicle(1, 1, 5) # it will show car1, van(since not booked between 1 t0 5)