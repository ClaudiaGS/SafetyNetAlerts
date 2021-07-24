package com.safetyfirst.SafetyFirstApp.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyfirst.SafetyFirstApp.model.Firestation;
import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import com.safetyfirst.SafetyFirstApp.model.Person;
import lombok.Data;

import java.net.URL;
import java.util.List;

@Data
public class RecoveredData implements IDataRecovery {
    
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalrecords;
    
    @Override
    public RecoveredData readData() {
        
        ObjectMapper mapper = new ObjectMapper();
        RecoveredData myObjects = null;
        try {
            URL url = new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
            myObjects = mapper.readValue(url, RecoveredData.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("myObjects are " + myObjects);
        persons = myObjects.persons;
        firestations = myObjects.firestations;
        medicalrecords = myObjects.medicalrecords;
        return myObjects;
    }
}
    
    

