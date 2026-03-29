package com.f1.boxbox.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BoxBox API Spring")
                        .description("""
                            La **F1 BoxBox API** es un servicio RESTful para gestionar información de la temporada de Fórmula 1.
                            Permite consultar y administrar datos sobre **pilotos**, **equipos**, **carreras** y **resultados** de cada Gran Premio.
                
                            Funcionalidades principales:
                            - Obtener la lista de pilotos con sus equipos, bandera, imagen y puntos acumulados.
                            - Consultar y actualizar información de equipos, incluyendo puntos y logos.
                            - Registrar y consultar resultados de cada carrera, incluyendo posiciones y puntos obtenidos.
                            - Gestionar el historial de equipos de cada piloto.
                            """)
                        .version("1.0"))
                        .servers(List.of(
                                new Server().url("https://boxboxapi-spring-production.up.railway.app/")
                        ));
    }
}