package com.safetyfirst.SafetyFirstApp.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

//    public Person(String firstName, String lastName, String adress, String city, String zip, String phone, String email) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.adress = adress;
//        this.city = city;
//        this.zip = zip;
//        this.phone = phone;
//        this.email = email;
//    }
//    @Bean
//    @Override
//    public String toString() {
//        return "Person{" +
//                "firstName:" + firstName +
//                "lastName:" + lastName + '\'' +
//                ", adress:'" + adress + '\'' +
//                ", city:" + city + '\'' +
//                ", phone:" + phone +
//                ", email:" + email +
//                '}';
//    }
}
