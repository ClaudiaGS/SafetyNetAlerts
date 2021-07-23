package com.safetyfirst.SafetyFirstApp.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyfirst.SafetyFirstApp.model.Firestation;
import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import com.safetyfirst.SafetyFirstApp.model.Person;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Data
@Component
public class ReadData implements IDataReader {
    
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalrecords;
//
//    public ReadData(List<Person> personslist, List<Firestation> firestationsList, List<MedicalRecord> medicalRecordsList) {
//        this.personslist = personslist;
//        this.firestationsList = firestationsList;
//        this.medicalRecordsList = medicalRecordsList;
//    }
    
    @Override
    public ReadData readData() {

        ObjectMapper mapper = new ObjectMapper();
        ReadData myObjects = null;
        try {
            URL url=new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
            myObjects = mapper.readValue(url, ReadData.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("myObjects are "+myObjects);
        persons=myObjects.persons;
        firestations=myObjects.firestations;
        medicalrecords=myObjects.medicalrecords;
        return myObjects;
    }
    

}

//        ObjectMapper objectMapper = new ObjectMapper();
//        ReadData recoveredData=null;
//        URL jSonUrl = null;
//        try {
//            jSonUrl = new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
//            recoveredData = objectMapper.readValue(jSonUrl, ReadData.class);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        //print customer details
//        System.out.println(recoveredData);
//        return recoveredData;

//        ObjectMapper mapper=new ObjectMapper();
//        ReadData[] myObjects=null;
//        try {
//             myObjects=mapper.readValue("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json",ReadData[].class);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return myObjects;
//https://www.edureka.co/community/22395/using-jackson-to-deserialise-an-array-of-object
//  https://attacomsian.com/blog/processing-json-spring-boot