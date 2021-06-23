package com.summarizer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@EnableSwagger2
@Configuration
public class SwaggerConfigurations {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.summarizer"))
        .paths(PathSelectors.ant("/**"))
        .build()
        .useDefaultResponseMessages(true)
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Gerador de resumos de textos literários automático")
        .description(
            "Gerador de resumos de textos literários automático feito para o projeto de final de curso da aluna Alice Borges dos Santos, do curso de Bacharelado em Sistemas"
                + "de Informação do Instituto Federal de Ciência e Tecnologia Catarinense")
        .version("1.0")
        .contact(
            new Contact(
                "Alice Borges",
                "https://www.linkedin.com/in/alice-borges/",
                "aliceborges@outlook.com.br"))
        .build();
  }
}
