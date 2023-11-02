package dev.mvc.team8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.mvc"})
public class Team8V4sbm3cApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team8V4sbm3cApplication.class, args);
	}

}
