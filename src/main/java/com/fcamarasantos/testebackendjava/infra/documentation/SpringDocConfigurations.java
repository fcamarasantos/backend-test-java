package com.fcamarasantos.testebackendjava.infra.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("FCamara API")
            .description("Teste para vaga de Desenvolvedor Back-end\n" +
                "Criar uma API REST para gerenciar um estacionamento de carros e motos.")
            .contact(new Contact()
                .name("Time backend")
                .email("devlinomota@gmail.com")));
  }

}
