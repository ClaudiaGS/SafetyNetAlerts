package com.safetynetalerts.SafetyNetAlerts.controller;

import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynetalerts.SafetyNetAlerts.service.IMedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {
    private static final Logger logger = LogManager.getLogger(MedicalRecordController.class);
    @Autowired
    IMedicalRecordService medicalRecordService;
    
    @GetMapping("/medicalRecord/all")
    public List<MedicalRecord> readMedicalRecords() {
        logger.debug("Reading medical records from JSON");
        List<MedicalRecord>list=medicalRecordService.readMedicalRecords();
        logger.info("Medical record list is "+list);
        return list;
    }
    
    @DeleteMapping("/medicalRecord")
    public List<MedicalRecord> deleteMedicalRecord(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        logger.debug("Delete medical record for " + firstName + " " + lastName);
        List<MedicalRecord>list=medicalRecordService.deleteMedicalRecord(firstName, lastName);
        logger.info("Medical Record list after delete is "+list);
        return list;
    }
    
    @PutMapping("/medicalRecord")
    public List<MedicalRecord> modifyMedicalRecord(@RequestParam String firstName, @RequestParam String lastName, @RequestParam List<String> newMedications, @RequestParam List<String>newAllergies){
        logger.debug("Modify medical record for " + firstName + " " + lastName + " with " + newMedications.toString() + " and " + newAllergies.toString());
        List<MedicalRecord>list=medicalRecordService.modifyMedicalRecord(firstName,lastName,newMedications,newAllergies);
        logger.info("Medical Record List after modification is "+list);
        return list;
    }
    
    @PostMapping("/medicalRecord")
    public List<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.debug("Adding medical record");
        List<MedicalRecord>list=medicalRecordService.addMedicalRecord(medicalRecord);
        logger.info("Medical Record list after adding medical record is "+list);
        return list;
    }
}

