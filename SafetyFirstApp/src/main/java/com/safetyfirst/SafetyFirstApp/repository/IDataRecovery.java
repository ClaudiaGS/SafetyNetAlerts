package com.safetyfirst.SafetyFirstApp.repository;


import com.safetyfirst.SafetyFirstApp.model.Firestation;
import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import com.safetyfirst.SafetyFirstApp.model.Person;

import java.util.List;

public interface IDataRecovery {
    public RecoveredData readData();
    public List<Person> getPersons();
    public List<Firestation> getFirestations();
    public List<MedicalRecord> getMedicalrecords();

}
