package io.github.brunnotoscano.feignclient.config;

import feign.codec.ErrorDecoder;
import io.github.brunnotoscano.feignclient.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//|***********************************************************************|
//| Descrição: Configuração do Cliente Feign que usa o CustomErrorDecoder |
//|***********************************************************************|
@Configuration
public class ClienteClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }

}
