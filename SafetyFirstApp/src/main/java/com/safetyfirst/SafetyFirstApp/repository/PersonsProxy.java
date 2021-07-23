package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@Repository
public class PersonsProxy implements IPersonsProxy{
    @Autowired
    IDataRecovery recoveredData;
    
    @Override
    public List<Person> readPersons() {
        List<Person> persons = recoveredData.getPersons();
        System.out.println("person list is " + persons);
        return persons;
    }
}
