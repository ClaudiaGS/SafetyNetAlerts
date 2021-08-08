package com.safetynetalerts.SafetyNetAlerts.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetalerts.SafetyNetAlerts.config.DataSources;
import com.safetynetalerts.SafetyNetAlerts.integration.config.DataTestConfig;
import com.safetynetalerts.SafetyNetAlerts.repository.IFirestationsProxy;
import com.safetynetalerts.SafetyNetAlerts.repository.IRecoveredData;
import com.safetynetalerts.SafetyNetAlerts.service.IFirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(DataTestConfig.class)
@SpringBootTest
public class FirestationServiceIT {
    
    @Autowired
    IFirestationsProxy firestationsProxy;
    @Autowired
    IFirestationService firestationService;
    @Autowired
    DataSources dataSources;
    @Autowired
    IRecoveredData recoveredTestData;
    
    @BeforeEach
    public void config() {
        recoveredTestData.readData(dataSources);
        firestationsProxy.setRecoveredData(recoveredTestData);
        
    }
    
    @Test
    public void readFirestationsIT() {
        assertThat(asJsonString(firestationService.readFirestations())).isEqualTo("[{\"address\":\"1509 Culver St\",\"station\":\"3\"},{\"address\":\"29 15th St\",\"station\":\"2\"},{\"address\":\"834 Binoc Ave\",\"station\":\"3\"},{\"address\":\"644 Gershwin Cir\",\"station\":\"1\"},{\"address\":\"748 Townings Dr\",\"station\":\"3\"},{\"address\":\"112 Steppes Pl\",\"station\":\"3\"},{\"address\":\"489 Manchester St\",\"station\":\"4\"},{\"address\":\"892 Downing Ct\",\"station\":\"2\"},{\"address\":\"908 73rd St\",\"station\":\"1\"},{\"address\":\"112 Steppes Pl\",\"station\":\"4\"},{\"address\":\"947 E. Rose Dr\",\"station\":\"1\"},{\"address\":\"748 Townings Dr\",\"station\":\"3\"},{\"address\":\"951 LoneTree Rd\",\"station\":\"2\"}]");
    }
    
    @Test
    public void deleteFirestationIT() {
        HashMap<String, String> addressOrStation = new HashMap<>();
        addressOrStation.put("station", "1");
        assertThat(asJsonString(firestationService.deleteFirestation(addressOrStation))).isEqualTo("[{\"address\":\"1509 Culver St\",\"station\":\"3\"},{\"address\":\"29 15th St\",\"station\":\"2\"},{\"address\":\"834 Binoc Ave\",\"station\":\"3\"},{\"address\":\"748 Townings Dr\",\"station\":\"3\"},{\"address\":\"112 Steppes Pl\",\"station\":\"3\"},{\"address\":\"489 Manchester St\",\"station\":\"4\"},{\"address\":\"892 Downing Ct\",\"station\":\"2\"},{\"address\":\"112 Steppes Pl\",\"station\":\"4\"},{\"address\":\"748 Townings Dr\",\"station\":\"3\"},{\"address\":\"951 LoneTree Rd\",\"station\":\"2\"}]");
    }
    
    @Test
    public void modifyFirestationIT() {
        assertThat(asJsonString(firestationService.modifyFirestation("1509 Culver St", "1"))).isEqualTo("[{\"address\":\"1509 Culver St\",\"station\":\"1\"},{\"address\":\"29 15th St\",\"station\":\"2\"},{\"address\":\"834 Binoc Ave\",\"station\":\"3\"},{\"address\":\"644 Gershwin Cir\",\"station\":\"1\"},{\"address\":\"748 Townings Dr\",\"station\":\"3\"},{\"address\":\"112 Steppes Pl\",\"station\":\"3\"},{\"address\":\"489 Manchester St\",\"station\":\"4\"},{\"address\":\"892 Downing Ct\",\"station\":\"2\"},{\"address\":\"908 73rd St\",\"station\":\"1\"},{\"address\":\"112 Steppes Pl\",\"station\":\"4\"},{\"address\":\"947 E. Rose Dr\",\"station\":\"1\"},{\"address\":\"748 Townings Dr\",\"station\":\"3\"},{\"address\":\"951 LoneTree Rd\",\"station\":\"2\"}]");
    }
    
    @Test
    public void addFirestationIT() {
        assertThat(asJsonString(firestationService.addFirestation("100 Pink St", "2"))).isEqualTo("[{\"address\":\"1509 Culver St\",\"station\":\"3\"},{\"address\":\"29 15th St\",\"station\":\"2\"},{\"address\":\"834 Binoc Ave\",\"station\":\"3\"},{\"address\":\"644 Gershwin Cir\",\"station\":\"1\"},{\"address\":\"748 Townings Dr\",\"station\":\"3\"},{\"address\":\"112 Steppes Pl\",\"station\":\"3\"},{\"address\":\"489 Manchester St\",\"station\":\"4\"},{\"address\":\"892 Downing Ct\",\"station\":\"2\"},{\"address\":\"908 73rd St\",\"station\":\"1\"},{\"address\":\"112 Steppes Pl\",\"station\":\"4\"},{\"address\":\"947 E. Rose Dr\",\"station\":\"1\"},{\"address\":\"748 Townings Dr\",\"station\":\"3\"},{\"address\":\"951 LoneTree Rd\",\"station\":\"2\"},{\"address\":\"100 Pink St\",\"station\":\"2\"}]");
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
