package com.safetyfirst.SafetyFirstApp.service;

import com.safetyfirst.SafetyFirstApp.model.Person;
import com.safetyfirst.SafetyFirstApp.repository.PersonsProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class PersonService {
    
    @Autowired
    PersonsProxy personsProxy;
    
    public List<Person> readPersons() {
        return personsProxy.readPersons();
    }
   
}