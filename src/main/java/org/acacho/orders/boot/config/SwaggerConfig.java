package org.acacho.orders.boot.config;

import static springfox.documentation.builders.PathSelectors.regex;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String ENDPOINTS_REGEX = "/product.*|/order.*";
  private static final String NAME = "Alejandro Cacho";
  private static final String URL = "https://www.linkedin.com/in/alejandrocacho";
  private static final String EMAIL = "alex.cacho@outlook.com";

  @Bean
  public Docket api(final TypeResolver typeResolver) {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .paths(regex(ENDPOINTS_REGEX))
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Orders Service")
        .description("REST microservice for orders and product handling.")
        .version("1.0")
        .contact(new Contact(NAME, URL, EMAIL))
        .build();
  }
}
