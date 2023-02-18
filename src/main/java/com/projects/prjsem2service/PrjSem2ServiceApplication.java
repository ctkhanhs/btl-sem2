package com.projects.prjsem2service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200")
public class PrjSem2ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrjSem2ServiceApplication.class, args);
    }

}
