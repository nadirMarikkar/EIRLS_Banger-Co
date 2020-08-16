package com.example.EIRLS_Banger.Controllers;


import com.example.EIRLS_Banger.Models.Rental;
import com.example.EIRLS_Banger.Services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/rentals")
@RestController
public class RentalController {
    private RentalService rentalService;


    @Autowired
    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    //RENT VEHICLE-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/rentVehicle")
    public ResponseEntity<?> rentVehicle(@RequestBody Rental rentVehicle){

        return rentalService.rentVehicle(rentVehicle) ;
    }



    //GET ALL RENTALS-------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @RequestMapping("/allRentals")
    public List<Rental> getAllRentals(){
        return rentalService.allRentals();
    }


    //GET A SINGLE RENTAL-------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/rental/{rentalId}")
    public Rental getRental(@PathVariable Integer rentalId){
        return rentalService.getRental(rentalId);
    }


    //UPDATE RENTAL-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/updateRental")
    public ResponseEntity<?> updateRental(@RequestBody Rental updateRental){
        return rentalService.updateRental(updateRental);
    }


    //CANCEL RENTAL -------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    @DeleteMapping("/rental/{rentalId}")
//    public void cancelRental(@PathVariable Integer rentalId){
//        rentalService.deleteById(rentalId);
//    }

    //UPDATE STATUSES -------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // ACCEPTED RENTAL

    @PreAuthorize("hasRole('ADMIIN')")
    @PutMapping("/updateRentalStatus/updateAcceptedStatus")
    public ResponseEntity<?> updateAcceptedStatus (@RequestBody Integer updateAcceptedStatus){
        return rentalService.updateAcceptedStatus(updateAcceptedStatus);
    }


    //REJECTED RENTAL

    @PreAuthorize("hasRole('ADMIIN')")
    @PutMapping("/updateRentalStatus/updateRejectedStatus")
    public ResponseEntity<?> updateRejectedStatus (@RequestBody Integer updateRejectedStatus){
        return rentalService.updateRejectedStatus(updateRejectedStatus);
    }

    //COLLECTED VEHICLE

    @PreAuthorize("hasRole('ADMIIN')")
    @PutMapping("/updateRentalStatus/updateCollectedStatus")
    public ResponseEntity<?> updateCollectedStatus (@RequestBody Integer updateCollectedStatus){
        return rentalService.updateCollectedStatus(updateCollectedStatus);
    }


    //RETURNED VEHICLE

    @PreAuthorize("hasRole('ADMIIN')")
    @PutMapping("/updateRentalStatus/updateReturnedStatus")
    public ResponseEntity<?> updateReturnedStatus (@RequestBody Integer updateReturnedStatus){
        return rentalService.updateReturnedStatus(updateReturnedStatus);
    }



}
