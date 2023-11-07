package br.com.desafio.application.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        servers = {@Server(url = "http://localhost:8080")},
        info =
                @Info(
                        title = "Department Service APIs",
                        description = "This lists all the Department Service API Calls.",
                        version = "v1.0"))
@SecurityScheme(
        name = "security_auth",
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.APIKEY,
        bearerFormat = "JWT",
        scheme = "bearer"
        //        flows = @OAuthFlows(clientCredentials  = @OAuthFlow(tokenUrl = "${openapi.oAuthFlow.tokenUrl}", scopes
        // = {
        //                @OAuthScope(name = "openid", description = "openid scope")
        //        }))
        )
public class OpenAPI3Configuration {}
