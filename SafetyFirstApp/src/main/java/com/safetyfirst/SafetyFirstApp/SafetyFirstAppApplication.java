package com.safetyfirst.SafetyFirstApp;

import com.safetyfirst.SafetyFirstApp.repository.ReadData;
import com.safetyfirst.SafetyFirstApp.model.Person;
import com.safetyfirst.SafetyFirstApp.repository.FirestationsReader;
import com.safetyfirst.SafetyFirstApp.repository.MedicalRecordsReader;
import com.safetyfirst.SafetyFirstApp.repository.PersonsReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication

public class SafetyFirstAppApplication implements CommandLineRunner {
    @Autowired
    PersonsReader personsReader;
    @Autowired
    FirestationsReader firestationsReader;
    @Autowired
    MedicalRecordsReader medicalRecordsReader;
    
    public static void main(String[] args) {
        SpringApplication.run(SafetyFirstAppApplication.class, args);
        
        
    }
    @Autowired
    ReadData readData;
    @Autowired
    Person person;
    @Override
    public void run(String... args) throws Exception {
        readData.readData();
        List<Person>persons=readData.getPersons();
        System.out.println("Persons "+persons);
        System.out.println("persons[0] "+persons.get(0));
        firestationsReader.readFirestation();
        medicalRecordsReader.readMedicalRecords();
        
        
       
        
    }}
