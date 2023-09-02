package br.com;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EntityScan("br.com.model.entity")
@EnableJpaRepositories("br.com.repository")
public class Application {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		String swaggerUrl = "http://localhost:8080/swagger-ui.html#/";

		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			SpringApplication.run(Application.class, args);
			Desktop.getDesktop().browse(new URI(swaggerUrl));
			log.info("Aplicação iniciada.");

		}
	}

}
