package com.safetynetalerts.SafetyNetAlerts.repository;

import com.safetynetalerts.SafetyNetAlerts.model.Firestation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FirestationsProxy implements IFirestationsProxy {
    private static final Logger logger = LogManager.getLogger(FirestationsProxy.class);
    
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
        int loggerIndex = -1;
        for (Map.Entry<String, String> entry : addressOrStation.entrySet()) {
            switch (entry.getKey()) {
                case "address":
                    for (Firestation f : firestations) {
                        if (f.getAddress().equals(entry.getValue())) {
                            loggerIndex = 1;
                            toRemove.add(f);
                        }
                    }
                    break;
                case "station":
                    for (Firestation f : firestations) {
                        if (f.getStation().equals(entry.getValue())) {
                            loggerIndex = 1;
                            toRemove.add(f);
                        }
                        
                    }
                    break;
                default:
                    System.out.println("Trying to delete unexisting station or address");
            }
        }
        if (loggerIndex == -1) {
            logger.error("Address or station not in list");
        }
        for (Firestation f : toRemove) {
            firestations.remove(f);
        }
        return firestations;
    }
    
    @Override
    public List<Firestation> modifyFirestation(String address, String station) {
        List<Firestation> firestationList = recoveredData.getFirestations();
        int loggerIndex=-1;
        for (Firestation f : firestationList) {
            if (f.getAddress().equals(address)) {
                f.setStation(station.trim());
                loggerIndex=1;
            }
        }
        if(loggerIndex==-1) {
            logger.error("Address not found");
        }
        return firestationList;
    }
    
    @Override
    public List<Firestation> addFirestation(String address, String station) {
        List<Firestation> firestationList = recoveredData.getFirestations();
        Firestation f = new Firestation();
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
