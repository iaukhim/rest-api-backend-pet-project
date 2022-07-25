package com.unknown.supportapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

@Configuration
public class MappingConfig {



    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PersistenceUtil persistenceUnitUtil(){
        return Persistence.getPersistenceUtil();
    }

}
