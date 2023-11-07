package br.com.desafio.application.config;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import br.com.desafio.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BearerTokenProvider {
    private final JwtEncoder jwtEncoder;

    public String createBearerToken(User user) {
        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("Nome do assinante")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(SecurityConstants.EXPIRATION_TIME))
                .subject(user.getId().toString())
                .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(claimsSet);
        String token = jwtEncoder.encode(parameters).getTokenValue();
        log.debug("Generated bearer token with user id `{}`: {}", user.getId(), token);

        return token;
    }
}
