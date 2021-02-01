package org.aptiliux.restaurant.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public Docket api() {
		
		 ApiInfo apiInfo = new ApiInfoBuilder()
	        		.title("Restaurant API")
	        		.version("1.0")
	                .description("Restaurant API Documentation")
	                .termsOfServiceUrl("urn:tos")
	                .build();
		 
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.aptiliux"))
				.paths(PathSelectors.any()).build();
	}
}
