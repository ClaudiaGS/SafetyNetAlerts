package com.safetyfirst.SafetyFirstApp.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Firestation {
    private String address;
    private String station;
    
//    public Firestation(String adress, String station) {
//        this.adress = adress;
//        this.station = station;
//    }
    

}
