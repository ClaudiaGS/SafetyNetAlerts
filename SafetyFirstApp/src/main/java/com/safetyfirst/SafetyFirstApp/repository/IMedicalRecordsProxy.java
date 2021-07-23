package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordsProxy {
    public List<MedicalRecord> readMedicalRecords();
}
