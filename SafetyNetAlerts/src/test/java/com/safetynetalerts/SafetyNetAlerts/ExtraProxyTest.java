package com.safetynetalerts.SafetyNetAlerts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.SafetyNetAlerts.model.Firestation;
import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynetalerts.SafetyNetAlerts.model.Person;
import com.safetynetalerts.SafetyNetAlerts.repository.ExtraProxy;
import com.safetynetalerts.SafetyNetAlerts.repository.IExtraProxy;
import com.safetynetalerts.SafetyNetAlerts.repository.IRecoveredData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes= ExtraProxy.class)
public class ExtraProxyTest {
    @MockBean
    IRecoveredData rc;
    @Autowired
    IExtraProxy extraProxy;
    
    List<Firestation> firestationList = new ArrayList<>();
    List<MedicalRecord> medicalRecordList = new ArrayList<>();
    List<Person> personList = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    
    
    @BeforeEach
    public void config() {
        String jsonPerson="[\n" +
                "    {\n" +
                "        \"firstName\": \"John\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"address\": \"1509 Culver St\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6512\",\n" +
                "        \"email\": \"jaboyd@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Jacob\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"address\": \"1509 Culver St\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6513\",\n" +
                "        \"email\": \"drk@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Tenley\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"address\": \"1509 Culver St\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6512\",\n" +
                "        \"email\": \"tenz@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Roger\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"address\": \"1509 Culver St\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6512\",\n" +
                "        \"email\": \"jaboyd@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Felicia\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"address\": \"1509 Culver St\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6544\",\n" +
                "        \"email\": \"jaboyd@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Jonanathan\",\n" +
                "        \"lastName\": \"Marrack\",\n" +
                "        \"address\": \"29 15th St\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6513\",\n" +
                "        \"email\": \"drk@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Tessa\",\n" +
                "        \"lastName\": \"Carman\",\n" +
                "        \"address\": \"834 Binoc Ave\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6512\",\n" +
                "        \"email\": \"tenz@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Peter\",\n" +
                "        \"lastName\": \"Duncan\",\n" +
                "        \"address\": \"644 Gershwin Cir\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6512\",\n" +
                "        \"email\": \"jaboyd@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Foster\",\n" +
                "        \"lastName\": \"Shepard\",\n" +
                "        \"address\": \"748 Townings Dr\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6544\",\n" +
                "        \"email\": \"jaboyd@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Tony\",\n" +
                "        \"lastName\": \"Cooper\",\n" +
                "        \"address\": \"112 Steppes Pl\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6874\",\n" +
                "        \"email\": \"tcoop@ymail.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Lily\",\n" +
                "        \"lastName\": \"Cooper\",\n" +
                "        \"address\": \"489 Manchester St\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-9845\",\n" +
                "        \"email\": \"lily@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Sophia\",\n" +
                "        \"lastName\": \"Zemicks\",\n" +
                "        \"address\": \"892 Downing Ct\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-7878\",\n" +
                "        \"email\": \"soph@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Warren\",\n" +
                "        \"lastName\": \"Zemicks\",\n" +
                "        \"address\": \"892 Downing Ct\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-7512\",\n" +
                "        \"email\": \"ward@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Zach\",\n" +
                "        \"lastName\": \"Zemicks\",\n" +
                "        \"address\": \"892 Downing Ct\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-7512\",\n" +
                "        \"email\": \"zarc@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Reginold\",\n" +
                "        \"lastName\": \"Walker\",\n" +
                "        \"address\": \"908 73rd St\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-8547\",\n" +
                "        \"email\": \"reg@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Jamie\",\n" +
                "        \"lastName\": \"Peters\",\n" +
                "        \"address\": \"908 73rd St\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-7462\",\n" +
                "        \"email\": \"jpeter@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Ron\",\n" +
                "        \"lastName\": \"Peters\",\n" +
                "        \"address\": \"112 Steppes Pl\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-8888\",\n" +
                "        \"email\": \"jpeter@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Allison\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"address\": \"112 Steppes Pl\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-9888\",\n" +
                "        \"email\": \"aly@imail.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Brian\",\n" +
                "        \"lastName\": \"Stelzer\",\n" +
                "        \"address\": \"947 E. Rose Dr\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-7784\",\n" +
                "        \"email\": \"bstel@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Shawna\",\n" +
                "        \"lastName\": \"Stelzer\",\n" +
                "        \"address\": \"947 E. Rose Dr\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-7784\",\n" +
                "        \"email\": \"ssanw@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Kendrik\",\n" +
                "        \"lastName\": \"Stelzer\",\n" +
                "        \"address\": \"947 E. Rose Dr\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-7784\",\n" +
                "        \"email\": \"bstel@email.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Clive\",\n" +
                "        \"lastName\": \"Ferguson\",\n" +
                "        \"address\": \"748 Townings Dr\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-6741\",\n" +
                "        \"email\": \"clivfd@ymail.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Eric\",\n" +
                "        \"lastName\": \"Cadigan\",\n" +
                "        \"address\": \"951 LoneTree Rd\",\n" +
                "        \"city\": \"Culver\",\n" +
                "        \"zip\": \"97451\",\n" +
                "        \"phone\": \"841-874-7458\",\n" +
                "        \"email\": \"gramps@email.com\"\n" +
                "    }\n" +
                "]";
        String jsonFirestation="[\n" +
                "    {\n" +
                "        \"address\": \"1509 Culver St\",\n" +
                "        \"station\": \"3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"29 15th St\",\n" +
                "        \"station\": \"2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"834 Binoc Ave\",\n" +
                "        \"station\": \"3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"644 Gershwin Cir\",\n" +
                "        \"station\": \"1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"748 Townings Dr\",\n" +
                "        \"station\": \"3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"112 Steppes Pl\",\n" +
                "        \"station\": \"3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"489 Manchester St\",\n" +
                "        \"station\": \"4\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"892 Downing Ct\",\n" +
                "        \"station\": \"2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"908 73rd St\",\n" +
                "        \"station\": \"1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"112 Steppes Pl\",\n" +
                "        \"station\": \"4\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"947 E. Rose Dr\",\n" +
                "        \"station\": \"1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"748 Townings Dr\",\n" +
                "        \"station\": \"3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"address\": \"951 LoneTree Rd\",\n" +
                "        \"station\": \"2\"\n" +
                "    }\n" +
                "]";
        String jsonMedicalRecord="[\n" +
                "    {\n" +
                "        \"firstName\": \"John\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"birthdate\": \"03/06/1984\",\n" +
                "        \"medications\": [\n" +
                "            \"aznol:350mg\",\n" +
                "            \"hydrapermazol:100mg\"\n" +
                "        ],\n" +
                "        \"allergies\": [\n" +
                "            \"nillacilan\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Jacob\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"birthdate\": \"03/06/1989\",\n" +
                "        \"medications\": [\n" +
                "            \"pharmacol:5000mg\",\n" +
                "            \"terazine:10mg\",\n" +
                "            \"noznazol:250mg\"\n" +
                "        ],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Tenley\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"birthdate\": \"02/18/2012\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": [\n" +
                "            \"peanut\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Roger\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"birthdate\": \"09/06/2017\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Felicia\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"birthdate\": \"01/08/1986\",\n" +
                "        \"medications\": [\n" +
                "            \"tetracyclaz:650mg\"\n" +
                "        ],\n" +
                "        \"allergies\": [\n" +
                "            \"xilliathal\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Jonanathan\",\n" +
                "        \"lastName\": \"Marrack\",\n" +
                "        \"birthdate\": \"01/03/1989\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Tessa\",\n" +
                "        \"lastName\": \"Carman\",\n" +
                "        \"birthdate\": \"02/18/2012\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Peter\",\n" +
                "        \"lastName\": \"Duncan\",\n" +
                "        \"birthdate\": \"09/06/2000\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": [\n" +
                "            \"shellfish\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Foster\",\n" +
                "        \"lastName\": \"Shepard\",\n" +
                "        \"birthdate\": \"01/08/1980\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Tony\",\n" +
                "        \"lastName\": \"Cooper\",\n" +
                "        \"birthdate\": \"03/06/1994\",\n" +
                "        \"medications\": [\n" +
                "            \"hydrapermazol:300mg\",\n" +
                "            \"dodoxadin:30mg\"\n" +
                "        ],\n" +
                "        \"allergies\": [\n" +
                "            \"shellfish\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Lily\",\n" +
                "        \"lastName\": \"Cooper\",\n" +
                "        \"birthdate\": \"03/06/1994\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Sophia\",\n" +
                "        \"lastName\": \"Zemicks\",\n" +
                "        \"birthdate\": \"03/06/1988\",\n" +
                "        \"medications\": [\n" +
                "            \"aznol:60mg\",\n" +
                "            \"hydrapermazol:900mg\",\n" +
                "            \"pharmacol:5000mg\",\n" +
                "            \"terazine:500mg\"\n" +
                "        ],\n" +
                "        \"allergies\": [\n" +
                "            \"peanut\",\n" +
                "            \"shellfish\",\n" +
                "            \"aznol\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Warren\",\n" +
                "        \"lastName\": \"Zemicks\",\n" +
                "        \"birthdate\": \"03/06/1985\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Zach\",\n" +
                "        \"lastName\": \"Zemicks\",\n" +
                "        \"birthdate\": \"03/06/2017\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Reginold\",\n" +
                "        \"lastName\": \"Walker\",\n" +
                "        \"birthdate\": \"08/30/1979\",\n" +
                "        \"medications\": [\n" +
                "            \"thradox:700mg\"\n" +
                "        ],\n" +
                "        \"allergies\": [\n" +
                "            \"illisoxian\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Jamie\",\n" +
                "        \"lastName\": \"Peters\",\n" +
                "        \"birthdate\": \"03/06/1982\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Ron\",\n" +
                "        \"lastName\": \"Peters\",\n" +
                "        \"birthdate\": \"04/06/1965\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Allison\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"birthdate\": \"03/15/1965\",\n" +
                "        \"medications\": [\n" +
                "            \"aznol:200mg\"\n" +
                "        ],\n" +
                "        \"allergies\": [\n" +
                "            \"nillacilan\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Brian\",\n" +
                "        \"lastName\": \"Stelzer\",\n" +
                "        \"birthdate\": \"12/06/1975\",\n" +
                "        \"medications\": [\n" +
                "            \"ibupurin:200mg\",\n" +
                "            \"hydrapermazol:400mg\"\n" +
                "        ],\n" +
                "        \"allergies\": [\n" +
                "            \"nillacilan\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Shawna\",\n" +
                "        \"lastName\": \"Stelzer\",\n" +
                "        \"birthdate\": \"07/08/1980\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Kendrik\",\n" +
                "        \"lastName\": \"Stelzer\",\n" +
                "        \"birthdate\": \"03/06/2014\",\n" +
                "        \"medications\": [\n" +
                "            \"noxidian:100mg\",\n" +
                "            \"pharmacol:2500mg\"\n" +
                "        ],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Clive\",\n" +
                "        \"lastName\": \"Ferguson\",\n" +
                "        \"birthdate\": \"03/06/1994\",\n" +
                "        \"medications\": [],\n" +
                "        \"allergies\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Eric\",\n" +
                "        \"lastName\": \"Cadigan\",\n" +
                "        \"birthdate\": \"08/06/1945\",\n" +
                "        \"medications\": [\n" +
                "            \"tradoxidine:400mg\"\n" +
                "        ],\n" +
                "        \"allergies\": []\n" +
                "    }\n" +
                "]";
        ObjectMapper mapper = new ObjectMapper();
        try {
            personList = mapper.readValue(jsonPerson, new TypeReference<List<Person>>() {});
            firestationList=mapper.readValue(jsonFirestation, new TypeReference<List<Firestation>>() {});
            medicalRecordList=mapper.readValue(jsonMedicalRecord, new TypeReference<List<MedicalRecord>>() {});
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        when(rc.getFirestations()).thenReturn(firestationList);
        when(rc.getMedicalrecords()).thenReturn(medicalRecordList);
        when(rc.getPersons()).thenReturn(personList);
    }
    
    @Test
    public void endpoint1ToJSonTest() {
        String result="{\"station\":\"1\",\"persons\":[{\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"phone\":\"841-874-6512\"},{\"firstName\":\"Reginold\",\"lastName\":\"Walker\",\"address\":\"908 73rd St\",\"phone\":\"841-874-8547\"},{\"firstName\":\"Jamie\",\"lastName\":\"Peters\",\"address\":\"908 73rd St\",\"phone\":\"841-874-7462\"},{\"firstName\":\"Brian\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"phone\":\"841-874-7784\"},{\"firstName\":\"Shawna\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"phone\":\"841-874-7784\"},{\"firstName\":\"Kendrik\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"phone\":\"841-874-7784\"}],\"childrenAndAdults\":{\"children\":1,\"adults\":5}}";
        assertThat(extraProxy.endpoint1ToJSon("1")).isEqualTo(result);
    }
    @Test
    public void endpoint2ToJSonTest() {
        String result="{\"address\":\"1509 Culver St\",\"childrenData\":[{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"age\":9},{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"age\":3}],\"otherPersonsPerAddress\":[{\"firstName\":\"John\",\"lastName\":\"Boyd\"},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\"},{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\"}]}";
        assertThat(extraProxy.endpoint2ToJSon("1509 Culver St")).isEqualTo(result);
    }
    @Test
    public void endpoint3ToJSonTest() {
        String result="{\"station\":\"2\",\"phoneNumbers\":[\"841-874-6513\",\"841-874-7878\",\"841-874-7512\",\"841-874-7512\",\"841-874-7458\"]}";
        assertThat(extraProxy.endpoint3ToJSon("2")).isEqualTo(result);
    }
    @Test
    public void endpoint4ToJSonTest() {
        String result="{\"address\":\"1509 Culver St\",\"station\":\"3\",\"personsData\":[{\"lastName\":\"Boyd\",\"phone\":\"841-874-6512\",\"age\":37,\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]},{\"lastName\":\"Boyd\",\"phone\":\"841-874-6513\",\"age\":32,\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]},{\"lastName\":\"Boyd\",\"phone\":\"841-874-6512\",\"age\":9,\"medications\":[],\"allergies\":[\"peanut\"]},{\"lastName\":\"Boyd\",\"phone\":\"841-874-6512\",\"age\":3,\"medications\":[],\"allergies\":[]},{\"lastName\":\"Boyd\",\"phone\":\"841-874-6544\",\"age\":35,\"medications\":[\"tetracyclaz:650mg\"],\"allergies\":[\"xilliathal\"]}]}";
        assertThat(extraProxy.endpoint4ToJSon("1509 Culver St")).isEqualTo(result);
    }
    @Test
    public void endpoint5ToJSonTest() {
        String result="{\"station\":\"2\",\"addressesAndPersons\":[{\"address\":\"29 15th St\",\"personsPerAddress\":[{\"lastName\":\"Marrack\",\"phone\":\"841-874-6513\",\"age\":32,\"medications\":[],\"allergies\":[]}]},{\"address\":\"892 Downing Ct\",\"personsPerAddress\":[{\"lastName\":\"Zemicks\",\"phone\":\"841-874-7512\",\"age\":4,\"medications\":[],\"allergies\":[]},{\"lastName\":\"Zemicks\",\"phone\":\"841-874-7512\",\"age\":4,\"medications\":[],\"allergies\":[]},{\"lastName\":\"Zemicks\",\"phone\":\"841-874-7512\",\"age\":4,\"medications\":[],\"allergies\":[]}]},{\"address\":\"951 LoneTree Rd\",\"personsPerAddress\":[{\"lastName\":\"Cadigan\",\"phone\":\"841-874-7458\",\"age\":76,\"medications\":[\"tradoxidine:400mg\"],\"allergies\":[]}]}]}";
        assertThat(extraProxy.endpoint5ToJSon("2")).isEqualTo(result);
    }
    @Test
    public void endpoint6ToJSonTest() {
        String result="[{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":37,\"email\":\"jaboyd@email.com\",\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":\"[\\\"nillacilan\\\"]\"},{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":32,\"email\":\"drk@email.com\",\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":\"[]\"},{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":9,\"email\":\"tenz@email.com\",\"medications\":[],\"allergies\":\"[\\\"peanut\\\"]\"},{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":3,\"email\":\"jaboyd@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":35,\"email\":\"jaboyd@email.com\",\"medications\":[\"tetracyclaz:650mg\"],\"allergies\":\"[\\\"xilliathal\\\"]\"},{\"lastName\":\"Marrack\",\"address\":\"29 15th St\",\"age\":32,\"email\":\"drk@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Carman\",\"address\":\"834 Binoc Ave\",\"age\":9,\"email\":\"tenz@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"age\":20,\"email\":\"jaboyd@email.com\",\"medications\":[],\"allergies\":\"[\\\"shellfish\\\"]\"},{\"lastName\":\"Shepard\",\"address\":\"748 Townings Dr\",\"age\":41,\"email\":\"jaboyd@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Cooper\",\"address\":\"112 Steppes Pl\",\"age\":27,\"email\":\"tcoop@ymail.com\",\"medications\":[\"hydrapermazol:300mg\",\"dodoxadin:30mg\"],\"allergies\":\"[\\\"shellfish\\\"]\"},{\"lastName\":\"Cooper\",\"address\":\"489 Manchester St\",\"age\":27,\"email\":\"lily@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":33,\"email\":\"soph@email.com\",\"medications\":[\"aznol:60mg\",\"hydrapermazol:900mg\",\"pharmacol:5000mg\",\"terazine:500mg\"],\"allergies\":\"[\\\"peanut\\\",\\\"shellfish\\\",\\\"aznol\\\"]\"},{\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":36,\"email\":\"ward@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":4,\"email\":\"zarc@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Walker\",\"address\":\"908 73rd St\",\"age\":41,\"email\":\"reg@email.com\",\"medications\":[\"thradox:700mg\"],\"allergies\":\"[\\\"illisoxian\\\"]\"},{\"lastName\":\"Peters\",\"address\":\"908 73rd St\",\"age\":39,\"email\":\"jpeter@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Peters\",\"address\":\"112 Steppes Pl\",\"age\":56,\"email\":\"jpeter@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Boyd\",\"address\":\"112 Steppes Pl\",\"age\":56,\"email\":\"aly@imail.com\",\"medications\":[\"aznol:200mg\"],\"allergies\":\"[\\\"nillacilan\\\"]\"},{\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":45,\"email\":\"bstel@email.com\",\"medications\":[\"ibupurin:200mg\",\"hydrapermazol:400mg\"],\"allergies\":\"[\\\"nillacilan\\\"]\"},{\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":41,\"email\":\"ssanw@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":7,\"email\":\"bstel@email.com\",\"medications\":[\"noxidian:100mg\",\"pharmacol:2500mg\"],\"allergies\":\"[]\"},{\"lastName\":\"Ferguson\",\"address\":\"748 Townings Dr\",\"age\":27,\"email\":\"clivfd@ymail.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Cadigan\",\"address\":\"951 LoneTree Rd\",\"age\":76,\"email\":\"gramps@email.com\",\"medications\":[\"tradoxidine:400mg\"],\"allergies\":\"[]\"}]";
        assertThat(extraProxy.endpoint6ToJSon()).isEqualTo(result);
    }
    @Test
    public void endpoint7ToJSonTest() {
        String result="{\"city\":\"Culver\",\"emails\":[\"jaboyd@email.com\",\"drk@email.com\",\"tenz@email.com\",\"jaboyd@email.com\",\"jaboyd@email.com\",\"drk@email.com\",\"tenz@email.com\",\"jaboyd@email.com\",\"jaboyd@email.com\",\"tcoop@ymail.com\",\"lily@email.com\",\"soph@email.com\",\"ward@email.com\",\"zarc@email.com\",\"reg@email.com\",\"jpeter@email.com\",\"jpeter@email.com\",\"aly@imail.com\",\"bstel@email.com\",\"ssanw@email.com\",\"bstel@email.com\",\"clivfd@ymail.com\",\"gramps@email.com\"]}";
        assertThat(extraProxy.endpoint7ToJSon("Culver")).isEqualTo(result);
    }
}
