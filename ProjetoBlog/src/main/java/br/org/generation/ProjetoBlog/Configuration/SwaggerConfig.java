package br.org.generation.ProjetoBlog.Configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.org.generation.ProjetoBlog.controller"))
                .paths(PathSelectors.any()).build().apiInfo(metadata()).useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, responseMessageForGET());

    }

    public static ApiInfo metadata() {

        return new ApiInfoBuilder().title("O Blogão").description("Projeto do Blog Pessoal - Bloco II.")
                .version("1.0.0").license("Apache License Version 2.0").licenseUrl("http://localhost:8080/swagger-ui/")
                .contact(contact()).build();
    }

    private static Contact contact() {

        return new Contact("Leandro de Souza", "https://github.com/leandrodesouza25", "leandro.desouza606@gmail.com");

    }

    private static List<Response> responseMessageForGET() {

        return new ArrayList<Response>() {

            private static final long serialVersionUID = 1L;

            {
                add(new ResponseBuilder().code("200").description("Deu Certo").build());
                add(new ResponseBuilder().code("201").description("Funcionou amg :).").build());
                add(new ResponseBuilder().code("401").description("Não foi dessa vez ::( .").build());
                add(new ResponseBuilder().code("403").description("Vish, Barrado.").build());
                add(new ResponseBuilder().code("404").description("Não foi encontrado...").build());
                add(new ResponseBuilder().code("500").description("Vish, deu erro :/ ").build());
            }
        };

    }
}

