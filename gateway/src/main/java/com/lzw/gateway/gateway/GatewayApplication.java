package com.lzw.gateway.gateway;

import com.lzw.config.BasicProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.net.UnknownHostException;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableEnvironment environment = SpringApplication.run(GatewayApplication.class, args).getEnvironment();
		BasicProperties.profiles(environment);
	}

}
