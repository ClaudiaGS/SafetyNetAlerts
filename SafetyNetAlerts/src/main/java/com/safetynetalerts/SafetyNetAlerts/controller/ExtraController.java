package com.safetynetalerts.SafetyNetAlerts.controller;

import com.safetynetalerts.SafetyNetAlerts.service.IExtraService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExtraController {
    private static final Logger logger = LogManager.getLogger(ExtraController.class);
    @Autowired
    IExtraService extraService;
    @GetMapping("/firestation")
    public String endpoint1ToJSon(@RequestParam String stationNumber){
        String jsonString=extraService.endpoint1ToJSon(stationNumber);
        logger.info("Exit data for endpoint 1 is "+jsonString);
        return jsonString;
    }
    @GetMapping("/childAlert")
    public String endpoint2ToJSon(@RequestParam String address){
        String jsonString=extraService.endpoint2ToJSon(address);
        logger.info("Exit data for endpoint 2 is "+jsonString);
        return jsonString;
    }
    @GetMapping("/phoneAlert")
    public String endpoint3ToJSon(@RequestParam String station){
        String jsonString=extraService.endpoint3ToJSon(station);
        logger.info("Exit data for endpoint 3 is "+jsonString);
        return jsonString;
    }
    @GetMapping("/fire")
    public String endpoint4ToJSon(@RequestParam String address){
        String jsonString=extraService.endpoint4ToJSon(address);
        logger.info("Exit data for endpoint 4 is "+jsonString);
        return jsonString;
    }
    @GetMapping("/flood/stations")
    public String endpoint5ToJSon(@RequestParam String station){
        String jsonString=extraService.endpoint5ToJSon(station);
        logger.info("Exit data for endpoint 5 is "+jsonString);
        return jsonString;
    }
    @GetMapping("/personInfo")
    public String endpoint6ToJSon(){
        String jsonString=extraService.endpoint6ToJSon();
        logger.info("Exit data for endpoint 6 is "+jsonString);
        return jsonString;
    }
    @GetMapping("/communityEmail")
    public String endpoint7ToJSon(String city){
        String jsonString=extraService.endpoint7ToJSon(city);
        logger.info("Exit data for endpoint 7 is "+jsonString);
        return jsonString;
    }
}

