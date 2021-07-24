package com.safetyfirst.SafetyFirstApp.controller;

import com.safetyfirst.SafetyFirstApp.model.Firestation;
import com.safetyfirst.SafetyFirstApp.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class FirestationsController {
    
    @Autowired
    FirestationService firestationService;
    
    @GetMapping("/firestation/all")
    public List<Firestation> readFirestations() {
        return firestationService.readFirestations();
    }
    
    @DeleteMapping("/firestation")
    public List<Firestation> deleteFirestation(@RequestBody HashMap<String, String> addressOrStation) {
        return firestationService.deleteFirestation(addressOrStation);
    }
    
    @PutMapping("/firestation")
    public List<Firestation> modifyFirestation(@RequestParam String address, @RequestParam String station) {
        return firestationService.modifyFirestation(address, station);
    }
    
    @PostMapping("/firestation")
    public List<Firestation> addFirestation(@RequestParam String address, @RequestParam String station) {
        return firestationService.addFirestation(address, station);
    }
}
