package com.school.sunflower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SunflowerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunflowerApplication.class, args);
    }

}
