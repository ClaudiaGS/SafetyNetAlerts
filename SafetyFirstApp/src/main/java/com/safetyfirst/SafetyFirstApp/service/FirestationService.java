package com.safetyfirst.SafetyFirstApp.service;

import com.safetyfirst.SafetyFirstApp.model.Firestation;
import com.safetyfirst.SafetyFirstApp.repository.FirestationsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FirestationService implements IFirestationService{
    @Autowired
    FirestationsProxy firestationsProxy;
    
    public List<Firestation> readFirestations(){
        return firestationsProxy.readFirestations();
    }
    public List<Firestation> deleteFirestation(HashMap<String, String> addressOrStation){
        return firestationsProxy.deleteFirestation(addressOrStation);
    }
    public List<Firestation> modifyFirestation( String address, String station){
        return firestationsProxy.modifyFirestation(address,station);
    }
    public List<Firestation>addFirestation(String address, String station){
        return firestationsProxy.addFirestation(address,station);
    }
}
