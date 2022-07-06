package pl.dev.CarHire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@EnableWebMvc
@ComponentScan(basePackages = { "pl.dev.CarHire", "pl.dev.CarHire.config" , "pl.dev.CarHire.model.*"})
public class CarHireApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarHireApplication.class, args);
	}

}
