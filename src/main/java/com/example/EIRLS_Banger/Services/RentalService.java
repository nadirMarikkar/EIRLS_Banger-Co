package com.example.EIRLS_Banger.Services;


import com.example.EIRLS_Banger.Models.Rental;
import com.example.EIRLS_Banger.Models.User;
import com.example.EIRLS_Banger.Models.Vehicle;
import com.example.EIRLS_Banger.Repositories.RentalRepository;
import com.example.EIRLS_Banger.Repositories.UserRepository;
import com.example.EIRLS_Banger.Repositories.VehicleRepository;
import com.example.EIRLS_Banger.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private RentalRepository rentalRepository;
    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    //RENT VEHICLE-------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public ResponseEntity<?> rentVehicle(Rental newRent) {
        //from to get the differnece and see if it is less than 5


        //conditions u sh0ould check before saving a rent
        //check if the vehicle that user ask is available for renting
        //check if the equipment is available for that renting perdio


        //u dont check whether a rental is vailable by rental id
//        when checcking a rental u should check if the reant table have that particular vehicle id
        if (rentalRepository.existsByVehicle(newRent.getVehicle())) {
//                (newRent.getVehicle())){
            return ResponseEntity.badRequest().body(new MessageResponse("Cannot rent"));
        } else {
           // if (userRepository.existsByUsername(newRent.getUser().getUsername())) {

                if (!userRepository.existsByStatus(newRent.getUser())) //if user is not blaklist
            //        if(!vehicleRepository.existsById(newRent.getVehicle().getVehicleId()))
                {
                    Rental rental = new Rental();


                    rental.setPickupDate(newRent.getPickupDate());
                    rental.setPickupTime(newRent.getPickupTime());
                    rental.setRentalType(newRent.getRentalType());
                    rental.setRentalDuration(newRent.getRentalDuration());
                    rental.setReturnDueDate(newRent.getReturnDueDate());
                    rental.setReturnDueTime(newRent.getReturnDueTime());
                   // rental.setRentalStatus(newRent.getRentalStatus());
                    rental.setRentalCost(newRent.getRentalCost());


                    rentalRepository.save(rental);

                    return ResponseEntity.ok().body(new MessageResponse("Successfully Rented"));
                } else {
                    return ResponseEntity.badRequest().body(new MessageResponse("Cannot Rent. You are BLACKLISTED"));
                }


            }

        }
//






    //GET ALL RENTALS-------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public List<Rental> allRentals(){
        List<Rental> rentalList = rentalRepository.findAll();
        return rentalList;
    }



    //GET A SINGLE RENTAL-------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public Rental getRental(Integer rentalId){
        if (rentalRepository.existsById(rentalId)){
            Rental rental= rentalRepository.findById(rentalId).get(); //gets singleoops
            return rental;
        } else {
            return null;
        }
    }


    //UPDATE RENTAL-------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public ResponseEntity<?> updateRental( Rental updateRental){
        if (rentalRepository.existsById(updateRental.getRentalId())){

//            to search for a partcular id and get that ids details use findById(Integer id)
            //to get all the list of a particular id use findAll()
            Rental rental = rentalRepository.findById(updateRental.getRentalId()).get();
            rental.setPickupDate(updateRental.getPickupDate());
            rental.setPickupTime(updateRental.getPickupTime());
            rental.setRentalType(updateRental.getRentalType());
            rental.setRentalDuration(updateRental.getRentalDuration());
            rental.setReturnDueDate(updateRental.getReturnDueDate());
            rental.setReturnDueTime(updateRental.getReturnDueTime());
            rental.setRentalStatus("pending");
            rental.setRentalCost(updateRental.getRentalCost());

            rentalRepository.save(rental);
            return ResponseEntity.ok().body(new MessageResponse("Successfully updated"));

        } else {
            return ResponseEntity.ok().body(new MessageResponse("Rental could'nt update"));

        }
    }

    //CANCEL RENTAL-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//
//    public void deleteById(Integer rentalId){
//      rentalRepository.deleteById(rentalId);
//   }

    //UPDATE STATUSES-------------------------------------------------------------------------------------------------------------------------------------------------------------------//


    // ACCEPTED RENTAL

    public ResponseEntity<?> updateAcceptedStatus (Integer rentalId){
        Rental rental= rentalRepository.findById(rentalId).get();

        rental.setRentalStatus("accepted");
        rentalRepository.save(rental);
        return ResponseEntity.ok().body(new MessageResponse("Successfully status updated"));
    }


    //REJECTED RENTAL

    public ResponseEntity<?> updateRejectedStatus (Integer rentalId){
        Rental rental= rentalRepository.findById(rentalId).get();

        rental.setRentalStatus("rejected");
        rentalRepository.save(rental);
        return ResponseEntity.ok().body(new MessageResponse("Successfully status updated"));
    }

    //COLLECTED VEHICLE

    public ResponseEntity<?> updateCollectedStatus (Integer rentalId){
        Rental rental= rentalRepository.findById(rentalId).get();


        if (
                rental.getRentalStatus()== ("accepted")) {

            rental.setRentalStatus("collected");
            rentalRepository.save(rental);
            return ResponseEntity.ok().body(new MessageResponse("Successfully status updated"));
        }
        else {
            return ResponseEntity.ok().body(new MessageResponse("Rental is not accepted"));

        }
    }

    //RETURNED VEHICLE

    public ResponseEntity<?> updateReturnedStatus (Integer rentalId){
        Rental rental= rentalRepository.findById(rentalId).get();


        if (
                rental.getRentalStatus()== ("collected")) {

            rental.setRentalStatus("returned");
            rentalRepository.save(rental);
            return ResponseEntity.ok().body(new MessageResponse("Successfully status updated"));
        }
        else {
            return ResponseEntity.ok().body(new MessageResponse("Vehicle was not collected"));

        }
    }


    //EXTENDED RENTAL
}
