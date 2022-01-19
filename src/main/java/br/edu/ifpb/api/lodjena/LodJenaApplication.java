package br.edu.ifpb.api.lodjena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * 
 * @author Ronei Oliveira
 *
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Linked Open Data API", version = "0.0.1", description = "Linked Open Data from Apache Jena"))
public class LodJenaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LodJenaApplication.class, args);
	}

}
