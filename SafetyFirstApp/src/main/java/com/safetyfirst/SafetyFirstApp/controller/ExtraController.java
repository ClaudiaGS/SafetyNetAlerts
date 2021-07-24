package com.safetyfirst.SafetyFirstApp.controller;

import com.safetyfirst.SafetyFirstApp.service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExtraController {
    @Autowired
    ExtraService extraService;
    @GetMapping("/firestation")
    public String endpoint1ToJSon(@RequestParam String stationNumber){
        return extraService.endpoint1ToJSon(stationNumber);
    }
    @GetMapping("/childAlert")
    public String endpoint2ToJSon(@RequestParam String address){
        return extraService.endpoint2ToJSon(address);
    }
    @GetMapping("/phoneAlert")
    public String endpoint3ToJSon(@RequestParam String station){
        return extraService.endpoint3ToJSon(station);
    }
    @GetMapping("/fire")
    public String endpoint4ToJSon(@RequestParam String address){
        return extraService.endpoint4ToJSon(address);
    }
    @GetMapping("/stations")
    public String endpoint5ToJSon(@RequestParam String station){
        return extraService.endpoint5ToJSon(station);
    }
    @GetMapping("/personInfo")
    public String endpoint6ToJSon(){
        return extraService.endpoint6ToJSon();
    }
    @GetMapping("/communityEmail")
    public String endpoint7ToJSon(String city){
        return extraService.endpoint7ToJSon(city);
    }
}

