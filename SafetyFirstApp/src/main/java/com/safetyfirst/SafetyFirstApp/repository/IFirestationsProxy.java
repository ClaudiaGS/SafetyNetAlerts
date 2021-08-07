package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.Firestation;

import java.util.HashMap;
import java.util.List;

public interface IFirestationsProxy {
    public List<Firestation> readFirestations();
    
    public List<Firestation> deleteFirestation(HashMap<String,String> addressOrStation);
    
    public List<Firestation> modifyFirestation(String address, String station);
    
    public List<Firestation> addFirestation(String address, String station);
    
    public void setRecoveredData(IRecoveredData recoveredData);
}
