package by.valashchuk.microvisualizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@EnableJpaRepositories
@SpringBootApplication
public class MicroVisualizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroVisualizerApplication.class, args);
	}

}
