package com.shailendra.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.shailendra")
//@EnableJpaRepositories("com.shailendra.repo")
//@EntityScan("com.shailendra.pojo")
//@EnableScheduling
public class KalahaMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(KalahaMainApplication.class, args);
    }
}
