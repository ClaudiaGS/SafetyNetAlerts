package com.safetyfirst.SafetyFirstApp.integration.config;

import com.safetyfirst.SafetyFirstApp.config.DataConfig;
import com.safetyfirst.SafetyFirstApp.config.DataSources;
import com.safetyfirst.SafetyFirstApp.repository.IRecoveredData;
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