package com.safetyfirst.SafetyFirstApp;

import com.safetyfirst.SafetyFirstApp.model.Person;
import com.safetyfirst.SafetyFirstApp.repository.IPersonsProxy;
import com.safetyfirst.SafetyFirstApp.repository.IRecoveredData;
import com.safetyfirst.SafetyFirstApp.repository.PersonsProxy;
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

@SpringBootTest(classes = PersonsProxy.class)
public class PersonProxyTest {
    @MockBean
    IRecoveredData rc;
    
    @Autowired
    IPersonsProxy personsProxy;
    
    List<Person> personList = new ArrayList<>();
    Person testPerson1 = new Person();
    Person testPerson2 = new Person();
    
    @BeforeEach
    private void config(){
        testPerson1.setFirstName("Anne");
        testPerson1.setLastName("Dubois");
        testPerson1.setPhone("000-000");
        testPerson2.setFirstName("Arnaud");
        testPerson2.setLastName("Dub");
        personList.add(testPerson1);
        personList.add(testPerson2);
        when(rc.getPersons()).thenReturn(personList);
    }
    
    @Test
    public void readPersonTest() {
        assertThat(personsProxy.readPersons()).isEqualTo(personList);
    }
    
    @Test
    public void deletePersonTest() {
        List<Person>result=rc.getPersons();
        result.remove(testPerson1);
        assertThat(personsProxy.deletePerson("Anne", "Dubois")).isEqualTo(result);
    }
    
    @Test
    public void modifyPersonTest(){
        List<Person>resultList=rc.getPersons();
        resultList.get(0).setPhone("111-111");
        resultList.get(0).setAddress("100 Blue St");
        resultList.get(0).setCity("Orleans");
        resultList.get(0).setEmail("aaa@");
        resultList.get(0).setZip("1111");
        HashMap<String ,String > params=new HashMap<>();
        params.put("phone","111-111");
        params.put("address","100 Blue St");
        params.put("zip","1111");
        params.put("email", "aaa@");
        params.put("city", "Orleans");
        assertThat(personsProxy.modifyPerson("Anne","Dubois",params)).isEqualTo(resultList);
    }
    
    @Test
    public void addPersonTest(){
        Person personToAdd=new Person();
        List<Person> resultList=rc.getPersons();
        resultList.add(personToAdd);
        assertThat(personsProxy.addPerson(personToAdd)).isEqualTo(resultList);
    }
    
}
