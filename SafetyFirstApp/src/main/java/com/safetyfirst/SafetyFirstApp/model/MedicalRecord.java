package com.safetyfirst.SafetyFirstApp.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class MedicalRecord {
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String>medications;
    private List<String>allergies;
    
//    public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.birthdate = birthdate;
//        this.medications = medications;
//        this.allergies = allergies;
//    }

}
