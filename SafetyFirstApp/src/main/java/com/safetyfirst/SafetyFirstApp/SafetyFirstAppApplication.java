package com.safetyfirst.SafetyFirstApp;

import com.safetyfirst.SafetyFirstApp.repository.FirestationsProxy;
import com.safetyfirst.SafetyFirstApp.repository.MedicalRecordsProxy;
import com.safetyfirst.SafetyFirstApp.repository.PersonsProxy;
import com.safetyfirst.SafetyFirstApp.repository.RecoveredData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class SafetyFirstAppApplication implements CommandLineRunner {
   
    
    public static void main(String[] args) {
        SpringApplication.run(SafetyFirstAppApplication.class, args);
        
    }
    @Autowired
    PersonsProxy personsProxy;
    @Autowired
    FirestationsProxy firestationsProxy;
    @Autowired
    MedicalRecordsProxy medicalRecordsProxy;
    @Autowired
    RecoveredData recoveredData;
  
    @Override
    public void run(String... args) throws Exception {

        recoveredData.readData();
        personsProxy.readPersons();
        firestationsProxy.readFirestations();
        medicalRecordsProxy.readMedicalRecords();
        
       
        
    }}
