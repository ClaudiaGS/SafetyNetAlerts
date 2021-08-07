package com.safetynetalerts.SafetyNetAlerts.repository;

import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordsProxy implements IMedicalRecordsProxy {
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
        medicalRecords.remove(medicalRecordToDelete);
        return medicalRecords;
    }
    
    @Override
    public List<MedicalRecord> modifyMedicalRecord(String firstName, String lastName, List<String> newMedications, List<String> newAllergies) {
        List<MedicalRecord> medicalRecordList = recoveredData.getMedicalrecords();
        for (MedicalRecord m : medicalRecordList) {
            List<String>medications=m.getMedications();
            List<String>allergies=m.getAllergies();
            if (m.getFirstName() .equals(firstName) && m.getLastName().equals(lastName)) {
           for (String newMedication : newMedications) {
                    medications.add(newMedication);
                }

                for (String newAllergie : newAllergies) {
                    allergies.add(newAllergie);
                }

            }
            
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