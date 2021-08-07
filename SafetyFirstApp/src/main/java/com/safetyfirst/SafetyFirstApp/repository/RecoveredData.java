package com.safetyfirst.SafetyFirstApp.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyfirst.SafetyFirstApp.config.DataSources;
import com.safetyfirst.SafetyFirstApp.model.Firestation;
import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import com.safetyfirst.SafetyFirstApp.model.Person;

import java.util.List;


public class RecoveredData implements IRecoveredData {
    
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalrecords;
    
    @Override
    public void readData(DataSources dataSource) {
        
        ObjectMapper mapper = new ObjectMapper();
        RecoveredData myObjects = null;
        try {
            myObjects = mapper.readValue(dataSource.getDataUrl(), RecoveredData.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        persons = myObjects.persons;
        firestations = myObjects.firestations;
        medicalrecords = myObjects.medicalrecords;
        
    }
    
    @Override
    public List<Person> getPersons() {
        return persons;
    }
    
    @Override
    public List<Firestation> getFirestations() {
        return firestations;
    }
    
    @Override
    public List<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }
}



