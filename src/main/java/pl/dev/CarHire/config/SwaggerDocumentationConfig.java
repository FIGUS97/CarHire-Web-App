package pl.dev.CarHire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerDocumentationConfig {
  @Bean
  public Docket customImplementation(){
    return new Docket(DocumentationType.OAS_30)
        .select()
        .apis(RequestHandlerSelectors.basePackage("pl.dev.CarHire"))
        .build()
        .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
        .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
        .apiInfo(apiInfo());
  }

  ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("CarHire Car Registration API")
        .description("API for registrating cars for rental.")
        .license("")
        .licenseUrl("http://unlicense.org")
        .termsOfServiceUrl("https://github.com/FIGUS97/")
        .version("0.1")
        .contact(new Contact("","", "mateusz.kolaczyk.97@gmail.com"))
        .build();
  }

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
        .info(new Info()
            .title("CarHire Car Registration API")
            .description("API for registrating cars for rental.")
            .termsOfService("https://github.com/FIGUS97/")
            .version("0.1")
            .license(new License()
                .name("")
                .url("http://unlicense.org"))
            .contact(new io.swagger.v3.oas.models.info.Contact()
                .email("mateusz.kolaczyk.97@gmail.com")));
  }


}
