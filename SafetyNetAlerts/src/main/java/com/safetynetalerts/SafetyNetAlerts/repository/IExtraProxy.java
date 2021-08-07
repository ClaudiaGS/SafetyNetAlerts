package com.safetynetalerts.SafetyNetAlerts.repository;

public interface IExtraProxy {
    
    public String endpoint1ToJSon(String station);
    
    public String endpoint2ToJSon(String address);
    
    public String endpoint3ToJSon(String station);
    
    public String endpoint4ToJSon(String address);
    
    public String endpoint5ToJSon(String station);
    
    public String endpoint6ToJSon();
    
    public String endpoint7ToJSon(String city);
    
    public void setRecoveredData(IRecoveredData recoveredData);
}
