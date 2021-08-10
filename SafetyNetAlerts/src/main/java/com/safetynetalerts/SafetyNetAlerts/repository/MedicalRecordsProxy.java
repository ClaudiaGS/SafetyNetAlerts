package com.safetynetalerts.SafetyNetAlerts.repository;

import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MedicalRecordsProxy implements IMedicalRecordsProxy {
    private static final Logger logger = LogManager.getLogger(MedicalRecordsProxy.class);
    
    @Autowired
    IRecoveredData recoveredData;
    
    @Override
    public List<MedicalRecord> readMedicalRecords() {
        List<MedicalRecord> medicalRecords = recoveredData.getMedicalrecords();
        return medicalRecords;
    }
    
    @Override
    public List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName) {
        List<MedicalRecord> medicalRecords = recoveredData.getMedicalrecords();
        MedicalRecord medicalRecordToDelete = null;
        for (MedicalRecord m : medicalRecords) {
            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
                medicalRecordToDelete = m;
            }
        }
        if (medicalRecordToDelete == null) {
            logger.error("Medical record not in list.");
        }
        medicalRecords.remove(medicalRecordToDelete);
        return medicalRecords;
    }
    
    @Override
    public List<MedicalRecord> modifyMedicalRecord(String firstName, String lastName, List<String> newMedications, List<String> newAllergies) {
        List<MedicalRecord> medicalRecordList = recoveredData.getMedicalrecords();
        int loggerIndex=-1;
        for (MedicalRecord m : medicalRecordList) {
            List<String> medications = m.getMedications();
            List<String> allergies = m.getAllergies();
            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
                loggerIndex=1;
                for (String newMedication : newMedications) {
                    medications.add(newMedication);
                }
                for (String newAllergie : newAllergies) {
                    allergies.add(newAllergie);
                }
            }
        }
        if(loggerIndex==-1){
            logger.error("Medical record not in list");
        }
        return medicalRecordList;
        
    }
    
    @Override
    public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord) {
        List<MedicalRecord> medicalRecords = recoveredData.getMedicalrecords();
        medicalRecords.add(medicalRecord);
        return medicalRecords;
    }
    
    @Override
    public void setRecoveredData(IRecoveredData recoveredData) {
        this.recoveredData = recoveredData;
    }
}