package com.octaviocenteno.technicaltest.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octaviocenteno.technicaltest.client.OBPTransactionsClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Value("${feign.host.openBankProject}")
    private String openBankProjectHost;

    @Bean
    public OBPTransactionsClient getOBPTransactionsClient(ObjectMapper objectMapper){
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(OBPTransactionsClient.class, openBankProjectHost);
    }
}
