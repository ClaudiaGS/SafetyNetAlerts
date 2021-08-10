package com.safetynetalerts.SafetyNetAlerts.controller;

import com.safetynetalerts.SafetyNetAlerts.model.Firestation;
import com.safetynetalerts.SafetyNetAlerts.service.IFirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class FirestationController {
    
    private static final Logger logger = LogManager.getLogger(FirestationController.class);
    @Autowired
    IFirestationService firestationService;
    
    @GetMapping("/firestation/all")
    public List<Firestation> readFirestations() {
        logger.debug("Read firestation info from JSON");
        List<Firestation> list = firestationService.readFirestations();
        logger.info("Firestation list is " + list);
        return list;
    }
    
    @DeleteMapping("/firestation")
    public List<Firestation> deleteFirestation(@RequestBody HashMap<String, String> addressOrStation) {
        logger.debug("Delete firestation for " + addressOrStation.toString());
        List<Firestation> list = firestationService.deleteFirestation(addressOrStation);
        logger.info("Firestation list after delete is " + list);
        return list;
    }
    
    @PutMapping("/firestation")
    public List<Firestation> modifyFirestation(@RequestParam String address, @RequestParam String station) {
        logger.debug("Modify firestation: " + address + " goes to station " + station);
        List<Firestation> list = firestationService.modifyFirestation(address, station);
        logger.info("Firestation list after modification is " + list);
        return list;
    }
    
    @PostMapping("/firestation")
    public List<Firestation> addFirestation(@RequestParam String address, @RequestParam String station) {
        logger.debug("Add address " + address + " to station " + station);
        List<Firestation> list = firestationService.addFirestation(address, station);
        logger.info("Firestation list after adding firestation is " + list);
        return list;
    }
}
