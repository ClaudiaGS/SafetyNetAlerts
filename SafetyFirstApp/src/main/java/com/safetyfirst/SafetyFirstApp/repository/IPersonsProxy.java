package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.Person;

import java.util.List;

public interface IPersonsProxy {
    public List<Person> readPersons();
    
}
