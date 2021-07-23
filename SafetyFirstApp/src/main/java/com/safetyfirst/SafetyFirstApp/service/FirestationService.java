package com.safetyfirst.SafetyFirstApp.service;

import com.safetyfirst.SafetyFirstApp.model.Firestation;
import com.safetyfirst.SafetyFirstApp.repository.FirestationsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {
    @Autowired
    FirestationsProxy firestationsProxy;
    
    public List<Firestation> readFirestations(){
        return firestationsProxy.readFirestations();
    }
  
}
