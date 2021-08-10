package com.safetynetalerts.SafetyNetAlerts.controller;

import com.safetynetalerts.SafetyNetAlerts.model.Person;
import com.safetynetalerts.SafetyNetAlerts.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class PersonController {
    private static final Logger logger = LogManager.getLogger(PersonController.class);
    @Autowired
    IPersonService personService;
    
    @GetMapping("/person/all")
    public List<Person> readPersons() {
        logger.debug("Reading all persons from JSON");
        List<Person>list=personService.readPersons();
        logger.info("Person list is "+list);
        return list;
    }
    
    @DeleteMapping("/person")
    public List<Person> deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        logger.debug("Deleting person " + firstName + lastName);
        List<Person>list=personService.deletePerson(firstName, lastName);
        logger.info("Person list after delete is "+list);
        return list;
    }
    
    @PutMapping("/person")
    public List<Person> modifyPerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestBody HashMap<String, String> params) {
        logger.debug("Modify person "+firstName+" "+lastName+"with new parameters "+params.toString());
        List<Person>list=personService.modifyPerson(firstName, lastName, params);
        logger.info("Person list after modification is "+list);
        return list;
    }
    
    @PostMapping("/person")
    public List<Person> addPerson(@RequestBody Person person) {
        logger.debug("Adding person");
        List<Person>list=personService.addPerson(person);
        logger.info("Person list after adding "+person+" is"+list);
        return list;
    }
    
}
