package com.safetynetalerts.SafetyNetAlerts.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.URL;

@Configuration
@ConfigurationProperties(prefix = "com.safetynetalerts")
@Data
public class DataSources {
    private URL dataUrl;
    private File dataTest;
    
    
}

