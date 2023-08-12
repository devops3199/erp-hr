package com.erp.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(config -> config.disable())
                .authorizeHttpRequests(req -> req.requestMatchers("/api/**").permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
