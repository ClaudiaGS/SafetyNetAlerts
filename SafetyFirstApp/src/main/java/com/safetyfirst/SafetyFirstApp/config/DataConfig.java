package com.safetyfirst.SafetyFirstApp.config;

import com.safetyfirst.SafetyFirstApp.repository.IRecoveredData;
import com.safetyfirst.SafetyFirstApp.repository.RecoveredData;
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
