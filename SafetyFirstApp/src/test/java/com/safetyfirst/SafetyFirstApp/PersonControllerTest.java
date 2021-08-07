package com.safetyfirst.SafetyFirstApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyfirst.SafetyFirstApp.controller.PersonController;
import com.safetyfirst.SafetyFirstApp.model.Person;
import com.safetyfirst.SafetyFirstApp.service.IPersonService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@EnableWebMvc
@SpringBootTest(classes = PersonController.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    IPersonService personService;
    
    List<Person> personTestList = new ArrayList<>();
    
    @BeforeEach
    public void config() {
        Person testPerson1 = new Person();
        Person testPerson2 = new Person();
        testPerson1.setFirstName("Anne");
        testPerson1.setLastName("Dupont");
        testPerson1.setPhone("000-000");
        testPerson2.setFirstName("Arnaud");
        testPerson2.setLastName("Dub");
        personTestList.add(testPerson1);
        personTestList.add(testPerson2);
        when(personService.readPersons()).thenReturn(personTestList);
    }
    
    @Test
    public void testGetPersonAll() {
        try {
            when(personService.readPersons()).thenReturn(personTestList);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/person/all")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testDeletePerson() {
        try {
            personTestList.remove(0);
            when(personService.deletePerson("Anne", "Dupont")).thenReturn(personTestList);
            this.mvc.perform(MockMvcRequestBuilders
                    .delete("/person")
                    .param("firstName","Anne")
                    .param("lastName","Dupont")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testModifyPerson() {
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("phone", "111-111");
            personTestList.get(0).setPhone(params.get("phone"));
            when(personService.modifyPerson("Anne", "Dupont", params)).thenReturn(personTestList);
            this.mvc.perform(MockMvcRequestBuilders
                    .put("/person")
                    .param("firstName","Anne")
                    .param("lastName","Dupont")
                    .content(asJsonString(params))
                    .contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void addPerson() {
        try {
            Person person = new Person();
            personTestList.add(person);
            when(personService.addPerson(person)).thenReturn(personTestList);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("http://localhost:8080/person")
                    .content(asJsonString(person))
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
