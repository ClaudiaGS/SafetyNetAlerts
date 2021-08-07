package com.safetynetalerts.SafetyNetAlerts.repository;

import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordsProxy {
    public List<MedicalRecord> readMedicalRecords();
    
    public List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName);
    
    public List<MedicalRecord> modifyMedicalRecord(String firstName, String lastName, List<String> newMedications, List<String> newAllergies);
    
    public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord);
    
    public void setRecoveredData(IRecoveredData recoveredData);
}
