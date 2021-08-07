package com.safetynetalerts.SafetyNetAlerts.config;

import com.safetynetalerts.SafetyNetAlerts.repository.IRecoveredData;
import com.safetynetalerts.SafetyNetAlerts.repository.RecoveredData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    @Autowired
    private DataSources dataSources;
    @Bean
    public IRecoveredData createRecoveredData() {
        IRecoveredData dataRecovery = new RecoveredData();
        dataRecovery.readData(dataSources);
        return dataRecovery;
    }
}
