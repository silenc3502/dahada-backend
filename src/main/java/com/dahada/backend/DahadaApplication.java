package com.dahada.backend;

import com.dahada.backend.application.configuration.props.OAuth2KakaoRegistrationProperties;
import com.dahada.backend.application.configuration.props.OAuth2NaverRegistrationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@ConfigurationPropertiesScan
@EnableJpaAuditing
@EnableWebSecurity
@SpringBootApplication
public class DahadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DahadaApplication.class, args);
    }

    @Autowired
    private OAuth2NaverRegistrationProperties properties;

    @Autowired
    private OAuth2KakaoRegistrationProperties kakaoProps;

    @Bean
    public void test() {
        System.out.println(properties);
        System.out.println(kakaoProps);
    }

}
