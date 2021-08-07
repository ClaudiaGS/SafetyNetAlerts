package com.safetynetalerts.SafetyNetAlerts.repository;

import com.safetynetalerts.SafetyNetAlerts.model.Person;

import java.util.HashMap;
import java.util.List;

public interface IPersonsProxy {
    public List<Person> readPersons();
    
    public List<Person> deletePerson(String firstName, String lastName);
    
    public List<Person> modifyPerson(String firstName, String lastName, HashMap<String, String> params);
    
    public List<Person> addPerson(Person person);
    
    public void setRecoveredData(IRecoveredData recoveredData);
}
