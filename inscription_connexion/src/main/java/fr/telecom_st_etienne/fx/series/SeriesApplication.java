package fr.telecom_st_etienne.fx.series;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeriesApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
		return args -> {

			String[] noms = applicationContext.getBeanDefinitionNames();

			for (String nom : noms) {
				System.out.println(nom + " : " + applicationContext.getBean(nom).getClass().toString());
			}
		};
	}

}
