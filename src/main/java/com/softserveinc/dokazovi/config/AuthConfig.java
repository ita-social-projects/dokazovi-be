package com.softserveinc.dokazovi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "auth-config")
public class AuthConfig {

    private boolean useXForwardedFor;

    public boolean isUseXForwardedFor() {
        return useXForwardedFor;
    }

    public void setUseXForwardedFor(boolean useXForwardedFor) {
        this.useXForwardedFor = useXForwardedFor;
    }
}
