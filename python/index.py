from rental_service import RentalService

if __name__=='__main__':

    rental_service_object = RentalService()


    #  add branches
    rental_service_object.add_branch(1, "branch_1", ["CAR", "BIKE", "VAN"])
    rental_service_object.add_branch(2, "branch_2", ["CAR", "VAN"])
    rental_service_object.add_branch(3, "branch_3", ["CAR","BIKE"])

    #  add vehicles branch 1
    rental_service_object.add_vehicle(1, 1, "CAR", 1500)
    rental_service_object.add_vehicle(1, 2, "CAR", 1000)
    rental_service_object.add_vehicle(1, 3, "BIKE", 250)
    rental_service_object.add_vehicle(1, 4, "VAN", 300)
    #  add vehicles branch 2
    rental_service_object.add_vehicle(2, 5, "CAR", 100)
    rental_service_object.add_vehicle(2, 6, "CAR", 200)
    rental_service_object.add_vehicle(2, 7, "VAN", 1000)
    #  add vehicles branch 3
    rental_service_object.add_vehicle(3, 8, "BIKE", 2500)
    rental_service_object.add_vehicle(3, 9, "BIKE", 300)

    # display branch 1 before booking 
    rental_service_object.display_vehicle(1, 1, 5)
    # display branch 1 before booking 
    rental_service_object.display_vehicle(2, 1, 5)
    # display branch 1 before booking 
    rental_service_object.display_vehicle(3, 1, 5)

    # book vehicle from branch 1
    rental_service_object.book_vehicle(1, "CAR", 2, 4) # will pick car 2 as price is less
    rental_service_object.book_vehicle(1, "BIKE", 1, 3)
    rental_service_object.book_vehicle(1, "VAN", 6, 7)

    rental_service_object.display_vehicle(1, 1, 5) # it will show car1, van(since not booked between 1 t0 5)

    # book vehicle from branch 2
    rental_service_object.book_vehicle(2, "BIKE", 1, 3) # not available
    rental_service_object.book_vehicle(2, "VAN", 1, 3) # book
    rental_service_object.book_vehicle(2, "VAN", 4, 7) # book
    rental_service_object.book_vehicle(2, "CAR", 2, 3) # book
    rental_service_object.book_vehicle(2, "CAR", 4, 5) # book

    rental_service_object.display_vehicle(2, 1, 5)

    # book vehicle from branch 2
    rental_service_object.book_vehicle(3, "BIKE", 1, 3) # book
    rental_service_object.book_vehicle(3, "VAN", 1, 3) #  not available

    rental_service_object.display_vehicle(3, 4, 5)  

   