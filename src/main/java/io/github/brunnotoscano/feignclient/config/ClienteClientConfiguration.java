package io.github.brunnotoscano.feignclient.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import io.github.brunnotoscano.feignclient.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
