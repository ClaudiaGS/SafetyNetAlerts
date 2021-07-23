package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PersonsReader {
    @Autowired
    ReadData readData;
    public void readPersons(){
        List<Person>persons= readData.getPersons();
        System.out.println("personList is "+persons);
    }

}
