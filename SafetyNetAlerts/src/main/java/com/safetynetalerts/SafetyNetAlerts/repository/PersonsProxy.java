package com.safetynetalerts.SafetyNetAlerts.repository;

import com.safetynetalerts.SafetyNetAlerts.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonsProxy implements IPersonsProxy {
    private static final Logger logger = LoggerFactory.getLogger(PersonsProxy.class);
    
    @Autowired
    IRecoveredData recoveredData;
    
    @Override
    public List<Person> readPersons() {
        logger.info("Reading all persons from JSON");
        List<Person> persons = recoveredData.getPersons();
        return persons;
    }
    
    @Override
    public List<Person> deletePerson(String firstName, String lastName) {
        logger.info("Deleting person " + firstName + lastName);
        List<Person> persons = recoveredData.getPersons();
        Person personToDelete = null;
        for (Person p : persons) {
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                personToDelete = p;
            }
        }
        if (personToDelete == null) {
            logger.warn("Person not in list");
        }
        persons.remove(personToDelete);
        return persons;
    }
    
    @Override
    public List<Person> modifyPerson(String firstName, String lastName, HashMap<String, String> params) {
        logger.info("Modify person "+firstName+" "+lastName+"with new parameters "+params.toString());
        List<Person> persons = recoveredData.getPersons();
        int logIndex=-1;
        for (Person p : persons) {
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                logIndex=1;
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    switch (entry.getKey()) {
                        case "firstName":
                            p.setFirstName(entry.getValue());
                            break;
                        case "lastName":
                            p.setLastName(entry.getValue());
                            break;
                        case "address":
                            p.setAddress(entry.getValue());
                            break;
                        case "city":
                            p.setCity(entry.getValue());
                            break;
                        case "zip":
                            p.setZip(entry.getValue());
                            break;
                        case "phone":
                            p.setPhone(entry.getValue());
                            break;
                        case "email":
                            p.setEmail(entry.getValue());
                            break;
                        default:
                            System.out.println("Trying to modify unexisting parameter");
                            break;
                    }
                }
            }
        }
        if(logIndex==-1){
            logger.warn("Person not in list");
        }
        return persons;
    }
    
    @Override
    public List<Person> addPerson(Person person) {
        logger.info("Adding person");
        List<Person> persons = recoveredData.getPersons();
        persons.add(person);
        return persons;
    }
    
    @Override
    public void setRecoveredData(IRecoveredData recoveredData) {
        this.recoveredData = recoveredData;
    }
}
