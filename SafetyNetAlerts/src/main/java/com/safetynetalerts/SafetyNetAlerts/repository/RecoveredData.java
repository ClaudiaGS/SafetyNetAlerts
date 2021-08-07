package com.safetynetalerts.SafetyNetAlerts.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.SafetyNetAlerts.config.DataSources;
import com.safetynetalerts.SafetyNetAlerts.model.Firestation;
import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynetalerts.SafetyNetAlerts.model.Person;

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



