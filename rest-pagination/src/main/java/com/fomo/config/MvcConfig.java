package com.fomo.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableAutoConfiguration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
		// Set the default size to 5
		phmar.setFallbackPageable(new PageRequest(0, 5));
		argumentResolvers.add(phmar);
		super.addArgumentResolvers(argumentResolvers);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		super.addViewControllers(registry);
		registry.addViewController("/howPointsWorks").setViewName("howPointsWorks");
		registry.addViewController("/test").setViewName("test");
		registry.addViewController("/signin").setViewName("signin");
		registry.addViewController("/interests").setViewName("interests");
		registry.addViewController("/social_connect").setViewName("social_connect");
		registry.addViewController("/payments").setViewName("payments");
		registry.addViewController("/contentFeed").setViewName("contentFeed");
		registry.addViewController("/play_and_earn").setViewName("play_and_earn");
		registry.addViewController("/contacts12").setViewName("contacts");

	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		final Charset UTF8 = Charset.forName("UTF-8");
		stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", UTF8)));
		converters.add(jsonConverter());
		converters.add(stringConverter);

	}

	@Bean
	public MappingJackson2HttpMessageConverter jsonConverter() {
		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		jacksonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.valueOf("application/json")));
		// jacksonConverter.setObjectMapper(jacksonObjectMapper());
		return jacksonConverter;
	}

	@Bean
	public ObjectMapper jacksonObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		return objectMapper;
	}

	@Override
	public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter requestMapping = new RequestMappingHandlerAdapter();
		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(jsonConverter());
		requestMapping.setMessageConverters(converters);
		return requestMapping;
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:src/main/resources/");
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
		return filter;
	}

	/*	@Bean
	public Docket mainConfig() {// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build()

				.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class);

	}// @formatter:on
 
/*	@Autowired
	SpringSwaggerConfig springSwaggerConfig;

	@Bean
	public SwaggerSpringMvcPlugin configureSwagger() {
		SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);
		ApiInfo apiInfo = new ApiInfoBuilder().title("QuickPoll REST API")
				.description("QuickPoll Api for creating and managing 	polls")
				.termsOfServiceUrl("http://example.com/terms-of-service").contact("info@example.com")
				.license("MIT License").licenseUrl("http://opensource.org/licenses/MIT").build();
		swaggerSpringMvcPlugin.apiInfo(apiInfo).apiVersion("1.0");
		return swaggerSpringMvcPlugin;
	}
	@Bean
	public Docket mainConfig() {// @formatter:off
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
		
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class);

	}// @formatter:on
*/
}
