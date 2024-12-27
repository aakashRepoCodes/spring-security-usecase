package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                       // .requestMatchers("/").permitAll() //to make a route public
                        .anyRequest().authenticated()) // enforce authentication
               // .formLogin(Customizer.withDefaults()) //for UI based clients: Browser
                .httpBasic(Customizer.withDefaults()) //For rest client: Postman
               // .sessionManagement(Customizer.withDefaults()) // session in created once, until logout
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                //session is not created, every request has to identify itself with valid token
                .build();

    }
}
