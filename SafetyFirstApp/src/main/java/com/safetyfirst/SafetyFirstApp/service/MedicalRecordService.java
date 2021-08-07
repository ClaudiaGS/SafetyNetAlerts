package com.safetyfirst.SafetyFirstApp.service;

import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import com.safetyfirst.SafetyFirstApp.repository.MedicalRecordsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicalRecordService implements IMedicalRecordService {
    @Autowired
    MedicalRecordsProxy medicalRecordsProxy;
    
    public List<MedicalRecord> readMedicalRecords() {
        return medicalRecordsProxy.readMedicalRecords();
    }
    
    public List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName) {
        return medicalRecordsProxy.deleteMedicalRecord(firstName, lastName);
    }
    public List<MedicalRecord> modifyMedicalRecord(String firstName,String lastName, List<String>newMedications, List<String> newAllergies) {
        return medicalRecordsProxy.modifyMedicalRecord(firstName,lastName,newMedications,newAllergies);
    }
    public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordsProxy.addMedicalRecord(medicalRecord);
    }
}
