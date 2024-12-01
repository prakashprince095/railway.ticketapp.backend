package com.sparx.railway.ticketapp.backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Beans {

    @Bean
    @Qualifier("modelMapper")
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }
}
