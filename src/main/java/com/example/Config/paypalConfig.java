package com.example.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.paypal.base.rest.APIContext;

@Configuration
public class paypalConfig {

    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.client-secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    // Define the APIContext bean to interact with PayPal API
    @Bean
    public APIContext apiContext() {
        // Ensure that the credentials and mode are valid before creating APIContext
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        return apiContext;
    }
}


