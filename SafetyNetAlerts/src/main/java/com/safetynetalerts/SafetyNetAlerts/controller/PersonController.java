package com.safetynetalerts.SafetyNetAlerts.controller;

import com.safetynetalerts.SafetyNetAlerts.model.Person;
import com.safetynetalerts.SafetyNetAlerts.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class PersonController {
    
    @Autowired
    IPersonService personService;
    
    @GetMapping("/person/all")
    public List<Person> readPersons() {
        return personService.readPersons();
    }
    
    @DeleteMapping("/person")
    public List<Person> deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return personService.deletePerson(firstName, lastName);
    }
    
    @PutMapping("/person")
    public List<Person> modifyPerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestBody HashMap<String, String> params) {
        return personService.modifyPerson(firstName, lastName, params);
    }
    
    @PostMapping("/person")
    public List<Person> addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }
    
}
