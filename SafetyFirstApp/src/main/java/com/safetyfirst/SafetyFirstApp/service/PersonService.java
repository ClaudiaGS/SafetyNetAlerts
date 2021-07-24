package com.safetyfirst.SafetyFirstApp.service;

import com.safetyfirst.SafetyFirstApp.model.Person;
import com.safetyfirst.SafetyFirstApp.repository.PersonsProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Data
@Service
public class PersonService {
    
    @Autowired
    PersonsProxy personsProxy;
    
    public List<Person> readPersons() {
        return personsProxy.readPersons();
    }
    
    public List<Person> deletePerson(String firstName, String lastName) {
        return personsProxy.deletePerson(firstName, lastName);
    }
    
    public List<Person> modifyPerson(String firstName, String lastName, HashMap<String, String> params) {
        return personsProxy.modifyPerson(firstName, lastName, params);
    }
    
    public List<Person> addPerson(Person person) {
        return personsProxy.addPerson(person);
    }
}