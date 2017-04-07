package com.intertec;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.intertec.domain.Username;
import com.intertec.repositories.IUsernameRepository;

@SpringBootApplication
public class IntertecApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntertecApplication.class, args);
	}

	@Bean
    public CommandLineRunner init(IUsernameRepository repository) {
    	return (evt) -> Arrays.asList(
    			"john, Doe, jrossi, mjordan, scurry, ljames".split(",")).forEach(
    					a -> { 
    						repository.save(new Username(a));	
    					}	
    			);
    	
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
    }

}
