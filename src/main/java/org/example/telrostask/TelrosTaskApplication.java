package org.example.telrostask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TelrosTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelrosTaskApplication.class, args);
	}

}
