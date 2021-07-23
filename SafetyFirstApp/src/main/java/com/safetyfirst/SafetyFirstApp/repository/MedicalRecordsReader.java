package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordsReader {
    @Autowired
    ReadData readData;
    public void readMedicalRecords() {
        List<MedicalRecord> medicalRecordsList = readData.getMedicalrecords();
        System.out.println("medical records list is "+medicalRecordsList);
    }
    
}