package io.github.brunnotoscano.feignclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//|*********************************|
//| Descrição: Aplicativo Principal |
//|*********************************|

@SpringBootApplication
@EnableFeignClients
public class FeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}

}
