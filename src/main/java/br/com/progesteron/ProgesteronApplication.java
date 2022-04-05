package br.com.progesteron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ProgesteronApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgesteronApplication.class, args);
	}

}
