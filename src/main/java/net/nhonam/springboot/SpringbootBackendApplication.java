package net.nhonam.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication
@CrossOrigin(origins = "*")
public class SpringbootBackendApplication {

	public static void main(String[] args) {



		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

}
	