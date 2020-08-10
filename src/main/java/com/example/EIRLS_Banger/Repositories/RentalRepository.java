package com.example.EIRLS_Banger.Repositories;

import com.example.EIRLS_Banger.Models.Rental;
import com.example.EIRLS_Banger.Models.User;
import com.example.EIRLS_Banger.Models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    boolean existsByVehicle(Vehicle vehicle);
//    boolean existsByUserDrivingLicence(String licwen);
    //did u get. what you said? yep answer the call

}
