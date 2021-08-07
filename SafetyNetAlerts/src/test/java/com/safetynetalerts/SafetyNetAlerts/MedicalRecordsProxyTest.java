package com.safetynetalerts.SafetyNetAlerts;

import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynetalerts.SafetyNetAlerts.repository.IMedicalRecordsProxy;
import com.safetynetalerts.SafetyNetAlerts.repository.IRecoveredData;
import com.safetynetalerts.SafetyNetAlerts.repository.MedicalRecordsProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MedicalRecordsProxy.class)
public class MedicalRecordsProxyTest {
    @MockBean
    IRecoveredData rc;
    
    @Autowired
    IMedicalRecordsProxy medicalRecordsProxy;
    
    List<MedicalRecord> medicalRecordTestList = new ArrayList<>();
    MedicalRecord medicalRecordTest1 = new MedicalRecord();
    MedicalRecord medicalRecordTest2 = new MedicalRecord();
    
    @BeforeEach
    public void config() {
        
        medicalRecordTest1.setFirstName("Anne");
        medicalRecordTest1.setLastName("Dubois");
        medicalRecordTest2.setFirstName("Jack");
        medicalRecordTest2.setLastName("Connor");
        List<String>actualMedications=new ArrayList<>();
        actualMedications.add("aaaaaaa");
        actualMedications.add("ooooo");
        medicalRecordTest1.setMedications(actualMedications);
        List<String>actualAllergies=new ArrayList<>();
        actualAllergies.add("bbbbbbbb");
        medicalRecordTest1.setAllergies(actualAllergies);
        medicalRecordTestList.add(medicalRecordTest1);
        medicalRecordTestList.add(medicalRecordTest2);
        when(rc.getMedicalrecords()).thenReturn(medicalRecordTestList);
    }
    
    @Test
    public void readMedicalRecordTest() {
        assertThat(medicalRecordsProxy.readMedicalRecords()).isEqualTo(medicalRecordTestList);
    }
    
    @Test
    public void deleteMedicalRecordTest() {
        List<MedicalRecord> result = rc.getMedicalrecords();
        result.remove(medicalRecordTest1);
        assertThat(medicalRecordsProxy.deleteMedicalRecord("Anne", "Dubois")).isEqualTo(result);
    }
    
    @Test
    public void modifyMedicalRecordTest() {
        List<MedicalRecord> result = rc.getMedicalrecords();
        MedicalRecord medicalRecordToModify = result.get(0);
        List<String> newMedications = new ArrayList<>();
        newMedications.add("bbb");
        List<String> newAllergies = new ArrayList<>();
        newAllergies.add("aaa");
        for (String medication : newMedications) {
            medicalRecordToModify.getMedications().add(medication);
        }
        for (String allergy : newAllergies) {
            medicalRecordToModify.getAllergies().add(allergy);
        }
        assertThat(medicalRecordsProxy.modifyMedicalRecord("Anne", "Dubois", newMedications, newAllergies)).isEqualTo(result);
    }
    
    @Test
    public void addMedicalRecordTest() {
        MedicalRecord medicalRecordToAdd= new MedicalRecord();
        List<MedicalRecord> resultList = rc.getMedicalrecords();
        resultList.add(medicalRecordToAdd);
        assertThat(medicalRecordsProxy.addMedicalRecord(medicalRecordToAdd)).isEqualTo(resultList);
    }
    
}

