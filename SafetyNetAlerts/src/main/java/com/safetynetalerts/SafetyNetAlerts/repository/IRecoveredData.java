package com.safetynetalerts.SafetyNetAlerts.repository;


import com.safetynetalerts.SafetyNetAlerts.config.DataSources;
import com.safetynetalerts.SafetyNetAlerts.model.Firestation;
import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynetalerts.SafetyNetAlerts.model.Person;

import java.util.List;

public interface IRecoveredData {
    public void readData(DataSources dataSources);
    public List<Person> getPersons();
    public List<Firestation> getFirestations();
    public List<MedicalRecord> getMedicalrecords();
    
}
