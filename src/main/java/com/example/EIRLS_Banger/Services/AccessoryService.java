package com.example.EIRLS_Banger.Services;

import com.example.EIRLS_Banger.Models.Accessory;
import com.example.EIRLS_Banger.Repositories.AccessoryRepository;
import com.example.EIRLS_Banger.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service // declaring a service class. a stereo type annotation
public class AccessoryService {

    private AccessoryRepository accessoryRepository;

    @Autowired
    public AccessoryService(AccessoryRepository accessoryRepository){
        this.accessoryRepository = accessoryRepository;
    }


    //ADD ACCESSORY-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public ResponseEntity<?> addAccessory(Accessory newAccessory) {
        if (accessoryRepository.existsById(newAccessory.getAccessoryId())){
            return ResponseEntity.ok().body(new MessageResponse("Accessory is already added!!!"));
        } else {
            Accessory accessory = new Accessory();

            accessory.setAccessoryName(newAccessory.getAccessoryName());
            accessory.setAccessoryDescription(newAccessory.getAccessoryDescription());
            accessory.setQuantity(newAccessory.getQuantity());
            accessory.setAvailability(newAccessory.getAvailability());
            //save
            accessoryRepository.save(accessory);
        }
        return ResponseEntity.ok().body(new MessageResponse("Successfully added"));

    }


    //GET ALL ACCESSORIES-------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public List<Accessory> allAccessories(){
        //now this will return all the list of accessories from ur db

        List<Accessory> accessoryList = accessoryRepository.findAll();
        return accessoryList;
    }




    //GET A SINGLE ACCESSORY-------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public Accessory getAccessory(Integer accessoryId){
        if(accessoryRepository.existsById(accessoryId)){
            Accessory accessory = accessoryRepository.findById(accessoryId).get();
            return accessory;
        } else {
            return null;
        }

    }




    //UPDATE ACCESSORY-------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public ResponseEntity<?>
    updateAccessory(Accessory updateAccessory){
        if (accessoryRepository.existsById(updateAccessory.getAccessoryId())){
            //now the accessory ur search to uodate is there in the db
            //so get that accessory entire data and assing it to accessory object
            //so this accessory object has the details of the specific id


            Accessory accessory = accessoryRepository.findById(updateAccessory.getAccessoryId()).get();
            accessory.setAccessoryName(updateAccessory.getAccessoryName());
            accessory.setAccessoryDescription(updateAccessory.getAccessoryDescription());
            accessory.setQuantity(updateAccessory.getQuantity());
            accessory.setAvailability(updateAccessory.getAvailability());
            //save
            accessoryRepository.save(accessory);
            return ResponseEntity.ok().body(new MessageResponse("Successfully updated"));

        } else {
            return ResponseEntity.ok().body(new MessageResponse("Accessory is not available"));
        }
    }




    //DELETE ACCESSORY-------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public void deleteById(Integer accessoryId){
        accessoryRepository.deleteById(accessoryId);
    }

}
