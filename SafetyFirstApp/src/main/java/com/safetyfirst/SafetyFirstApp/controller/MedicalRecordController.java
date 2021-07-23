package com.safetyfirst.SafetyFirstApp.controller;

import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import com.safetyfirst.SafetyFirstApp.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {
    @Autowired
    MedicalRecordService medicalRecordService;
    
    @GetMapping("/medicalRecord/all")
    public List<MedicalRecord> readMedicalRecords() {
        return medicalRecordService.readMedicalRecords();
    }
}
