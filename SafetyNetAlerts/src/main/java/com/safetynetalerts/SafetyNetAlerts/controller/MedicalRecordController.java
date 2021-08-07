package com.safetynetalerts.SafetyNetAlerts.controller;

import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynetalerts.SafetyNetAlerts.service.IMedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {
    @Autowired
    IMedicalRecordService medicalRecordService;
    
    @GetMapping("/medicalRecord/all")
    public List<MedicalRecord> readMedicalRecords() {
        return medicalRecordService.readMedicalRecords();
    }
    
    @DeleteMapping("/medicalRecord")
    public List<MedicalRecord> deleteMedicalRecord(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
    
    @PutMapping("/medicalRecord")
    public List<MedicalRecord> modifyMedicalRecord(@RequestParam String firstName, @RequestParam String lastName, @RequestParam List<String> newMedications, @RequestParam List<String>newAllergies){
        return medicalRecordService.modifyMedicalRecord(firstName,lastName,newMedications,newAllergies);
    }
    
    @PostMapping("/medicalRecord")
    public List<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }
}

