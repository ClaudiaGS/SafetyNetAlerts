package com.safetyfirst.SafetyFirstApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import com.safetyfirst.SafetyFirstApp.service.IMedicalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    IMedicalRecordService medicalRecordService;
    
    List<MedicalRecord> medicalRecordTestList = new ArrayList<>();
    
    @BeforeEach
    public void config() {
        MedicalRecord medicalRecordTest1 = new MedicalRecord();
        medicalRecordTest1.setFirstName("Anne");
        medicalRecordTest1.setLastName("Dubois");
        MedicalRecord medicalRecordTest2= new MedicalRecord();
        medicalRecordTestList.add(medicalRecordTest1);
        medicalRecordTestList.add(medicalRecordTest2);
    }
    
    @Test
    public void testGetMedicalRecordAll() {
        try {
            when(medicalRecordService.readMedicalRecords()).thenReturn(medicalRecordTestList);
            mvc.perform(MockMvcRequestBuilders
                    .get("/medicalRecord/all"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testDeleteMedicalRecord() {
        try {
            medicalRecordTestList.remove(0);
            when(medicalRecordService.deleteMedicalRecord("Anne","Dubois")).thenReturn(medicalRecordTestList);
            this.mvc.perform(MockMvcRequestBuilders
                    .delete("/medicalRecord")
                    .param("firstName","Anne")
                    .param("lastName","Dubois")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testModifyMedicalRecord() {
        try {
            List<String>newMedications=new ArrayList<>();
            newMedications.add("aaa");
            
            List<String >newAllergies=new ArrayList<>();
            newAllergies.add("iii");
            medicalRecordTestList.get(0).setMedications(newMedications);
            medicalRecordTestList.get(0).setAllergies(newAllergies);
            when(medicalRecordService.modifyMedicalRecord("Anne","Dubois",newMedications,newAllergies)).thenReturn(medicalRecordTestList);
            this.mvc.perform(MockMvcRequestBuilders
                    .put("/medicalRecord")
                    .param("firstName","Anne")
                    .param("lastname","Dubois")
                    .param("newMedications",(newMedications).toString())
                    .param("newAllergies",(newAllergies).toString())
                    .contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void addMedicalRecord() {
        try {
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecordTestList.add(medicalRecord);
            when(medicalRecordService.addMedicalRecord(medicalRecord)).thenReturn(medicalRecordTestList);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/medicalRecord")
                    .content(asJsonString(medicalRecord))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
