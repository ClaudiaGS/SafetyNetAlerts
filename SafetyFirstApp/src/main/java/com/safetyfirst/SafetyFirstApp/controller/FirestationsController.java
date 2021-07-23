package com.safetyfirst.SafetyFirstApp.controller;

import com.safetyfirst.SafetyFirstApp.model.Firestation;
import com.safetyfirst.SafetyFirstApp.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FirestationsController {
    
    @Autowired
    FirestationService firestationService;
    
    @GetMapping("/firestation/all")
    public List<Firestation> readFirestations() {
        return firestationService.readFirestations();
    }
   
}
