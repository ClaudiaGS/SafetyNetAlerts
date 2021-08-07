package com.safetynetalerts.SafetyNetAlerts.integration.config;

import com.safetynetalerts.SafetyNetAlerts.config.DataConfig;
import com.safetynetalerts.SafetyNetAlerts.config.DataSources;
import com.safetynetalerts.SafetyNetAlerts.repository.IRecoveredData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DataTestConfig extends DataConfig {
    @Autowired
    private DataSources dataSources;
    @Bean
    public IRecoveredData createRecoveredData() {
        IRecoveredData dataTestRecovery = new RecoveredTestData();
        dataTestRecovery.readData(dataSources);
        return dataTestRecovery;
    }


}