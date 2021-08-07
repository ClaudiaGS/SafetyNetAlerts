package com.safetynetalerts.SafetyNetAlerts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.SafetyNetAlerts.controller.FirestationController;
import com.safetynetalerts.SafetyNetAlerts.model.Firestation;
import com.safetynetalerts.SafetyNetAlerts.service.IFirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@SpringBootTest(classes = FirestationController.class)
@AutoConfigureMockMvc
public class FirestationControllerTest {
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    IFirestationService firestationService;
    
    List<Firestation> firestationTestList = new ArrayList<>();
    Firestation firestationTest1 = new Firestation();
    Firestation firestationTest2 = new Firestation();
    
    @BeforeEach
    public void config() {
        firestationTest1.setStation("1");
        firestationTest1.setAddress("95 Culver St");
        firestationTest2.setStation("2");
        firestationTest2.setAddress("85 Pink St");
        firestationTestList.add(firestationTest1);
        firestationTestList.add(firestationTest2);
        
    }
    
    @Test
    public void testGetFirestationAll() {
        try {
            when(firestationService.readFirestations()).thenReturn(firestationTestList);
            mvc.perform(MockMvcRequestBuilders
                    .get("/firestation/all"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testDeleteFirestation() {
        try {
            List<Firestation>result=firestationTestList;
            result.remove(firestationTest1);
            HashMap<String, String> addressOrStation = new HashMap<>();
            addressOrStation.put("station","1");
            when(firestationService.deleteFirestation(addressOrStation)).thenReturn(result);
            this.mvc.perform(MockMvcRequestBuilders
                    .delete("/firestation")
                    .content(asJsonString(addressOrStation))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testModifyFirestation() {
        try {
            List<Firestation>result=firestationTestList;
            result.get(0).setStation("3");
            when(firestationService.modifyFirestation("95 Culver St","3")).thenReturn(result);
            this.mvc.perform(MockMvcRequestBuilders
                    .put("/firestation")
                    .param("address","95 Culver St")
                    .param("station","3")
                    .contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void addFirestation() {
        try {
            Firestation firestation=new Firestation();
            List<Firestation>result=firestationTestList;
            result.add(firestation);
            when(firestationService.addFirestation(anyString(),anyString())).thenReturn(result);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/firestation")
                    .param("address","1505 Culver St")
                    .param("station","3")
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