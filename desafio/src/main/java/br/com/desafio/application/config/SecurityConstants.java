package br.com.desafio.application.config;

public class SecurityConstants {
    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/services/controller/user";
    public static final String AUTHORIZATION_PATTERN = "^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$";
    public static final String GROUP_PATTERN = "token";
    public static final String[] AUTH_WHITE_LIST = {
        "/v3/api-docs/**", "/swagger-ui/**", "/v2/api-docs/**", "/swagger-resources/**"
    };
}
