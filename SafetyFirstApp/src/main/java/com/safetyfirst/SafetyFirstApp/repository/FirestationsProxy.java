package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FirestationsProxy implements IFirestationsProxy {
    @Autowired
    IRecoveredData recoveredData;
    
    @Override
    public List<Firestation> readFirestations() {
        List<Firestation> firestations = recoveredData.getFirestations();
        return firestations;
    }
    
    @Override
    public List<Firestation> deleteFirestation(HashMap<String, String> addressOrStation) {
        List<Firestation> firestations = recoveredData.getFirestations();
        ArrayList<Firestation> toRemove = new ArrayList<Firestation>();
        for (Map.Entry<String, String> entry : addressOrStation.entrySet()) {
            switch (entry.getKey()) {
                case "address":
                    for (Firestation f : firestations) {
                        if (f.getAddress().equals(entry.getValue())) {
                            toRemove.add(f);
                        }
                    }
                    break;
                case "station":
                    for (Firestation f : firestations) {
                        if (f.getStation().equals(entry.getValue())) {
                            toRemove.add(f);
                        }
                    }
                    break;
                default:
                    System.out.println("Trying to delete unexisting station or address");
            }
        }
        for (Firestation f : toRemove) {
            firestations.remove(f);
        }
        return firestations;
    }
    
    @Override
    public List<Firestation> modifyFirestation(String address, String station) {
        List<Firestation>firestationList=recoveredData.getFirestations();
        for(Firestation f:firestationList){
            if(f.getAddress().equals(address)){
                f.setStation(station.trim());
            }
        }
        return firestationList;
    }
    
    @Override
    public List<Firestation> addFirestation(String address, String station) {
        List<Firestation>firestationList=recoveredData.getFirestations();
        Firestation f=new Firestation();
        f.setAddress(address);
        f.setStation(station);
        firestationList.add(f);
        return firestationList;
    }
    @Override
    public void setRecoveredData(IRecoveredData recoveredData) {
        this.recoveredData = recoveredData;
    }
}
