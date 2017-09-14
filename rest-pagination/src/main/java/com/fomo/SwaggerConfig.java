package com.fomo;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/*
	 * @Bean public Docket api() { return new
	 * Docket(DocumentationType.SWAGGER_2) .select()
	 * .apis(RequestHandlerSelectors.basePackage("org.baeldung.web.controller"))
	 * .paths(PathSelectors.ant("/foos/*"))
	 * .build().apiInfo(apiInfo()).useDefaultResponseMessages(false)
	 * .globalResponseMessage(RequestMethod.GET, newArrayList(new
	 * ResponseMessageBuilder().code(500).message("500 message"
	 * ).responseModel(new ModelRef("Error")).build(), new
	 * ResponseMessageBuilder().code(403).message("Forbidden!!!!!").build())); }
	 */
	@Bean
	public Docket mainConfig() {// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2).
				 select().apis(RequestHandlerSelectors.basePackage("com.fomo.rest.controller"))
				.paths(PathSelectors.any()).
				 build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
			    .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class);

	}
// @formatter:on

	private ApiInfo apiInfo() {
		@SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo(
				"My REST API", "Some custom description of API.", "API TOS",
				"Terms of service",
				"myeaddress@company.com", 
				"License of API", "API license URL");
		return apiInfo;
	}
}