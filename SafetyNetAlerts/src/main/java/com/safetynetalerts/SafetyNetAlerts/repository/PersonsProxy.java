package com.safetynetalerts.SafetyNetAlerts.repository;

import com.safetynetalerts.SafetyNetAlerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonsProxy implements IPersonsProxy {
    
    private static final Logger logger = LogManager.getLogger(PersonsProxy.class);
    
    @Autowired
    IRecoveredData recoveredData;
    
    @Override
    public List<Person> readPersons() {
        List<Person> persons = recoveredData.getPersons();
        return persons;
    }
    
    @Override
    public List<Person> deletePerson(String firstName, String lastName) {
        List<Person> persons = recoveredData.getPersons();
        Person personToDelete = null;
        for (Person p : persons) {
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                personToDelete = p;
            }
        }
        if (personToDelete == null) {
            logger.error("Person not in list");
        }
        persons.remove(personToDelete);
        return persons;
    }
    
    @Override
    public List<Person> modifyPerson(String firstName, String lastName, HashMap<String, String> params) {
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
            logger.error("Person not in list");
        }
        return persons;
    }
    
    @Override
    public List<Person> addPerson(Person person) {
        List<Person> persons = recoveredData.getPersons();
        persons.add(person);
        return persons;
    }
    
    @Override
    public void setRecoveredData(IRecoveredData recoveredData) {
        this.recoveredData = recoveredData;
    }
}
