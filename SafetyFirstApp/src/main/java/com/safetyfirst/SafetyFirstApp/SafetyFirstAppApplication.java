package com.safetyfirst.SafetyFirstApp;

import com.safetyfirst.SafetyFirstApp.repository.*;
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
    @Autowired
    ExtraProxy extraProxy;
  
    @Override
    public void run(String... args) throws Exception {

        recoveredData.readData();
        personsProxy.readPersons();
        firestationsProxy.readFirestations();
        medicalRecordsProxy.readMedicalRecords();
    
        System.out.println("endpoint1: "+extraProxy.endpoint1ToJSon("4"));
        System.out.println("endpoint2: "+extraProxy.endpoint2ToJSon("1509 Culver St"));
        System.out.println("endpoint3: "+extraProxy.endpoint3ToJSon("4"));
        System.out.println("endpoint4: "+extraProxy.endpoint4ToJSon("1509 Culver St"));
        System.out.println("endpoint5: "+extraProxy.endpoint5ToJSon("1"));
        System.out.println("endpoint6: "+extraProxy.endpoint6ToJSon());
        System.out.println("endpoint7: "+extraProxy.endpoint7ToJSon("Culver"));
    }}
