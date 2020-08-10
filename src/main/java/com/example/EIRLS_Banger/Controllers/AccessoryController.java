package com.example.EIRLS_Banger.Controllers;
import com.example.EIRLS_Banger.Models.Accessory;
import com.example.EIRLS_Banger.Services.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("api/accessories")
//this is the main api for vehicle so in postman u have to give api/vehicle/ ur other vehicle url of this vehcile controller
@RestController
// This annotation makes this java class a controller ( a rest controller ). A spring MVC annotation. being restcontroller means can have methods which maps URL requests to particular methods.

public class AccessoryController {
    private AccessoryService accessoryService;


    @Autowired
    public AccessoryController(AccessoryService accessoryService){
        this.accessoryService = accessoryService;
    }



    //ADD ACCESSORY-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    // this is onlt for admin
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addAccessory")
    public ResponseEntity<?> addAccessory(@RequestBody Accessory newAccessory) {
        return accessoryService.addAccessory(newAccessory);
    }


//
    //GET ALL ACCESSORIES-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @RequestMapping("/allAccessories")
    public List<Accessory> getAllAccessories(){
        return accessoryService.allAccessories();
    }
//
//
//
//    //GET A SINGLE ACCESSORY-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @RequestMapping("/accessory/{accessoryId}")
    public Accessory getAccessory(@PathVariable Integer accessoryId){
        return accessoryService.getAccessory(accessoryId);
    }
//
//
//
//
//    //UPDATE ACCESSORY-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateAccessory")
    public ResponseEntity<?> updateAccessory(@RequestBody Accessory updateAccessory) {
        return accessoryService.updateAccessory(updateAccessory);
    }
//
//
//
//    //DELETE ACCESSORY-------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-accessory/{accessoryId}")
    public void deleteAccessory(@PathVariable Integer accessoryId){
        accessoryService.deleteById(accessoryId);
    }
}
