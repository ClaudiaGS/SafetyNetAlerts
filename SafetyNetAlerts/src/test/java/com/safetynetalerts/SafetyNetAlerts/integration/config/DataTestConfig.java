package com.safetynetalerts.SafetyNetAlerts.integration.config;

import com.safetynetalerts.SafetyNetAlerts.config.DataSources;
import com.safetynetalerts.SafetyNetAlerts.repository.IRecoveredData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class DataTestConfig {
    @Autowired
    private DataSources dataSources;
    @Bean
    @Primary
    public IRecoveredData createRecoveredTestData() {
        IRecoveredData dataTestRecovery = new RecoveredTestData();
        dataTestRecovery.readData(dataSources);
        return dataTestRecovery;
    }


}