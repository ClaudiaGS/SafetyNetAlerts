package com.safetynetalerts.SafetyNetAlerts.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.SafetyNetAlerts.config.DataSources;
import com.safetynetalerts.SafetyNetAlerts.integration.config.RecoveredTestData;
import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynetalerts.SafetyNetAlerts.repository.IMedicalRecordsProxy;
import com.safetynetalerts.SafetyNetAlerts.repository.IRecoveredData;
import com.safetynetalerts.SafetyNetAlerts.service.IMedicalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MedicalRecordServiceIT {
    
    @Autowired
    IMedicalRecordsProxy medicalRecordsProxy;
    
    @Autowired
    IMedicalRecordService medicalRecordService;
    
    @Autowired
    DataSources dataSources;
    
    
    IRecoveredData recoveredTestData=new RecoveredTestData();
    
    @BeforeEach
    public void config() {
         recoveredTestData.readData(dataSources);
        medicalRecordsProxy.setRecoveredData(recoveredTestData);
        
    }
    
    @Test
    public void readMedicalRecordsIT() {
        assertThat(asJsonString(medicalRecordService.readMedicalRecords())).isEqualTo("[{\"firstName\":\"TestJohn\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1984\",\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"TestJacob\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1989\",\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]},{\"firstName\":\"TestTenley\",\"lastName\":\"Boyd\",\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[\"peanut\"]},{\"firstName\":\"TestRoger\",\"lastName\":\"Boyd\",\"birthdate\":\"09/06/2017\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestFelicia\",\"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\",\"medications\":[\"tetracyclaz:650mg\"],\"allergies\":[\"xilliathal\"]},{\"firstName\":\"TestJonanathan\",\"lastName\":\"Marrack\",\"birthdate\":\"01/03/1989\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestTessa\",\"lastName\":\"Carman\",\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestPeter\",\"lastName\":\"Duncan\",\"birthdate\":\"09/06/2000\",\"medications\":[],\"allergies\":[\"shellfish\"]},{\"firstName\":\"TestFoster\",\"lastName\":\"Shepard\",\"birthdate\":\"01/08/1980\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestTony\",\"lastName\":\"Cooper\",\"birthdate\":\"03/06/1994\",\"medications\":[\"hydrapermazol:300mg\",\"dodoxadin:30mg\"],\"allergies\":[\"shellfish\"]},{\"firstName\":\"TestLily\",\"lastName\":\"Cooper\",\"birthdate\":\"03/06/1994\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestSophia\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1988\",\"medications\":[\"aznol:60mg\",\"hydrapermazol:900mg\",\"pharmacol:5000mg\",\"terazine:500mg\"],\"allergies\":[\"peanut\",\"shellfish\",\"aznol\"]},{\"firstName\":\"TestWarren\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1985\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestZach\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/2017\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestReginold\",\"lastName\":\"Walker\",\"birthdate\":\"08/30/1979\",\"medications\":[\"thradox:700mg\"],\"allergies\":[\"illisoxian\"]},{\"firstName\":\"TestJamie\",\"lastName\":\"Peters\",\"birthdate\":\"03/06/1982\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestRon\",\"lastName\":\"Peters\",\"birthdate\":\"04/06/1965\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestAllison\",\"lastName\":\"Boyd\",\"birthdate\":\"03/15/1965\",\"medications\":[\"aznol:200mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"TestBrian\",\"lastName\":\"Stelzer\",\"birthdate\":\"12/06/1975\",\"medications\":[\"ibupurin:200mg\",\"hydrapermazol:400mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"TestShawna\",\"lastName\":\"Stelzer\",\"birthdate\":\"07/08/1980\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestKendrik\",\"lastName\":\"Stelzer\",\"birthdate\":\"03/06/2014\",\"medications\":[\"noxidian:100mg\",\"pharmacol:2500mg\"],\"allergies\":[]},{\"firstName\":\"TestClive\",\"lastName\":\"Ferguson\",\"birthdate\":\"03/06/1994\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestEric\",\"lastName\":\"Cadigan\",\"birthdate\":\"08/06/1945\",\"medications\":[\"tradoxidine:400mg\"],\"allergies\":[]}]");
    }
    
    @Test
    public void deleteMedicalRecordIT(){
        assertThat(asJsonString(medicalRecordService.deleteMedicalRecord("TestJohn","Boyd"))).isEqualTo("[{\"firstName\":\"TestJacob\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1989\",\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]},{\"firstName\":\"TestTenley\",\"lastName\":\"Boyd\",\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[\"peanut\"]},{\"firstName\":\"TestRoger\",\"lastName\":\"Boyd\",\"birthdate\":\"09/06/2017\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestFelicia\",\"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\",\"medications\":[\"tetracyclaz:650mg\"],\"allergies\":[\"xilliathal\"]},{\"firstName\":\"TestJonanathan\",\"lastName\":\"Marrack\",\"birthdate\":\"01/03/1989\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestTessa\",\"lastName\":\"Carman\",\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestPeter\",\"lastName\":\"Duncan\",\"birthdate\":\"09/06/2000\",\"medications\":[],\"allergies\":[\"shellfish\"]},{\"firstName\":\"TestFoster\",\"lastName\":\"Shepard\",\"birthdate\":\"01/08/1980\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestTony\",\"lastName\":\"Cooper\",\"birthdate\":\"03/06/1994\",\"medications\":[\"hydrapermazol:300mg\",\"dodoxadin:30mg\"],\"allergies\":[\"shellfish\"]},{\"firstName\":\"TestLily\",\"lastName\":\"Cooper\",\"birthdate\":\"03/06/1994\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestSophia\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1988\",\"medications\":[\"aznol:60mg\",\"hydrapermazol:900mg\",\"pharmacol:5000mg\",\"terazine:500mg\"],\"allergies\":[\"peanut\",\"shellfish\",\"aznol\"]},{\"firstName\":\"TestWarren\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1985\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestZach\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/2017\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestReginold\",\"lastName\":\"Walker\",\"birthdate\":\"08/30/1979\",\"medications\":[\"thradox:700mg\"],\"allergies\":[\"illisoxian\"]},{\"firstName\":\"TestJamie\",\"lastName\":\"Peters\",\"birthdate\":\"03/06/1982\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestRon\",\"lastName\":\"Peters\",\"birthdate\":\"04/06/1965\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestAllison\",\"lastName\":\"Boyd\",\"birthdate\":\"03/15/1965\",\"medications\":[\"aznol:200mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"TestBrian\",\"lastName\":\"Stelzer\",\"birthdate\":\"12/06/1975\",\"medications\":[\"ibupurin:200mg\",\"hydrapermazol:400mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"TestShawna\",\"lastName\":\"Stelzer\",\"birthdate\":\"07/08/1980\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestKendrik\",\"lastName\":\"Stelzer\",\"birthdate\":\"03/06/2014\",\"medications\":[\"noxidian:100mg\",\"pharmacol:2500mg\"],\"allergies\":[]},{\"firstName\":\"TestClive\",\"lastName\":\"Ferguson\",\"birthdate\":\"03/06/1994\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestEric\",\"lastName\":\"Cadigan\",\"birthdate\":\"08/06/1945\",\"medications\":[\"tradoxidine:400mg\"],\"allergies\":[]}]");
    }
    @Test
    public void modifyMedicalRecordIT(){
        MedicalRecord medicalRecordToModify=recoveredTestData.getMedicalrecords().get(0);
        List<String> newMedications = new ArrayList<>();
        newMedications.add("bbb");
        List<String> newAllergies = new ArrayList<>();
        newAllergies.add("aaa");
        assertThat(asJsonString(medicalRecordService.modifyMedicalRecord("TestJohn","Boyd",newMedications,newAllergies))).isEqualTo("[{\"firstName\":\"TestJohn\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1984\",\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\",\"bbb\"],\"allergies\":[\"nillacilan\",\"aaa\"]},{\"firstName\":\"TestJacob\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1989\",\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]},{\"firstName\":\"TestTenley\",\"lastName\":\"Boyd\",\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[\"peanut\"]},{\"firstName\":\"TestRoger\",\"lastName\":\"Boyd\",\"birthdate\":\"09/06/2017\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestFelicia\",\"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\",\"medications\":[\"tetracyclaz:650mg\"],\"allergies\":[\"xilliathal\"]},{\"firstName\":\"TestJonanathan\",\"lastName\":\"Marrack\",\"birthdate\":\"01/03/1989\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestTessa\",\"lastName\":\"Carman\",\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestPeter\",\"lastName\":\"Duncan\",\"birthdate\":\"09/06/2000\",\"medications\":[],\"allergies\":[\"shellfish\"]},{\"firstName\":\"TestFoster\",\"lastName\":\"Shepard\",\"birthdate\":\"01/08/1980\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestTony\",\"lastName\":\"Cooper\",\"birthdate\":\"03/06/1994\",\"medications\":[\"hydrapermazol:300mg\",\"dodoxadin:30mg\"],\"allergies\":[\"shellfish\"]},{\"firstName\":\"TestLily\",\"lastName\":\"Cooper\",\"birthdate\":\"03/06/1994\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestSophia\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1988\",\"medications\":[\"aznol:60mg\",\"hydrapermazol:900mg\",\"pharmacol:5000mg\",\"terazine:500mg\"],\"allergies\":[\"peanut\",\"shellfish\",\"aznol\"]},{\"firstName\":\"TestWarren\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1985\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestZach\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/2017\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestReginold\",\"lastName\":\"Walker\",\"birthdate\":\"08/30/1979\",\"medications\":[\"thradox:700mg\"],\"allergies\":[\"illisoxian\"]},{\"firstName\":\"TestJamie\",\"lastName\":\"Peters\",\"birthdate\":\"03/06/1982\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestRon\",\"lastName\":\"Peters\",\"birthdate\":\"04/06/1965\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestAllison\",\"lastName\":\"Boyd\",\"birthdate\":\"03/15/1965\",\"medications\":[\"aznol:200mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"TestBrian\",\"lastName\":\"Stelzer\",\"birthdate\":\"12/06/1975\",\"medications\":[\"ibupurin:200mg\",\"hydrapermazol:400mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"TestShawna\",\"lastName\":\"Stelzer\",\"birthdate\":\"07/08/1980\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestKendrik\",\"lastName\":\"Stelzer\",\"birthdate\":\"03/06/2014\",\"medications\":[\"noxidian:100mg\",\"pharmacol:2500mg\"],\"allergies\":[]},{\"firstName\":\"TestClive\",\"lastName\":\"Ferguson\",\"birthdate\":\"03/06/1994\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestEric\",\"lastName\":\"Cadigan\",\"birthdate\":\"08/06/1945\",\"medications\":[\"tradoxidine:400mg\"],\"allergies\":[]}]");
    }
    @Test
    public void addMedicalRecordIT(){
        MedicalRecord medicalRecord=new MedicalRecord();
        medicalRecord.setFirstName("Anne");
        medicalRecord.setLastName("Dubois");
        medicalRecord.setBirthdate("11/10/2020");
        List<String>medications=new ArrayList<>();
        List<String>allergies=new ArrayList<>();
        medications.add("eee");
        allergies.add("ooo");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        assertThat(asJsonString(medicalRecordService.addMedicalRecord(medicalRecord))).isEqualTo("[{\"firstName\":\"TestJohn\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1984\",\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"TestJacob\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1989\",\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]},{\"firstName\":\"TestTenley\",\"lastName\":\"Boyd\",\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[\"peanut\"]},{\"firstName\":\"TestRoger\",\"lastName\":\"Boyd\",\"birthdate\":\"09/06/2017\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestFelicia\",\"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\",\"medications\":[\"tetracyclaz:650mg\"],\"allergies\":[\"xilliathal\"]},{\"firstName\":\"TestJonanathan\",\"lastName\":\"Marrack\",\"birthdate\":\"01/03/1989\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestTessa\",\"lastName\":\"Carman\",\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestPeter\",\"lastName\":\"Duncan\",\"birthdate\":\"09/06/2000\",\"medications\":[],\"allergies\":[\"shellfish\"]},{\"firstName\":\"TestFoster\",\"lastName\":\"Shepard\",\"birthdate\":\"01/08/1980\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestTony\",\"lastName\":\"Cooper\",\"birthdate\":\"03/06/1994\",\"medications\":[\"hydrapermazol:300mg\",\"dodoxadin:30mg\"],\"allergies\":[\"shellfish\"]},{\"firstName\":\"TestLily\",\"lastName\":\"Cooper\",\"birthdate\":\"03/06/1994\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestSophia\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1988\",\"medications\":[\"aznol:60mg\",\"hydrapermazol:900mg\",\"pharmacol:5000mg\",\"terazine:500mg\"],\"allergies\":[\"peanut\",\"shellfish\",\"aznol\"]},{\"firstName\":\"TestWarren\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1985\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestZach\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/2017\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestReginold\",\"lastName\":\"Walker\",\"birthdate\":\"08/30/1979\",\"medications\":[\"thradox:700mg\"],\"allergies\":[\"illisoxian\"]},{\"firstName\":\"TestJamie\",\"lastName\":\"Peters\",\"birthdate\":\"03/06/1982\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestRon\",\"lastName\":\"Peters\",\"birthdate\":\"04/06/1965\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestAllison\",\"lastName\":\"Boyd\",\"birthdate\":\"03/15/1965\",\"medications\":[\"aznol:200mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"TestBrian\",\"lastName\":\"Stelzer\",\"birthdate\":\"12/06/1975\",\"medications\":[\"ibupurin:200mg\",\"hydrapermazol:400mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"TestShawna\",\"lastName\":\"Stelzer\",\"birthdate\":\"07/08/1980\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestKendrik\",\"lastName\":\"Stelzer\",\"birthdate\":\"03/06/2014\",\"medications\":[\"noxidian:100mg\",\"pharmacol:2500mg\"],\"allergies\":[]},{\"firstName\":\"TestClive\",\"lastName\":\"Ferguson\",\"birthdate\":\"03/06/1994\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"TestEric\",\"lastName\":\"Cadigan\",\"birthdate\":\"08/06/1945\",\"medications\":[\"tradoxidine:400mg\"],\"allergies\":[]},{\"firstName\":\"Anne\",\"lastName\":\"Dubois\",\"birthdate\":\"11/10/2020\",\"medications\":[\"eee\"],\"allergies\":[\"ooo\"]}]");
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

