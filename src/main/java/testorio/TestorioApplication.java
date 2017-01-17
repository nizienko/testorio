package testorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestorioApplication {

	public static void main(String[] args) {
		SpringApplication.run( new Class<?>[] {TestorioApplication.class, ApplicationConfig.class}, args);
	}
}
