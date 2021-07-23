package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@Repository
public class MedicalRecordsProxy implements IMedicalRecordsProxy {
    @Autowired
    IDataRecovery recoveredData;
    
    @Override
    public List<MedicalRecord> readMedicalRecords() {
        List<MedicalRecord> medicalRecords = recoveredData.getMedicalrecords();
        System.out.println("medical records list is " + medicalRecords);
        return medicalRecords;
    }
}