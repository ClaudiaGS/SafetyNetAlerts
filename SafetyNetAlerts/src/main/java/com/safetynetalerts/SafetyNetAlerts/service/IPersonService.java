package com.safetynetalerts.SafetyNetAlerts.service;

import com.safetynetalerts.SafetyNetAlerts.model.Person;

import java.util.HashMap;
import java.util.List;

public interface IPersonService {
    public List<Person> readPersons();
    public List<Person> deletePerson(String firstName, String lastName);
    public List<Person> modifyPerson(String firstName, String lastName, HashMap<String, String> params);
    public List<Person> addPerson(Person person);
}
