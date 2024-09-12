package com.fcamara.parking.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

public class DateConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        return objectMapper;
    }
}