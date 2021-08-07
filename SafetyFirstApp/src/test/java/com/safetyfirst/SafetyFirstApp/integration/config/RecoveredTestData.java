package com.safetyfirst.SafetyFirstApp.integration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyfirst.SafetyFirstApp.config.DataSources;
import com.safetyfirst.SafetyFirstApp.model.Firestation;
import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import com.safetyfirst.SafetyFirstApp.model.Person;
import com.safetyfirst.SafetyFirstApp.repository.IRecoveredData;

import java.util.List;

public class RecoveredTestData implements IRecoveredData {
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalrecords;

    
    @Override
    public void readData(DataSources dataSource) {

        ObjectMapper mapper = new ObjectMapper();
        RecoveredTestData myObjects = null;
        try {
            //   URL url = new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
            myObjects = mapper.readValue(dataSource.getDataTest(), RecoveredTestData.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        persons = myObjects.persons;
        firestations = myObjects.firestations;
        medicalrecords = myObjects.medicalrecords;
        
    }
    @Override
    public List<Firestation> getFirestations() {
        return firestations;
    }
    
    @Override
    public List<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }
    @Override
    public List<Person> getPersons() {
        return persons;
    }
}


