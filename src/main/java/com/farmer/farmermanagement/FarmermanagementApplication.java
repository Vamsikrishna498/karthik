package com.farmer.farmermanagement; // ✅ This should match your test class package

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.farmer.farmermanagement.entity")
@ComponentScan("com.farmer.farmermanagement")
public class FarmermanagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(FarmermanagementApplication.class, args);
    }
	
}
