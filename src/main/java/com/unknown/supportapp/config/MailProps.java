package com.unknown.supportapp.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("mailProps")
@ConfigurationProperties
@PropertySource("classpath:mail-service-settings.properties")
public class MailProps {
    private Map<String, String> prop = new HashMap<String, String>();

    public Map<String, String> getProp() {
        return prop;
    }

    public void setProp(Map<String, String> prop) {
        this.prop = prop;
    }
}
