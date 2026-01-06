package com.knowledgevault.knowledge.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        // API Info
        .info(new Info()
            .title("Knowledge Vault API")
            .description("REST API for managing knowledge entries")
            .version("v1.0"))

        // External Documentation (optional)
        .externalDocs(new ExternalDocumentation()
            .description("Knowledge Vault -Project Documentation")
            .url("https://github.com/ravikr309/Knowledge-Vault"))

        // Security configuration for HTTP Basic
        .components(new Components()
            .addSecuritySchemes("basicAuth",
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("basic")))

        // Apply security globally
        .addSecurityItem(new SecurityRequirement().addList("basicAuth"));
  }
}
