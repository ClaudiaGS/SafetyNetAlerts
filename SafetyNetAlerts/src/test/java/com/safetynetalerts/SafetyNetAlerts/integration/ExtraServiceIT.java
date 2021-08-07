package com.safetynetalerts.SafetyNetAlerts.integration;

import com.safetynetalerts.SafetyNetAlerts.config.DataSources;
import com.safetynetalerts.SafetyNetAlerts.integration.config.RecoveredTestData;
import com.safetynetalerts.SafetyNetAlerts.repository.IExtraProxy;
import com.safetynetalerts.SafetyNetAlerts.repository.IRecoveredData;
import com.safetynetalerts.SafetyNetAlerts.service.IExtraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ExtraServiceIT {
    
    @Autowired
    IExtraProxy extraProxy;
    @Autowired
    IExtraService extraService;
    
    @Autowired
    DataSources dataSources;
    
    
    IRecoveredData recoveredTestData = new RecoveredTestData();
    
    
    @BeforeEach
    public void config() {
        recoveredTestData.readData(dataSources);
        extraProxy.setRecoveredData(recoveredTestData);
        
    }
    
    @Test
    public void endPointsIT() {
        assertThat(extraService.endpoint1ToJSon("1")).isEqualTo("{\"station\":\"1\",\"persons\":[{\"firstName\":\"TestPeter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"phone\":\"841-874-6512\"},{\"firstName\":\"TestReginold\",\"lastName\":\"Walker\",\"address\":\"908 73rd St\",\"phone\":\"841-874-8547\"},{\"firstName\":\"TestJamie\",\"lastName\":\"Peters\",\"address\":\"908 73rd St\",\"phone\":\"841-874-7462\"},{\"firstName\":\"TestBrian\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"phone\":\"841-874-7784\"},{\"firstName\":\"TestShawna\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"phone\":\"841-874-7784\"},{\"firstName\":\"TestKendrik\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"phone\":\"841-874-7784\"}],\"childrenAndAdults\":{\"children\":1,\"adults\":5}}");
        assertThat(extraService.endpoint2ToJSon("1509 Culver St")).isEqualTo("{\"address\":\"1509 Culver St\",\"childrenData\":[{\"firstName\":\"TestTenley\",\"lastName\":\"Boyd\",\"age\":9},{\"firstName\":\"TestRoger\",\"lastName\":\"Boyd\",\"age\":3}],\"otherPersonsPerAddress\":[{\"firstName\":\"TestJohn\",\"lastName\":\"Boyd\"},{\"firstName\":\"TestJacob\",\"lastName\":\"Boyd\"},{\"firstName\":\"TestFelicia\",\"lastName\":\"Boyd\"}]}");
        assertThat(extraService.endpoint3ToJSon("2")).isEqualTo("{\"station\":\"2\",\"phoneNumbers\":[\"841-874-6513\",\"841-874-7878\",\"841-874-7512\",\"841-874-7512\",\"841-874-7458\"]}");
        assertThat(extraService.endpoint4ToJSon("1509 Culver St")).isEqualTo("{\"address\":\"1509 Culver St\",\"station\":\"3\",\"personsData\":[{\"lastName\":\"Boyd\",\"phone\":\"841-874-6512\",\"age\":37,\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]},{\"lastName\":\"Boyd\",\"phone\":\"841-874-6513\",\"age\":32,\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]},{\"lastName\":\"Boyd\",\"phone\":\"841-874-6512\",\"age\":9,\"medications\":[],\"allergies\":[\"peanut\"]},{\"lastName\":\"Boyd\",\"phone\":\"841-874-6512\",\"age\":3,\"medications\":[],\"allergies\":[]},{\"lastName\":\"Boyd\",\"phone\":\"841-874-6544\",\"age\":35,\"medications\":[\"tetracyclaz:650mg\"],\"allergies\":[\"xilliathal\"]}]}");
        assertThat(extraService.endpoint5ToJSon("2")).isEqualTo("{\"station\":\"2\",\"addressesAndPersons\":[{\"address\":\"29 15th St\",\"personsPerAddress\":[{\"lastName\":\"Marrack\",\"phone\":\"841-874-6513\",\"age\":32,\"medications\":[],\"allergies\":[]}]},{\"address\":\"892 Downing Ct\",\"personsPerAddress\":[{\"lastName\":\"Zemicks\",\"phone\":\"841-874-7512\",\"age\":4,\"medications\":[],\"allergies\":[]},{\"lastName\":\"Zemicks\",\"phone\":\"841-874-7512\",\"age\":4,\"medications\":[],\"allergies\":[]},{\"lastName\":\"Zemicks\",\"phone\":\"841-874-7512\",\"age\":4,\"medications\":[],\"allergies\":[]}]},{\"address\":\"951 LoneTree Rd\",\"personsPerAddress\":[{\"lastName\":\"Cadigan\",\"phone\":\"841-874-7458\",\"age\":76,\"medications\":[\"tradoxidine:400mg\"],\"allergies\":[]}]}]}");
        assertThat(extraService.endpoint6ToJSon()).isEqualTo("[{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":37,\"email\":\"jaboyd@email.com\",\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":\"[\\\"nillacilan\\\"]\"},{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":32,\"email\":\"drk@email.com\",\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":\"[]\"},{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":9,\"email\":\"tenz@email.com\",\"medications\":[],\"allergies\":\"[\\\"peanut\\\"]\"},{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":3,\"email\":\"jaboyd@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":35,\"email\":\"jaboyd@email.com\",\"medications\":[\"tetracyclaz:650mg\"],\"allergies\":\"[\\\"xilliathal\\\"]\"},{\"lastName\":\"Marrack\",\"address\":\"29 15th St\",\"age\":32,\"email\":\"drk@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Carman\",\"address\":\"834 Binoc Ave\",\"age\":9,\"email\":\"tenz@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"age\":20,\"email\":\"jaboyd@email.com\",\"medications\":[],\"allergies\":\"[\\\"shellfish\\\"]\"},{\"lastName\":\"Shepard\",\"address\":\"748 Townings Dr\",\"age\":41,\"email\":\"jaboyd@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Cooper\",\"address\":\"112 Steppes Pl\",\"age\":27,\"email\":\"tcoop@ymail.com\",\"medications\":[\"hydrapermazol:300mg\",\"dodoxadin:30mg\"],\"allergies\":\"[\\\"shellfish\\\"]\"},{\"lastName\":\"Cooper\",\"address\":\"489 Manchester St\",\"age\":27,\"email\":\"lily@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":33,\"email\":\"soph@email.com\",\"medications\":[\"aznol:60mg\",\"hydrapermazol:900mg\",\"pharmacol:5000mg\",\"terazine:500mg\"],\"allergies\":\"[\\\"peanut\\\",\\\"shellfish\\\",\\\"aznol\\\"]\"},{\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":36,\"email\":\"ward@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":4,\"email\":\"zarc@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Walker\",\"address\":\"908 73rd St\",\"age\":41,\"email\":\"reg@email.com\",\"medications\":[\"thradox:700mg\"],\"allergies\":\"[\\\"illisoxian\\\"]\"},{\"lastName\":\"Peters\",\"address\":\"908 73rd St\",\"age\":39,\"email\":\"jpeter@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Peters\",\"address\":\"112 Steppes Pl\",\"age\":56,\"email\":\"jpeter@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Boyd\",\"address\":\"112 Steppes Pl\",\"age\":56,\"email\":\"aly@imail.com\",\"medications\":[\"aznol:200mg\"],\"allergies\":\"[\\\"nillacilan\\\"]\"},{\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":45,\"email\":\"bstel@email.com\",\"medications\":[\"ibupurin:200mg\",\"hydrapermazol:400mg\"],\"allergies\":\"[\\\"nillacilan\\\"]\"},{\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":41,\"email\":\"ssanw@email.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":7,\"email\":\"bstel@email.com\",\"medications\":[\"noxidian:100mg\",\"pharmacol:2500mg\"],\"allergies\":\"[]\"},{\"lastName\":\"Ferguson\",\"address\":\"748 Townings Dr\",\"age\":27,\"email\":\"clivfd@ymail.com\",\"medications\":[],\"allergies\":\"[]\"},{\"lastName\":\"Cadigan\",\"address\":\"951 LoneTree Rd\",\"age\":76,\"email\":\"gramps@email.com\",\"medications\":[\"tradoxidine:400mg\"],\"allergies\":\"[]\"}]");
        assertThat(extraService.endpoint7ToJSon("Culver")).isEqualTo("{\"city\":\"Culver\",\"emails\":[\"jaboyd@email.com\",\"drk@email.com\",\"tenz@email.com\",\"jaboyd@email.com\",\"jaboyd@email.com\",\"drk@email.com\",\"tenz@email.com\",\"jaboyd@email.com\",\"jaboyd@email.com\",\"tcoop@ymail.com\",\"lily@email.com\",\"soph@email.com\",\"ward@email.com\",\"zarc@email.com\",\"reg@email.com\",\"jpeter@email.com\",\"jpeter@email.com\",\"aly@imail.com\",\"bstel@email.com\",\"ssanw@email.com\",\"bstel@email.com\",\"clivfd@ymail.com\",\"gramps@email.com\"]}");
        
    }
    
}
