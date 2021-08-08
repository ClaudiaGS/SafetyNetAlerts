package com.safetynetalerts.SafetyNetAlerts;

import com.safetynetalerts.SafetyNetAlerts.controller.ExtraController;
import com.safetynetalerts.SafetyNetAlerts.service.IExtraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@SpringBootTest(classes = ExtraController.class)
@AutoConfigureMockMvc
public class ExtraControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    IExtraService extraService;
    
    String result1 = "1111";
    String result2 = "2222";
    String result3 = "3333";
    String result4 = "4444";
    String result5 = "5555";
    String result6 = "6666";
    String result7 = "7777";
    
    
    @Test
    public void testEndpoint1ToJSon() {
        try {
            when(extraService.endpoint1ToJSon("1")).thenReturn(result1);
            mvc.perform(MockMvcRequestBuilders
                    .get("/firestation")
                    .param("stationNumber", "1"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testEndpoint2ToJSon() {
        try {
            when(extraService.endpoint2ToJSon("1509 Culver St")).thenReturn(result2);
            mvc.perform(MockMvcRequestBuilders
                    .get("/childAlert")
                    .param("address", "1509 Culver St"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testEndpoint3ToJSon() {
        try {
            when(extraService.endpoint3ToJSon("2")).thenReturn(result3);
            mvc.perform(MockMvcRequestBuilders
                    .get("/phoneAlert")
                    .param("station", "2"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testEndpoint4ToJSon() {
        try {
            when(extraService.endpoint4ToJSon("1509 Culver St")).thenReturn(result4);
            mvc.perform(MockMvcRequestBuilders
                    .get("/fire")
                    .param("address", "1509 Culver St"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testEndpoint5ToJSon() {
        try {
            when(extraService.endpoint5ToJSon("2")).thenReturn(result5);
            mvc.perform(MockMvcRequestBuilders
                    .get("/flood/stations")
                    .param("station", "2"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testEndpoint6ToJSon() {
        try {
            when(extraService.endpoint6ToJSon()).thenReturn(result6);
            mvc.perform(MockMvcRequestBuilders
                    .get("/personInfo"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testEndpoint7ToJSon() {
        try {
            when(extraService.endpoint7ToJSon("Culver")).thenReturn(result7);
            mvc.perform(MockMvcRequestBuilders
                    .get("/communityEmail")
                    .param("city", "Culver"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}