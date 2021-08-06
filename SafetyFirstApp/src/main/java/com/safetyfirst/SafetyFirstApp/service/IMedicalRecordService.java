package com.safetyfirst.SafetyFirstApp.service;

import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordService {
    public List<MedicalRecord> readMedicalRecords();
    public List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName);
    public List<MedicalRecord> modifyMedicalRecord(String firstName, String lastName, List<String> newMedications, List<String> newAllergies);
    public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord);
}
