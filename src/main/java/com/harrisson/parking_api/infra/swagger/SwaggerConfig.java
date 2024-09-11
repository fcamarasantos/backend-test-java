package com.harrisson.parking_api.infra.swagger;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Parking")
                        .version("v1")
                        .description("Documentação da API para Estacionamento\n\nURL Base: parkingapi-production-0b39.up.railway.app ")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public GroupedOpenApi api(RequestMappingHandlerMapping handlerMapping) {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/establishments/**", "/access-controls/**", "/reports/**", "/access-controls/**", "/vehicles/**")
                .packagesToScan("com.harrisson.parking_api.controller")
                .addOpenApiCustomizer(filterControllers(handlerMapping))
                .build();
    }

    @Bean
    public OpenApiCustomizer filterControllers(RequestMappingHandlerMapping handlerMapping) {
        return openApi -> {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
            handlerMethods.forEach((requestMappingInfo, handlerMethod) -> {
                if (!handlerMethod.getBeanType().isAnnotationPresent(Tag.class)) {
                    Set<String> patterns = requestMappingInfo.getPatternsCondition() != null ? requestMappingInfo.getPatternsCondition().getPatterns() : null;
                    if (patterns != null) {
                        patterns.forEach(openApi.getPaths()::remove);
                    }
                }
            });
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // Allow all origins
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}