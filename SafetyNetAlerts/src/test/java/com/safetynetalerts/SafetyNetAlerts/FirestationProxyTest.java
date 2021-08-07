package com.safetynetalerts.SafetyNetAlerts;

import com.safetynetalerts.SafetyNetAlerts.model.Firestation;
import com.safetynetalerts.SafetyNetAlerts.repository.FirestationsProxy;
import com.safetynetalerts.SafetyNetAlerts.repository.IFirestationsProxy;
import com.safetynetalerts.SafetyNetAlerts.repository.IRecoveredData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
@SpringBootTest(classes = FirestationsProxy.class)
public class FirestationProxyTest {
    @MockBean
    IRecoveredData rc;
    
    @Autowired
    IFirestationsProxy firestationsProxy;
    
    List<Firestation> firestationList = new ArrayList<>();
    Firestation firestationTest1 = new Firestation();
    Firestation firestationTest2 = new Firestation();
    
    @BeforeEach
    private void config(){
        firestationTest1.setStation("1");
        firestationTest1.setAddress("95 Culver St");
        firestationTest2.setStation("2");
        firestationTest2.setAddress("85 Pink St");
        firestationList.add(firestationTest1);
        firestationList.add(firestationTest2);
        when(rc.getFirestations()).thenReturn(firestationList);
    }
    
    @Test
    public void readFirestationTest() {
        assertThat(firestationsProxy.readFirestations()).isEqualTo(rc.getFirestations());
    }
    
    @Test
    public void deleteFirestationTest() {
        List<Firestation>result=rc.getFirestations();
        result.remove(firestationTest1);
        HashMap<String, String> addressOrStation = new HashMap<>();
        addressOrStation.put("station","1");
        assertThat(firestationsProxy.deleteFirestation(addressOrStation)).isEqualTo(result);
    }
    
    @Test
    public void modifyFirestationTest(){
        List<Firestation>resultList=rc.getFirestations();
        Firestation firestationToModify=rc.getFirestations().get(0);
        firestationToModify.setStation("3");
        assertThat(firestationsProxy.modifyFirestation("95 Culver St","3")).isEqualTo(resultList);
    }
    
    @Test
    public void addFirestationTest(){
        Firestation firestationToAdd=new Firestation();
        firestationToAdd.setAddress("1500 Pink St");
        firestationToAdd.setStation("3");
        List<Firestation> resultList=rc.getFirestations();
        resultList.add(firestationToAdd);
        assertThat(firestationsProxy.addFirestation("1500 Pink St","3")).isEqualTo(resultList);
    }
    
}

