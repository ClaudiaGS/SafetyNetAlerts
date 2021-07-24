package com.safetyfirst.SafetyFirstApp.config;

import com.safetyfirst.SafetyFirstApp.repository.IDataRecovery;
import com.safetyfirst.SafetyFirstApp.repository.RecoveredData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    
    @Bean
    public IDataRecovery createRecoveredData() {
        IDataRecovery dataRecovery = new RecoveredData();
        dataRecovery.readData();
        return dataRecovery;
    }
}
