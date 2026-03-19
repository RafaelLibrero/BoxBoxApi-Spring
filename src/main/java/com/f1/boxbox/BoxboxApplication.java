package com.f1.boxbox;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Mi API",
				version = "1.0",
				description = """
            La **F1 BoxBox API** es un servicio RESTful para gestionar información de la temporada de Fórmula 1.
            Permite consultar y administrar datos sobre **pilotos**, **equipos**, **carreras** y **resultados** de cada Gran Premio.

            Funcionalidades principales:
            - Obtener la lista de pilotos con sus equipos, bandera, imagen y puntos acumulados.
            - Consultar y actualizar información de equipos, incluyendo puntos y logos.
            - Registrar y consultar resultados de cada carrera, incluyendo posiciones y puntos obtenidos.
            - Gestionar el historial de equipos de cada piloto.
            """
		)
)
public class BoxboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoxboxApplication.class, args);
	}

}
