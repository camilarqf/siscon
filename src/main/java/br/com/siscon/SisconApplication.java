package br.com.siscon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2
@SpringBootApplication
public class SisconApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisconApplication.class, args);
	}

}
