package com.lzw.config;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 16
 * @Description:
 */
@Slf4j
public class BasicProperties {

    public static void profiles(Environment environment) throws UnknownHostException {
        String protocol = "http";
        if (environment.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        Collection<String> applicationConfigs = Sets.newHashSet();
        if (environment instanceof StandardServletEnvironment) {
            StandardServletEnvironment standardServletEnvironment = (StandardServletEnvironment)environment;
            MutablePropertySources mutablePropertySources = standardServletEnvironment.getPropertySources();
            if (mutablePropertySources != null) {
                Iterator propertySources = mutablePropertySources.iterator();

                while(propertySources.hasNext()) {
                    PropertySource propertySource = (PropertySource)propertySources.next();
                    String configName = propertySource.getName();
                    if (configName.startsWith("applicationConfig")) {
                        applicationConfigs.add(configName);
                    }
                }
            }
        }
        log.info("\n----------------------------------------------------------\n\tEcloudApplication '{}' is running! Access URLs:\n\tLocal: \t\t{}://localhost:{}\n\tExternal: \t{}://{}:{}\n\tProfile(s): \t{}\n\tConfig(s): \t{}\n----------------------------------------------------------", new Object[]{environment.getProperty("spring.application.name"), protocol, environment.getProperty("server.port"), protocol, InetAddress.getLocalHost().getHostAddress(), environment.getProperty("server.port"), getActiveProfiles(environment), applicationConfigs});
    }

    public static String[] getActiveProfiles(Environment environment) {
        String[] activeProfiles = environment.getActiveProfiles();
        return activeProfiles.length == 0 ? environment.getDefaultProfiles() : activeProfiles;
    }
}
