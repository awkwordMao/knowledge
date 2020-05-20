package com.lzw.gateway.gateway;

//import com.lzw.config.BasicProperties;

import com.lzw.framework.config.BasicProperties;
import com.lzw.framework.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.UnknownHostException;

@SpringBootApplication
@Import({SwaggerConfig.class})
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableEnvironment environment = SpringApplication.run(GatewayApplication.class, args).getEnvironment();
		BasicProperties.profiles(environment);
	}

}
