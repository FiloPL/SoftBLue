package com.filopl.softblue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

/**
 * Created by T. Filo Zegarlicki on 10.06.2023
 **/

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("softblue-github-api")
                .apiInfo(apiInfo())
                .select()
                .paths(any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SoftBlue")
                .description("RecruitmentTask SoftBlue API")
                .termsOfServiceUrl("http://springfox.io")
                .contact(new Contact("Tomasz Zegarlicki", "", "tzegarlicki@gmail.com"))
                .license("OpenSource 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("1.0")
                .build();
    }
}
