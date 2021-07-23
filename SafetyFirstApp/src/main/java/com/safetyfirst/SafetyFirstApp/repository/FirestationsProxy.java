package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.Firestation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@Repository
public class FirestationsProxy implements IFirestationsProxy {
    @Autowired
    IDataRecovery recoveredData;
    
    @Override
    public List<Firestation> readFirestations() {
        List<Firestation> firestations = recoveredData.getFirestations();
        System.out.println("firestations list is " + firestations);
        return firestations;
    }
}
