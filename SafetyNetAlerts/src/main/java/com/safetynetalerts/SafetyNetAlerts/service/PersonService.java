package com.safetynetalerts.SafetyNetAlerts.service;

import com.safetynetalerts.SafetyNetAlerts.model.Person;
import com.safetynetalerts.SafetyNetAlerts.repository.IPersonsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PersonService implements IPersonService{
    
    @Autowired
    IPersonsProxy personsProxy;
    
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