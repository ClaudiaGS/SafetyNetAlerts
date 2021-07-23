package com.safetyfirst.SafetyFirstApp.service;

import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import com.safetyfirst.SafetyFirstApp.repository.MedicalRecordsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    MedicalRecordsProxy medicalRecordsProxy;
    
    public List<MedicalRecord> readMedicalRecords() {
        return medicalRecordsProxy.readMedicalRecords();
    }

}
