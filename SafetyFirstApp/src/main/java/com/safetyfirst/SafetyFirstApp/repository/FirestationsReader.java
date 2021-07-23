package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationsReader {
    @Autowired
    ReadData readData;
    public void readFirestation() {
        List<Firestation> firestationList = readData.getFirestations();
        System.out.println("firestationList is "+firestationList);
    }
}
