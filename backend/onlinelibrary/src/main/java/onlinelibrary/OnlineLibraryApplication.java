package onlinelibrary;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
// import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import onlinelibrary.repositories.BookRepository;

// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

@SpringBootApplication()
// @EnableMongoRepositories
public class OnlineLibraryApplication {
	@Autowired
	// BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	// 	return args -> {

	// 		System.out.println("Bismillah, Allahu Akbar");
	// 		System.out.println("Saifudin");

	// 		// String[] beanNames = ctx.getBeanDefinitionNames();
	// 		// Arrays.sort(beanNames);
	// 		// for (String beanName : beanNames) {
	// 		// System.out.println(beanName);
	// 		// }

	// 	};
	// }

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}

}
