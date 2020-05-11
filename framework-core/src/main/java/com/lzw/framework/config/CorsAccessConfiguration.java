package com.lzw.framework.config;

import com.lzw.framework.MicroServiceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Auther: Rick
 * @Date: 2020/5/11 14
 * @Description:
 */
public class CorsAccessConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorsAccessConfiguration.class);

    private final MicroServiceProperties microServiceProperties;

    public CorsAccessConfiguration(MicroServiceProperties microServiceProperties) {
        this.microServiceProperties = microServiceProperties;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = microServiceProperties.getCors();
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            LOGGER.debug("Registering CORS filter");
            source.registerCorsConfiguration("/api/**", config);
            source.registerCorsConfiguration("/oauth/**", config);
            source.registerCorsConfiguration("/v2/api-docs", config);
        }
        return new CorsFilter(source);
    }
}
