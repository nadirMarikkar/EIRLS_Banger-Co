package com.example.EIRLS_Banger.Services;


import com.example.EIRLS_Banger.Models.Rental;
import com.example.EIRLS_Banger.Repositories.RentalRepository;
import com.example.EIRLS_Banger.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RentalService {

    private RentalRepository rentalRepository;

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
        //check if the time renting is not less than 5 hrs

    //u dont check whether a rental is vailable by rental id
//        when checcking a rental u should check if the reant table have that particular vehicle id
        if (rentalRepository.existsByVehicle(newRent.getVehicle())){
//                (newRent.getVehicle())){
            return ResponseEntity.badRequest().body(new MessageResponse("cant rent"));
        } else

    {
        Rental rental = new Rental();


        rental.setPickupDate(newRent.getPickupDate());
        rental.setPickupTime(newRent.getPickupTime());
        rental.setRentalType(newRent.getRentalType());
        rental.setRentalDuration(newRent.getRentalDuration());
        rental.setReturnDueDate(newRent.getReturnDueDate());
        rental.setReturnDueTime(newRent.getReturnDueTime());
        rentalRepository.save(rental);

        return ResponseEntity.ok().body(new MessageResponse("Successfully Rented"));

    }
//
}



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

}
