package com.example.carrental;

import com.example.carrental.service.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class CarRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/v1/users/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/rents/newcardrent/{userId}").permitAll()
                    .antMatchers(HttpMethod.GET, "allcars").permitAll()
                    .antMatchers(HttpMethod.GET, "allusers").permitAll()
                    .antMatchers(HttpMethod.DELETE, "/delete/{userId}").permitAll()
                    .antMatchers(HttpMethod.DELETE, "/cancelrent/{cardId}").permitAll()
           // "/update/{rentId}"
                    .anyRequest().authenticated();
        }
    }
}