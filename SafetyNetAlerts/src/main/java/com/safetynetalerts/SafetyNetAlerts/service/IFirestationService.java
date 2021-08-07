package com.safetynetalerts.SafetyNetAlerts.service;

import com.safetynetalerts.SafetyNetAlerts.model.Firestation;

import java.util.HashMap;
import java.util.List;

public interface IFirestationService {
    public List<Firestation> readFirestations();
    public List<Firestation> deleteFirestation(HashMap<String, String> addressOrStation);
    public List<Firestation> modifyFirestation( String address, String station);
    public List<Firestation>addFirestation(String address, String station);
}