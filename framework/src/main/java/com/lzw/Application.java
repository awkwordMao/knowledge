package com.lzw;

import com.lzw.config.BasicProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.UnknownHostException;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 16
 * @Description:
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        ConfigurableApplicationContext run = app.run(args);
        ConfigurableEnvironment environment = run.getEnvironment();
        BasicProperties.profiles(environment);
    }

}
