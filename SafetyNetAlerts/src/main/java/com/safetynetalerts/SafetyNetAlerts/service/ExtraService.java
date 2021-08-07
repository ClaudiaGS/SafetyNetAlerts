package com.safetynetalerts.SafetyNetAlerts.service;

import com.safetynetalerts.SafetyNetAlerts.repository.ExtraProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraService implements IExtraService {
    @Autowired
    ExtraProxy extraProxy;
    public String endpoint1ToJSon(String station){
        return extraProxy.endpoint1ToJSon(station);
    }
    public String endpoint2ToJSon(String address){
        return extraProxy.endpoint2ToJSon(address);
    }
    public String endpoint3ToJSon(String station){
        return extraProxy.endpoint3ToJSon(station);
    }
    public String endpoint4ToJSon(String address){
        return extraProxy.endpoint4ToJSon(address);
    }
    public String endpoint5ToJSon(String station){
        return extraProxy.endpoint5ToJSon(station);
    }
    public String endpoint6ToJSon(){
        return extraProxy.endpoint6ToJSon();
    }
    public String endpoint7ToJSon(String city){
        return extraProxy.endpoint7ToJSon(city);
    }
}
